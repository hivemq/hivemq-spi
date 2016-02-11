package com.hivemq.spi.services;

import com.google.common.base.Optional;
import com.hivemq.spi.security.ClientData;

import java.util.Set;

/**
 * Through this client service a plugin can query details about connected or disconnected clients (with a persistent session) from the HiveMQ core.
 *
 * @author Lukas Brandl
 * @since 3.1
 */
public interface ClusterServiceLocal {

    /**
     * Returns all identifiers of connected clients of this HiveMQ Node. You won't receive client identifiers of connected
     * clients from other HiveMQ nodes if HiveMQ runs in a cluster.
     * <p/>
     * If you have many client connections, please not that calling this method frequently could have negative performance
     * effects.
     *
     * @return client identifiers of all connected clients
     */
    Set<String> getConnectedClients();

    /**
     * Returns all disconnected clients which have a persistent MQTT session on this instance of HiveMQ (MQTT clean session=false).
     * <p/>
     * Disconnected MQTT clients which don't have a persistent session won't be returned by this method
     *
     * @return all disconnected clients with a persistent MQTT session
     */
    Set<String> getDisconnectedClients();

    /**
     * Check if a client with a given identifier is currently connected to this HiveMQ instance.
     *
     * @param clientId client, which should be checked
     * @return true, if a certain client is currently connected and false otherwise
     */
    boolean isClientConnected(String clientId);

    /**
     * Returns client information for clients that are connected to this broker instance.
     * <p/>
     * If the client isn't connected, you will receive an {@link Optional} with absent data.
     *
     * @param clientId the client identifier of the client
     * @return {@link ClientData} for a specific client.
     */
    ClientData getClientData(String clientId);
}
