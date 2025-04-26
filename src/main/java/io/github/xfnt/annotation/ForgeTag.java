package io.github.xfnt.annotation;

import io.github.xfnt.Generated;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface ForgeTag {
    Class<? extends Generated<?, ? extends Annotation>> generatedClass();
}
