package io.github.xfnt.annotation;

import io.github.xfnt.generation.IntegerGenerator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@ForgeTag(generatedClass = IntegerGenerator.class)
public @interface FakeInt {
    int min() default 10;
    int max() default 15;
}
