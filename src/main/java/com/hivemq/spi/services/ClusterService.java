package com.hivemq.spi.services;

import java.util.Set;

/**
 * @author Lukas Brandl
 */
public interface ClusterService {

    /**
     * @return The cluster id of this HiveMQ instance.
     */
    String getClusterId();

    /**
     * @return The cluster id of all HiveMQ instance in the cluster.
     */
    Set<String> getAllClusterIds();

    /**
     * @return <code>true</code> if the cluster enabled config is set to <code>true</code>.
     */
    boolean isClusterEnabled();
}
