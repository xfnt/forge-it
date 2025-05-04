package io.github.xfnt.dto;

import io.github.xfnt.annotation.FailingField;
import io.github.xfnt.annotation.Template;

@Template
public class FailingTemplate {

    @FailingField
    private String data;
}
