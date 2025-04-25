package io.github.xfnt.construct;

public class Forge<T> {
    private final Oven<T> oven;

    public Forge(Oven<T> oven) {
        this.oven = oven;
    }

    public T forge() {
        Mold<T> mold = oven.melt();

        for(GeneratedFieldsMold generatedFieldsMold : mold.getGeneratedFieldsMolds()) {
            generatedFieldsMold.getAnnotatedFields().forEach((field, annotation) -> {
                field.setAccessible(true);
                try {
                    field.set(mold.getInstance(), generatedFieldsMold.getGenerator().generate(annotation));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        return mold.getInstance();
    }
}
