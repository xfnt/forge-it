package io.github.xfnt.exception;

public class ForgeInstantiationException extends RuntimeException {
    public ForgeInstantiationException(Class<?> clazz, Throwable cause) {
        super(String.format(ForgeErrorMessages.INSTANTIATION_FAILED, clazz.getName()), cause);
    }
}