package io.github.xfnt;

import io.github.xfnt.annotation.ForgeTag;
import io.github.xfnt.annotation.Template;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ForgeIt {

    public static <T, A extends Annotation> T forge(Class<T> template) {
        // Step 1. Check annotation @Template
        checkTemplateAnnotationOnClass(template);

        // Step 2. Create instance of T
        T instance = createInstance(template);

        // Step 3. Build dependency between Generator -> Annotation - > Field
        Map<Generated<?, A>, Map<Field, Annotation>> dependencyTree =  buildDependencyGenFieldAnnotation(template);

        // Step 4. Generate and inject data in field
        generateAndInjectData(instance, dependencyTree);

       return instance;
    }

    private static <T> void checkTemplateAnnotationOnClass(Class<T> template) {
        if(!template.isAnnotationPresent(Template.class)) {
            throw new RuntimeException("Annotation @Template not found!");
        }
    }

    private static <T> T createInstance(Class<T> template) {
        try {
            Constructor<T> constructor = template.getDeclaredConstructor();
            if(constructor.canAccess(null)) {
                constructor.setAccessible(true);
            }
            return constructor.newInstance();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Class should have default constructor!", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static <T, A extends Annotation> Map<Generated<?, A>, Map<Field, Annotation>> buildDependencyGenFieldAnnotation(Class<T> template) {
        Map<Generated<?, A>, Map<Field, Annotation>> map = new HashMap<>();
        Field[] fields = template.getDeclaredFields();

        for(Field field : fields) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            for(Annotation annotation : annotations) {
                if(annotation.annotationType().isAnnotationPresent(ForgeTag.class)) {
                    ForgeTag forgeTag = annotation.annotationType().getAnnotation(ForgeTag.class);
                    Generated<?, A> generator = (Generated<?, A>) createInstance(forgeTag.generatedClass());
                    map.computeIfAbsent(generator, key -> new HashMap<>()).put(field, annotation);
                }
            }
        }
        return map;
    }

    private static <T, A extends Annotation> void generateAndInjectData(T instance,  Map<Generated<?, A>, Map<Field, Annotation>> dependencyTree) {
        for(Map.Entry<Generated<?, A>, Map<Field, Annotation>> entry : dependencyTree.entrySet()) {
            Generated<?, A> generator = entry.getKey();
            Map<Field, Annotation> fieldAnnotationMap = entry.getValue();
            for(Map.Entry<Field, Annotation> fieldAnnotationEntry : fieldAnnotationMap.entrySet()) {
                Field field = fieldAnnotationEntry.getKey();
                A annotation = (A) fieldAnnotationEntry.getValue();
                field.setAccessible(true);
                try {
                    field.set(instance, generator.generate(annotation));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
