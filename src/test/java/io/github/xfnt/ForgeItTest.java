package io.github.xfnt;

import io.github.xfnt.annotation.Template;
import io.github.xfnt.dto.FailingTemplate;
import io.github.xfnt.dto.SimpleTemplate;
import io.github.xfnt.dto.TestObject;
import io.github.xfnt.exception.ForgeGenerationException;
import io.github.xfnt.exception.ForgeInstantiationException;
import io.github.xfnt.exception.ForgeTemplateException;
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
        assertTrue(testObject.getNumber() >= 10 && testObject.getNumber() <= 15);
    }

    @Test
    void shouldThrowTemplateExceptionWhenClassIsNotAnnotated() {
        class NotAnnotated {}

        ForgeTemplateException exception = assertThrows(
                ForgeTemplateException.class,
                () -> ForgeIt.forge(NotAnnotated.class)
        );

        assertTrue(exception.getMessage().contains("must be annotated with @Template"));
    }

    @Test
    void shouldThrowInstantiationExceptionWhenNoDefaultConstructor() {
        @Template
        class NoDefaultConstructor {
            private String field;
        }

        ForgeInstantiationException exception = assertThrows(
                ForgeInstantiationException.class,
                () -> ForgeIt.forge(NoDefaultConstructor.class)
        );

        assertTrue(exception.getMessage().contains("Failed to instantiate class"));
    }

    @Test
    void shouldSuccessfullyForgeSimpleTemplate() {
        SimpleTemplate template = ForgeIt.forge(SimpleTemplate.class);

        assertNotNull(template);
        assertNotNull(template.getName());
        assertFalse(template.getName().isEmpty());
    }

    @Test
    void shouldThrowGenerationExceptionWhenGeneratorFails() {
        ForgeGenerationException exception = assertThrows(
                ForgeGenerationException.class,
                () -> ForgeIt.forge(FailingTemplate.class)
        );

        assertTrue(exception.getMessage().contains("Generator"));
    }
}
