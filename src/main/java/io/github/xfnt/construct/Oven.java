package io.github.xfnt.construct;

import io.github.xfnt.Generated;
import io.github.xfnt.annotation.ForgeTag;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Oven<T> {
    private final Class<T> source;

    public Oven(Class<T> source) {
        this.source = source;
    }

    public Mold<T> melt() {
        Mold<T> mold = new Mold<>();

        mold.setInstance(ovenInstance());
        mold.setGeneratedFieldsMolds(findGeneratedFieldsMolds());

        return mold;
    }

    private Map<Class<? extends Generated<?, ? extends Annotation>>, Map<Field, Annotation>> findGeneratedFieldsMolds() {
        Map<Class<? extends Generated<?, ? extends Annotation>>, Map<Field, Annotation>> genFieldAnnotationMold = new HashMap<>();
        Field[] fields = source.getDeclaredFields();

        for (Field field : fields) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> annotationType = annotation.annotationType();

                if (annotationType.isAnnotationPresent(ForgeTag.class)) {
                    ForgeTag forgeTag = annotationType.getAnnotation(ForgeTag.class);
                    Class<? extends Generated<?, ? extends Annotation>> generatorClass = forgeTag.generatedClass();

                    genFieldAnnotationMold
                            .computeIfAbsent(generatorClass, k -> new HashMap<>())
                            .put(field, annotation);
                }
            }
        }

        return genFieldAnnotationMold;
    }

    private T ovenInstance() {
        T instance = null;
        try {
            Constructor<T> constructor = source.getDeclaredConstructor();
            if(constructor.canAccess(null)) {
                constructor.setAccessible(true);
            }
            instance = constructor.newInstance();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }
}
