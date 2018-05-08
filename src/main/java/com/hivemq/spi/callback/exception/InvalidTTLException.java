package com.hivemq.spi.callback.exception;


/**
 * An exception which is thrown when the time to live was invalid (< -1).
 *
 * @author Waldemar Ruck
 * @since 3.4
 */
public class InvalidTTLException  extends RuntimeException {

    public InvalidTTLException() {
        super();
    }

    public InvalidTTLException(final String message) {
        super(message);
    }

    public InvalidTTLException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidTTLException(final Throwable cause) {
        super(cause);
    }
}
