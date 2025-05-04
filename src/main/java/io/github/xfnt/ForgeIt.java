package io.github.xfnt;

import io.github.xfnt.annotation.Template;
import io.github.xfnt.exception.ForgeGenerationException;
import io.github.xfnt.exception.ForgeInjectionException;
import io.github.xfnt.exception.ForgeInstantiationException;
import io.github.xfnt.exception.ForgeTemplateException;
import io.github.xfnt.logger.ForgeLogger;
import io.github.xfnt.util.GeneratorUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ForgeIt {
    private static final ForgeLogger log = ForgeLogger.getLogger(ForgeIt.class);

    public static <T, A extends Annotation> T forge(Class<T> template) {

        log.info("Starting to forge instance for class [{}]", template.getName());

        // Step 1. Check annotation @Template
        checkTemplateAnnotationOnClass(template);

        // Step 2. Create instance of T
        T instance = createInstance(template);

        // Step 3. Build dependency between Generator -> Annotation - > Field
        Map<Generated<?, A>, Map<Field, Annotation>> dependencyTree =  buildDependencyGenFieldAnnotation(template);

        // Step 4. Generate and inject data in field
        generateAndInjectData(instance, dependencyTree);

        log.info("Successfully forged instance for class [{}]", template.getName());
        return instance;
    }

    private static <T> void checkTemplateAnnotationOnClass(Class<T> template) {
        if (!template.isAnnotationPresent(Template.class)) {
            log.error("Class [{}] is missing required @Template annotation", template.getName());
            throw new ForgeTemplateException(template);
        }
        log.debug("Class [{}] is properly annotated with @Template", template.getName());
    }

    private static <T> T createInstance(Class<T> template) {
        try {
            Constructor<T> constructor = template.getDeclaredConstructor();
            constructor.setAccessible(true);
            T instance = constructor.newInstance();
            log.debug("Successfully created instance of [{}]", template.getName());
            return instance;
        } catch (NoSuchMethodException e) {
            log.error("No default constructor found for [{}]", template.getName(), e);
            throw new ForgeInstantiationException(template, e);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            log.error("Failed to instantiate [{}]", template.getName(), e);
            throw new ForgeInstantiationException(template, e);
        }
    }

    private static <T, A extends Annotation> Map<Generated<?, A>, Map<Field, Annotation>> buildDependencyGenFieldAnnotation(Class<T> template) {
        Map<Generated<?, A>, Map<Field, Annotation>> result = new HashMap<>();

        for (Field field : template.getDeclaredFields()) {
            for (Annotation annotation : field.getDeclaredAnnotations()) {
                Class<? extends Annotation> annotationType = annotation.annotationType();

                if (annotationType.isAnnotationPresent(io.github.xfnt.annotation.ForgeTag.class)) {
                    var forgeTag = annotationType.getAnnotation(io.github.xfnt.annotation.ForgeTag.class);
                    var generator = GeneratorUtils.<A>getGenerator(forgeTag.generatedClass());
                    result.computeIfAbsent(generator, g -> new HashMap<>()).put(field, annotation);
                    log.debug("Mapped field [{}] to generator [{}]", field.getName(), generator.getClass().getSimpleName());
                }
            }
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    private static <T, A extends Annotation> void generateAndInjectData(T instance, Map<Generated<?, A>, Map<Field, Annotation>> dependencyTree) {
        for (Map.Entry<Generated<?, A>, Map<Field, Annotation>> entry : dependencyTree.entrySet()) {
            Generated<?, A> generator = entry.getKey();
            Map<Field, Annotation> fieldAnnotationMap = entry.getValue();

            for (Map.Entry<Field, Annotation> fieldAnnotationEntry : fieldAnnotationMap.entrySet()) {
                Field field = fieldAnnotationEntry.getKey();
                A annotation = (A) fieldAnnotationEntry.getValue();
                field.setAccessible(true);
                try {
                    field.set(instance, generator.generate(annotation));
                    log.debug("Injected generated data into field [{}]", field.getName());
                } catch (IllegalAccessException e) {
                    log.error("Failed to inject data into field [{}]", field.getName(), e);
                    throw new ForgeInjectionException(field, instance.getClass(), e);
                } catch (Exception e) {
                    log.error("Generator [{}] failed to generate value for field [{}]", generator.getClass().getName(), field.getName(), e);
                    throw new ForgeGenerationException(generator.getClass(), field, e);
                }
            }
        }
    }
}
