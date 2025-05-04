package io.github.xfnt.generation;

import io.github.xfnt.Generated;
import io.github.xfnt.annotation.DummyField;

public class DummyGenerator implements Generated<String, DummyField> {

    @Override
    public String generate(DummyField annotation) {
        return "dummyValue";
    }
}
