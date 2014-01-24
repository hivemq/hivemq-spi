package com.dcsquare.hivemq.spi.services;

import com.dcsquare.hivemq.spi.security.ClientData;
import com.google.common.base.Optional;

import java.util.Set;

/**
 * @author Lukas Brandl
 */
public interface ClientService {

    /**
     * @return all client identifiers of all connected clients
     */
    public Set<String> getConnectedClients();

    public Set<String> getDisconnectedClients();

    public boolean isClientConnected(String clientId);

    public Optional<ClientData> getClientDataForClientId(String clientId);

}
