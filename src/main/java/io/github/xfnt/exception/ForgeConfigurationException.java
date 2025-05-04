package io.github.xfnt.exception;

public class ForgeConfigurationException extends RuntimeException {
  public ForgeConfigurationException(String messageDetail) {
    super(String.format(ForgeErrorMessages.CONFIGURATION_INVALID, messageDetail));
  }
}