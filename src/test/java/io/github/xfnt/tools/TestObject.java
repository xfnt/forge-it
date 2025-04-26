package io.github.xfnt.tools;

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

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return "MyObject{" +
                "number=" + number +
                ", line='" + line + '\'' +
                '}';
    }
}
