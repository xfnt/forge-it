package io.github.xfnt.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ForgeLogger {

    private final Logger slf4jLogger;

    private ForgeLogger(Class<?> clazz) {
        this.slf4jLogger = LoggerFactory.getLogger(clazz);
    }

    public static ForgeLogger getLogger(Class<?> clazz) {
        return new ForgeLogger(clazz);
    }

    public void info(String message, Object... args) {
        if (slf4jLogger.isInfoEnabled()) {
            slf4jLogger.info(message, args);
        }
    }

    public void warn(String message, Object... args) {
        if (slf4jLogger.isWarnEnabled()) {
            slf4jLogger.warn(message, args);
        }
    }

    public void error(String message, Object... args) {
        if (slf4jLogger.isErrorEnabled()) {
            slf4jLogger.error(message, args);
        }
    }

    public void debug(String message, Object... args) {
        if (slf4jLogger.isDebugEnabled()) {
            slf4jLogger.debug(message, args);
        }
    }
}