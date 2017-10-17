package com.hivemq.spi.callback.exception;

/**
 * This exception is used to signal in the {@link com.hivemq.spi.callback.events.BeforePublishSendCallback} that the PUBLISH should not be sent.
 *
 * @author Georg Held
 * @since 3.3
 */
public class BeforePublishSendException extends Exception {

    /**
     * This is a helper constant for a {@link BeforePublishSendException}.
     */
    private static BeforePublishSendException INSTANCE = new BeforePublishSendException();

    /**
     * Use this method if you need a {@link BeforePublishSendException}, it is more performant than the usage of a Constructor.
     *
     * @return a singleton {@link BeforePublishSendException} exception
     */
    public static BeforePublishSendException getInstance() {
        return INSTANCE;
    }
}
