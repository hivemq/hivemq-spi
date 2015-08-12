package com.hivemq.spi.services.rest.listener;

import com.hivemq.spi.annotations.NotNull;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A base implementation for listeners with common listener
 * base properties
 *
 * @author Dominik Obermaier
 */
abstract class AbstractListener implements Listener {

    private final String name;
    private final String bindAddress;
    private final int port;


    AbstractListener(@NotNull final String name, @NotNull final String bindAddress, final int port) {
        checkNotNull(name, "Name of the listener must not be null");
        checkNotNull(bindAddress, "Bind address of the listener must not be null");
        checkArgument(port <= 65535 && port > 0, " The port %s is not a valid port. Valid ports are between 1 and 65535", port);
        this.name = name;
        this.bindAddress = bindAddress;
        this.port = port;
    }


    /**
     * Returns the unique name of the listener
     *
     * @return the unique name of the listener
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the bind address the listener is bound to
     *
     * @return the the bind address
     */
    public String getBindAddress() {
        return bindAddress;
    }

    /**
     * Returns the port the listener is bound to
     *
     * @return the port of the listener
     */
    public int getPort() {
        return port;
    }

}
