package io.github.xfnt.construct;

import java.util.List;

public class Mold<T> {
    private T instance;
    private List<GeneratedFieldsMold> generatedFieldsMolds;

    public T getInstance() {
        return instance;
    }

    public void setInstance(T instance) {
        this.instance = instance;
    }

    public List<GeneratedFieldsMold> getGeneratedFieldsMolds() {
        return generatedFieldsMolds;
    }

    public void setGeneratedFieldsMolds(List<GeneratedFieldsMold> generatedFieldsMolds) {
        this.generatedFieldsMolds = generatedFieldsMolds;
    }
}
