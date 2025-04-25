package io.github.xfnt;

import io.github.xfnt.tools.TestObject;
import org.junit.jupiter.api.Test;

public class ForgeItTest {

    @Test
    public void shouldReturnForgedObject() {
        TestObject testObject = ForgeIt.forge(TestObject.class);

        System.out.println(testObject.toString());
    }
}
