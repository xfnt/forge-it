package io.github.xfnt.util;

import io.github.xfnt.Generated;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class GeneratorUtils {

    private static final Map<Class<?>, Generated<?, ?>> generatorCache = new ConcurrentHashMap<>();

    private GeneratorUtils() {
        throw new RuntimeException("Utility class");
    }

    @SuppressWarnings("unchecked")
    public static <A extends Annotation> Generated<?, A> getGenerator(Class<?> rawGenClass) {
        return (Generated<?, A>) generatorCache.computeIfAbsent(rawGenClass, GeneratorUtils::createInstance);
    }

    private static Generated<?, ?> createInstance(Class<?> clazz) {
        try {
            return (Generated<?, ?>) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to instantiate generator: " + clazz.getName(), e);
        }
    }
}
