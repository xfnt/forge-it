package io.github.xfnt.dto;

import io.github.xfnt.annotation.FakeInt;
import io.github.xfnt.annotation.FakeString;
import io.github.xfnt.annotation.Template;

@Template
public class TestObject {

    @FakeInt
    private Integer number;

    @FakeString
    private String line;

    public Integer getNumber() {
        return number;
    }

    public String getLine() {
        return line;
    }

    @Override
    public String toString() {
        return "TestObject{" +
                "number=" + number +
                ", line='" + line + '\'' +
                '}';
    }
}
