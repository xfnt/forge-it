package io.github.xfnt.annotation;

import io.github.xfnt.generation.DummyGenerator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@ForgeTag(generatedClass = DummyGenerator.class)
public @interface DummyField {
}
