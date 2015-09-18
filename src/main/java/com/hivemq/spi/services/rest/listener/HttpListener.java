package com.hivemq.spi.services.rest.listener;

import com.hivemq.spi.annotations.Immutable;
import com.hivemq.spi.annotations.NotNull;

/**
 * A HTTP listener which is bound to a specific host and port.
 *
 * @author Dominik Obermaier
 */
@Immutable
public class HttpListener extends AbstractListener {


    /**
     * Creates a new HTTP listener.
     * <p/>
     * In order to bind the HTTP listener to all interfaces use the host '0.0.0.0'
     *
     * @param name        The unique identifier of the listener.
     * @param bindAddress the bind address the listener should be bound to. Must be a valid IP or hostname.
     * @param port        the port the listener should be bound to. Must be between 1 and 65535.
     * @throws NullPointerException     if name or host are null
     * @throws IllegalArgumentException if the port is not valid
     */
    public HttpListener(@NotNull final String name, @NotNull final String bindAddress, final int port) {
        super(name, bindAddress, port);
    }

}
