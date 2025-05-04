package io.github.xfnt.exception;

public class ForgeTemplateException extends RuntimeException {
    public ForgeTemplateException(Class<?> clazz) {
        super(String.format(ForgeErrorMessages.TEMPLATE_MISSING, clazz.getName()));
    }
}
