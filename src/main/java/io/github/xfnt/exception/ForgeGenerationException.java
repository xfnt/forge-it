package io.github.xfnt.exception;

import java.lang.reflect.Field;

public class ForgeGenerationException extends RuntimeException {
  public ForgeGenerationException(Class<?> generatorClass, Field field, Throwable cause) {
    super(String.format(ForgeErrorMessages.GENERATION_FAILED, generatorClass.getSimpleName(), field.getName()), cause);
  }
}
