package com.epam.patient.exception;

public class FillingException extends Exception {
    public FillingException() {
        super();
    }

    public FillingException(String message) {
        super(message);
    }

    public FillingException(String message, Throwable cause) {
        super(message, cause);
    }

    public FillingException(Throwable cause) {
        super(cause);
    }
}
