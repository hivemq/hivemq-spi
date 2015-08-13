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

import com.google.common.base.Optional;
import com.google.common.util.concurrent.ListenableFuture;
import com.hivemq.spi.security.ClientData;

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
     * effects.
     *
     * @return client identifiers of all connected clients
     */
    public Set<String> getLocalConnectedClients();

    /**
     * Returns all disconnected clients which have a persistent MQTT session on this instance of HiveMQ (MQTT cleanSession=false).
     * <p/>
     * Disconnected MQTT clients which don't have a persistent Session won't be returned by this method
     *
     * @return all disconnected clients with a persistent MQTT session
     */
    public Set<String> getLocalDisconnectedClients();

    /**
     * Check if a client with a given identifier is currently connected to this HiveMQ instance.
     *
     * @param clientId client, which should be checked
     * @return true, if a certain client is currently connected and false otherwise
     */
    public boolean isClientConnectedLocal(String clientId);

    /**
     * Returns additional client information about a given client with a given client identifier.
     * Only returns client information for client that are connected to this broker,
     * even if it is connected to a broker cluster.
     * <p/>
     * If the client isn't connected, you will receive an {@link Optional} with absent data.
     *
     * @param clientId the client identifier of the client
     * @return {@link ClientData} for a specific client.
     */
    public Optional<ClientData> getLocalClientDataForClientId(String clientId);

    /**
     * Returns all identifiers of connected clients of this HiveMQ and all other nodes. If the broker is connected to a cluster.
     * <p/>
     * If you have many client connections, please not that calling this method frequently could have negative performance
     * effects
     * A default timeout is used for the cluster request.
     *
     * @return client identifiers of all connected clients
     */
    ListenableFuture<Set<String>> getConnectedClients();

    /**
     * Returns all disconnected clients which have a persistent MQTT session (MQTT cleanSession=false).
     * <p/>
     * Disconnected MQTT clients which don't have a persistent Session won't be returned by this method
     * A default timeout is used for the cluster request.
     *
     * @return all disconnected clients with a persistent MQTT session
     */
    ListenableFuture<Set<String>> getDisconnectedClients();

    /**
     * Check if a client with a given identifier is currently connected to the HiveMQ broker or the cluster.
     * A default timeout is used for the cluster request.
     *
     * @param clientId client, which should be checked
     * @return true, if a certain client is currently connected and false otherwise
     */
    ListenableFuture<Boolean> isClientConnected(final String clientId);

    /**
     * Returns additional client information about a given client with a given client identifier.
     * <p/>
     * If the client isn't connected, you will receive an {@link Optional} with absent data.
     * A default timeout is used for the cluster request.
     *
     * @param clientId the client identifier of the client
     * @return {@link ClientData} for a specific client.
     */
    ListenableFuture<Optional<ClientData>> getClientDataForClientId(final String clientId);

    /**
     * Returns all identifiers of connected clients of this HiveMQ and all other nodes. If the broker is connected to a cluster.
     * <p/>
     * If you have many client connections, please not that calling this method frequently could have negative performance
     * effects.
     *
     * @param timeout The maximum time (in milliseconds) the broker will wait for the cluster response.
     *                If the timeout is exceeded, the future will fail.
     * @return client identifiers of all connected clients
     */
    ListenableFuture<Set<String>> getConnectedClients(final long timeout);

    /**
     * Returns all disconnected clients which have a persistent MQTT session (MQTT cleanSession=false).
     * <p/>
     * Disconnected MQTT clients which don't have a persistent Session won't be returned by this method
     *
     * @param timeout The maximum time (in milliseconds) the broker will wait for the cluster response.
     *                If the timeout is exceeded, the future will fail.
     * @return all disconnected clients with a persistent MQTT session
     */
    ListenableFuture<Set<String>> getDisconnectedClients(final long timeout);

    /**
     * Check if a client with a given identifier is currently connected to the HiveMQ broker or the cluster.
     *
     * @param clientId client, which should be checked
     * @param timeout  The maximum time (in milliseconds) the broker will wait for the cluster response.
     *                 If the timeout is exceeded, the future will fail.
     * @return true, if a certain client is currently connected and false otherwise
     */
    ListenableFuture<Boolean> isClientConnected(final String clientId, final long timeout) throws IllegalStateException;

    /**
     * Returns additional client information about a given client with a given client identifier.
     * <p/>
     * If the client isn't connected, you will receive an {@link Optional} with absent data.
     *
     * @param clientId the client identifier of the client
     * @param timeout  The maximum time (in milliseconds) the broker will wait for the cluster response.
     *                 If the timeout is exceeded, the future will fail.
     * @return {@link ClientData} for a specific client.
     */
    ListenableFuture<Optional<ClientData>> getClientDataForClientId(final String clientId, final long timeout);

}
