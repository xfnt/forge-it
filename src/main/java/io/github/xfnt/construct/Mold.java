package io.github.xfnt.construct;

import io.github.xfnt.Generated;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

public class Mold<T> {
    private T instance;
    private Map<Class<? extends Generated<?, ? extends Annotation>>, Map<Field, Annotation>> generatedFieldsMolds;

    public T getInstance() {
        return instance;
    }

    public void setInstance(T instance) {
        this.instance = instance;
    }

    public Map<Class<? extends Generated<?, ? extends Annotation>>, Map<Field, Annotation>> getGeneratedFieldsMolds() {
        return generatedFieldsMolds;
    }

    public void setGeneratedFieldsMolds(Map<Class<? extends Generated<?, ? extends Annotation>>, Map<Field, Annotation>> generatedFieldsMolds) {
        this.generatedFieldsMolds = generatedFieldsMolds;
    }
}
