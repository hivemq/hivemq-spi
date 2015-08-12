/*
 * Copyright 2014 dc-square GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hivemq.spi.services;

import com.google.common.util.concurrent.ListenableFuture;
import com.hivemq.spi.security.ClientData;
import com.google.common.base.Optional;

import java.util.Set;

/**
 * Through this client service a plugin can query details about connected or disconnected clients (with a persistent session) from the HiveMQ core.
 *
 * @author Lukas Brandl
 * @since 1.5
 */
public interface ClientService {

    /**
     * Returns all identifiers of connected clients of this HiveMQ Node. You won't receive client identifiers of connected
     * clients from other HiveMQ nodes if HiveMQ runs in a cluster.
     * <p/>
     * If you have many client connections, please not that calling this method frequently could have negative performance
     * effects
     *
     * @return client identifiers of all connected clients
     */
    public Set<String> getLocalConnectedClients();

    /**
     * Returns all disconnected clients which have a persistent MQTT session (MQTT cleanSession=false).
     * <p/>
     * Disconnected MQTT clients which don't have a persistent Session won't be returned by this method
     *
     * @return all disconnected clients with a persistent MQTT session
     */
    public Set<String> getLocalDisconnectedClients();

    /**
     * Checks if a client with a given identifier is currently connected to the HiveMQ broker.
     *
     * @param clientId client, which should be checked
     * @return true, if a certain client is currently connected and false otherwise
     */
    public boolean isClientConnectedLocal(String clientId);

    /**
     * Returns additional client information about a given client with a given client identifier.
     * <p/>
     * If the client isn't connected, you will receive an {@link Optional} with absent data.
     *
     * @param clientId the client identifier of the client
     * @return {@link ClientData} for a specific client.
     */
    public Optional<ClientData> getLocalClientDataForClientId(String clientId);

    ListenableFuture<Set<String>> getConnectedClients();

    ListenableFuture<Set<String>> getDisconnectedClients();

    ListenableFuture<Boolean> isClientConnected(final String clientId);

    ListenableFuture<Optional<ClientData>> getClientDataForClientId(final String clientId);

    ListenableFuture<Set<String>> getConnectedClients(final long timeout);

    ListenableFuture<Set<String>> getDisconnectedClients(final long timeout);

    ListenableFuture<Boolean> isClientConnected(final String clientId, final long timeout) throws IllegalStateException;

    ListenableFuture<Optional<ClientData>> getClientDataForClientId(final String clientId, final long timeout);

}
