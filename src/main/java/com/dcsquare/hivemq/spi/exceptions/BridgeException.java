package com.dcsquare.hivemq.spi.exceptions;

/**
 * An exception which indicates that a bridge error occurred or a bridge configuration
 * was invalid
 *
 * @author Dominik Obermaier
 * @since 2.0
 */
public class BridgeException extends RuntimeException {
    public BridgeException(final String message) {
        super(message);
    }

    public BridgeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public BridgeException(Throwable cause) {
        super(cause);
    }

    protected BridgeException(final String message, final Throwable cause, final boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
