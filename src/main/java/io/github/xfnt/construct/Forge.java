package io.github.xfnt.construct;

import io.github.xfnt.Generated;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

public class Forge<T> {
    private final Oven<T> oven;

    public Forge(Oven<T> oven) {
        this.oven = oven;
    }

    public T forge() {
        Mold<T> mold = oven.melt();

        Map<Class<? extends Generated<?, ? extends Annotation>>, Map<Field, Annotation>> fieldsMolds = mold.getGeneratedFieldsMolds();

        for (Map.Entry<Class<? extends Generated<?, ? extends Annotation>>, Map<Field, Annotation>> entry : fieldsMolds.entrySet()) {
            // тут изолируем каст в хелпер
            applyGeneratorUnsafe(entry.getKey(), entry.getValue(), mold.getInstance());
        }

        return mold.getInstance();
    }

    @SuppressWarnings("unchecked")
    private void applyGeneratorUnsafe(Class<? extends Generated<?, ? extends Annotation>> rawGeneratorClass, Map<Field, Annotation> rawFieldMap, Object instance) {
        applyGenerator((Class<Generated<?, Annotation>>) rawGeneratorClass, rawFieldMap, instance);
    }

    private <A extends Annotation> void applyGenerator(Class<? extends Generated<?, A>> generatorClass, Map<Field, A> fieldMap, Object instance) {
        try {
            Generated<?, A> generator = generatorClass.getDeclaredConstructor().newInstance();

            for (Map.Entry<Field, A> entry : fieldMap.entrySet()) {
                Field field = entry.getKey();
                A annotation = entry.getValue();

                field.setAccessible(true);
                field.set(instance, generator.generate(annotation));
            }
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Failed to apply generator: " + generatorClass.getName(), e);
        }
    }

}
