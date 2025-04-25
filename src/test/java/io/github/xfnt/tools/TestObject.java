package io.github.xfnt.tools;

import io.github.xfnt.annotation.Source;

//@Source
public class TestObject {

    @FakeInt
    private Integer number;

    @FakeString
    private String line;

    @Override
    public String toString() {
        return "MyObject{" +
                "number=" + number +
                ", line='" + line + '\'' +
                '}';
    }
}
