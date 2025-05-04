package io.github.xfnt.exception;

public final class ForgeErrorMessages {

    private ForgeErrorMessages() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static final String TEMPLATE_MISSING = "Class '%s' must be annotated with @Template.";
    public static final String INSTANTIATION_FAILED = "Failed to instantiate class '%s'. Ensure it has a public no-args constructor and is not abstract.";
    public static final String INJECTION_FAILED = "Failed to inject value into field '%s' of class '%s'.";
    public static final String GENERATION_FAILED = "Generator '%s' failed for field '%s'.";
    public static final String CONFIGURATION_INVALID = "Invalid configuration: %s";
}