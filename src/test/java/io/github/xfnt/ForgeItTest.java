package io.github.xfnt;

import io.github.xfnt.tools.TestObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ForgeItTest {

    @Test
    public void shouldReturnForgedObject() {
        TestObject testObject = ForgeIt.forge(TestObject.class);
        assertNotNull(testObject);
    }

    @Test
    public void shouldReturnForgedObjectWithString() {
        TestObject testObject = ForgeIt.forge(TestObject.class);
        assertNotNull(testObject);
        assertEquals("This should be a random string.", testObject.getLine());
    }

    @Test
    public void shouldReturnForgedObjectWithInteger() {
        TestObject testObject = ForgeIt.forge(TestObject.class);
        assertNotNull(testObject);

        assertTrue(testObject.getNumber() > 10 && testObject.getNumber() < 15);
    }
}
