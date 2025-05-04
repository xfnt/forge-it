package io.github.xfnt.exception;

import java.lang.reflect.Field;

public class ForgeInjectionException extends RuntimeException {
  public ForgeInjectionException(Field field, Class<?> clazz, Throwable cause) {
    super(String.format(ForgeErrorMessages.INJECTION_FAILED, field.getName(), clazz.getName()), cause);
  }
}
