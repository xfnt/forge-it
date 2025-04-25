package io.github.xfnt.tools;

import io.github.xfnt.Generated;

import java.lang.annotation.Annotation;

public class StringGenerator implements Generated<String, FakeString> {

    @Override
    public String generate(FakeString annotation) {
        return "This should be a random string.";
    }
}
