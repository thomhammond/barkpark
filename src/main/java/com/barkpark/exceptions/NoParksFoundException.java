package com.barkpark.exceptions;

public class NoParksFoundException extends RuntimeException {
    private static final long serialVersionUID = -3995937675384209029L;

    /**
     * Exception with no message or cause.
     */
    public NoParksFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public NoParksFoundException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public NoParksFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public NoParksFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
