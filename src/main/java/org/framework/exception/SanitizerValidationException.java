package org.framework.exception;

public class SanitizerValidationException extends RuntimeException {
    public SanitizerValidationException(String message) {
        super(message);
    }
}
