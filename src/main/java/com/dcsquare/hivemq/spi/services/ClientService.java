package com.dcsquare.hivemq.spi.services;

import com.dcsquare.hivemq.spi.security.ClientData;
import com.google.common.base.Optional;

import java.util.Set;

/**
 * Through this client service a plugin can query details about connected or disconnected clients from the HiveMQ core.
 *
 * @author Lukas Brandl
 * @since 1.5
 */
public interface ClientService {

    /**
     * @return all client identifiers of all connected clients
     */
    public Set<String> getConnectedClients();

    /**
     * @return all disconnected clients, which are subscribed with quality of service 1 or 2
     */
    public Set<String> getDisconnectedClients();

    /**
     * @param clientId client, which should be checked
     * @return true, if a certain client is currently connected and false otherwise
     */
    public boolean isClientConnected(String clientId);

    /**
     * This is only possible if the client is connected
     * @param clientId client
     * @return a ClientData object, which holds more information of a specific client.
     */
    public Optional<ClientData> getClientDataForClientId(String clientId);

}
