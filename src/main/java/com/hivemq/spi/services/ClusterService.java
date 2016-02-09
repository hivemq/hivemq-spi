package com.hivemq.spi.services;

import java.util.Set;

/**
 * @author Lukas Brandl
 */
public interface ClusterService {

    /**
     * Returns the cluster id of this HiveMQ instance. If HiveMQ is not connected to a cluster, this method will return
     * <code>null</code>.
     *
     * @return The cluster id of this HiveMQ instance.
     */
    String getClusterId();

    /**
     * Returns the cluster id of all HiveMQ instance in the cluster. If HiveMQ is not connected to a cluster, this method will return
     * <code>null</code>.
     *
     * @return The cluster id of all HiveMQ instance in the cluster.
     */
    Set<String> getAllClusterIds();
}
