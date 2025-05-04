package io.github.xfnt.generation;

import io.github.xfnt.Generated;
import io.github.xfnt.annotation.FakeInt;

import java.util.Random;

public class IntegerGenerator implements Generated<Integer, FakeInt> {
    private final Random random = new Random();

    @Override
    public Integer generate(FakeInt annotation) {
        return random.nextInt(annotation.min(), annotation.max());
    }
}
