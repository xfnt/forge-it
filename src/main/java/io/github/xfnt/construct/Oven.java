package io.github.xfnt.construct;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Oven<T> {
    private final Class<T> source;

    public Oven(Class<T> source) {
        this.source = source;
    }

    public Mold<T> melt() {
        Mold<T> mold = new Mold<>();

        mold.setInstance(ovenInstance());
        return null;
    }

    private T ovenInstance() {
        T instance = null;
        try {
            Constructor<T> constructor = source.getDeclaredConstructor();
            if(constructor.canAccess(null)) {
                constructor.setAccessible(true);
            }
            instance = constructor.newInstance();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }
}
