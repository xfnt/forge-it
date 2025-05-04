package io.github.xfnt.generation;

import io.github.xfnt.Generated;
import io.github.xfnt.annotation.FailingField;

public class FailingGenerator implements Generated<String, FailingField> {

    @Override
    public String generate(FailingField annotation) {
        throw new RuntimeException("Forced failure");
    }
}