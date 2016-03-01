package com.hivemq.spi.callback.cluster;

import com.hivemq.spi.annotations.Immutable;

/**
 * Represents a cluster node's address
 *
 * @author Christoph Schäbel
 * @since 3.1
 */
@Immutable
public class ClusterNodeAddress {

    private final String host;
    private final int port;

    /**
     * @param host the hostname or IP of the cluster node
     * @param port the cluster bind-port of the cluster node
     */
    public ClusterNodeAddress(final String host, final int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
