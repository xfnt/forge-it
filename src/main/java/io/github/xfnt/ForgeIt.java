package io.github.xfnt;

import io.github.xfnt.construct.Forge;
import io.github.xfnt.construct.Oven;

public class ForgeIt {

    public static <T> T forge(Class<T> source) {
        Oven<T> oven = new Oven<>(source);
        Forge<T> forge = new Forge<>(oven);
        return forge.forge();
    }
}
