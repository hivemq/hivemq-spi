package com.hivemq.spi.callback.exception;

/**
 * Indicates that the broker should stop immediately because something in its current
 * state prevents a safe operation.
 * <p/>
 * This exception should be used in an implementation of
 * {@link com.hivemq.spi.callback.events.broker.OnBrokerReady}
 *
 * @author Georg Held
 * @since 3.3
 */
public class IllegalBrokerStateException extends Exception {

    public IllegalBrokerStateException() {
    }

    public IllegalBrokerStateException(final String message) {
        super(message);
    }

    public IllegalBrokerStateException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public IllegalBrokerStateException(final Throwable cause) {
        super(cause);
    }
}
