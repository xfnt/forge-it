package io.github.xfnt;

import java.lang.annotation.Annotation;

public interface Generated <T, A extends Annotation> {

    T generate(A annotation);
}
