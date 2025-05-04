package io.github.xfnt.dto;

import io.github.xfnt.annotation.DummyField;
import io.github.xfnt.annotation.Template;

@Template
public class SimpleTemplate {
    @DummyField
    private String name;

    public String getName() {
        return name;
    }
}
