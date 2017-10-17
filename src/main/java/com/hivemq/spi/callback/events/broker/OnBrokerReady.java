package com.hivemq.spi.callback.events.broker;

import com.hivemq.spi.callback.SynchronousCallback;
import com.hivemq.spi.callback.exception.IllegalBrokerStateException;

import java.util.List;

/**
 * *This callback gets called once the broker is started, after network interfaces are bound
 * and after a possible cluster join was executed.
 * <p/>
 * The broker will suspend all incoming client connections until the last executed OnBrokerReady callback returns.
 * <p/>
 * To signal HiveMQ, that a immediate shutdown is necessary, throw a {@link IllegalBrokerStateException}
 *
 * @author Georg Held
 * @since 3.3
 */
public interface OnBrokerReady extends SynchronousCallback {

    /**
     * Called, once the broker is ready
     *
     * @throws IllegalBrokerStateException if the broker is in a non recoverable state
     */
    public void onBrokerReady() throws IllegalBrokerStateException;
}

