package io.github.xfnt.tools;

import io.github.xfnt.Generated;

import java.util.Random;

public class IntegerGenerator implements Generated<Integer, FakeInt> {
    private Random random = new Random();

    @Override
    public Integer generate(FakeInt annotation) {
        return random.nextInt(annotation.min(), annotation.max());
    }
}
