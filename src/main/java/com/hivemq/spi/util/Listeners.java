package com.hivemq.spi.util;

import com.hivemq.spi.services.configuration.entity.Listener;
import com.hivemq.spi.services.configuration.entity.TlsTcpListener;
import com.hivemq.spi.services.configuration.entity.TlsWebsocketListener;
import com.hivemq.spi.services.configuration.entity.WebsocketListener;

/**
 * Utilities for working with {@link com.hivemq.spi.services.configuration.entity.Listener}s
 *
 * @author Dominik Obermaier
 */
public class Listeners {

    private Listeners() {
        //Util, don't instantiate
    }

    /**
     * Checks if a given {@link Listener} uses TLS.
     *
     * @param listener the listener
     * @return <code>true</code> if the listener is a {@link TlsTcpListener} or a {@link TlsWebsocketListener}
     */
    public static boolean isSecure(final Listener listener) {
        return listener instanceof TlsTcpListener ||
                listener instanceof TlsWebsocketListener;
    }

    /**
     * Checks if a given {@link Listener} is a websocket listener
     *
     * @param listener the listener
     * @return <code>true</code> if the listener is a websocket listener
     */
    public static boolean isWebsocket(final Listener listener) {
        return listener instanceof TlsWebsocketListener ||
                listener instanceof WebsocketListener;
    }

}
