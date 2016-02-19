package com.hivemq.spi.callback.cluster;

import com.google.common.util.concurrent.ListenableFuture;
import com.hivemq.spi.callback.AsynchronousCallback;

import java.util.List;

/**
 * This callback is meant to regularly discover the addresses of all available HiveMQ cluster nodes.
 * <p/>
 * Notice: It is used only if cluster is enabled and discovery is set to "plugin" in the HiveMQ config file.
 *
 * @author Christoph Schäbel
 * @since 3.1
 */
public interface ClusterDiscoveryCallback extends AsynchronousCallback {

    /**
     * Gets called before the HiveMQ instance starts looking for other HiveMQ instances to form a cluster with.
     * <p/>
     * This method can be used to register this HiveMQ instance with some kind of central registry or save it to a
     * database or file server for example.
     *
     * @param clusterId  this HiveMQ instance's unique cluster node ID
     * @param ownAddress this HiveMQ instance's address
     */
    void init(String clusterId, ClusterNodeAddress ownAddress);

    /**
     * Gets called every X seconds by HiveMQ to discover all available cluster nodes.
     * <p/>
     * The interval at which this method is called can be configured in the HiveMQ config file.
     *
     * @return a list of all the cluster node's addresses
     */
    ListenableFuture<List<ClusterNodeAddress>> getNodeAddresses();

    /**
     * Gets called when the cluster is shut down.
     * <p/>
     * This method can be used to unregister this HiveMQ instance from a central registry.
     */
    void destroy();
}
