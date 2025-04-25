package io.github.xfnt.construct;

import io.github.xfnt.Generated;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

public class GeneratedFieldsMold {
    private final Generated<?, Annotation> generator;
    private final Map<Field, Annotation> annotatedFields;

    public  GeneratedFieldsMold(Generated<?, Annotation> generator,  Map<Field, Annotation> annotatedFields) {
        this.generator = generator;
        this.annotatedFields = annotatedFields;
    }

    public Generated<?, Annotation> getGenerator() {
        return generator;
    }

    public Map<Field, Annotation> getAnnotatedFields() {
        return annotatedFields;
    }
}
