package io.github.xfnt.generation;

import io.github.xfnt.Generated;
import io.github.xfnt.annotation.FakeString;

public class StringGenerator implements Generated<String, FakeString> {

    @Override
    public String generate(FakeString annotation) {
        return "This should be a random string.";
    }
}
