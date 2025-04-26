package io.github.xfnt.tools;

import io.github.xfnt.annotation.ForgeTag;

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
