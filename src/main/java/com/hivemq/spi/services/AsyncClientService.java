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
 * @since 3.1
 */
public interface AsyncClientService {

    /**
     * Returns all identifiers of connected clients of this HiveMQ Node. You won't receive client identifiers of connected
     * clients from other HiveMQ nodes if HiveMQ runs in a cluster.
     * <p/>
     * If you have many client connections, please note that calling this method frequently could have negative performance
     * effects.
     *
     * @return a {@link com.google.common.util.concurrent.ListenableFuture} which contains all client identifiers of all connected clients
     */
    ListenableFuture<Set<String>> getLocalConnectedClients();

    /**
     * Returns all disconnected clients which have a persistent MQTT session on this instance of HiveMQ (MQTT clean session=false).
     * <p/>
     * Disconnected MQTT clients which don't have a persistent session won't be returned by this method
     *
     * @return a {@link com.google.common.util.concurrent.ListenableFuture} which contains the client identifiers of all disconnected clients with a persistent MQTT session
     */
    ListenableFuture<Set<String>> getLocalDisconnectedClients();

    /**
     * Check if a client with a given identifier is currently connected to this HiveMQ instance.
     *
     * @param clientId client, which should be checked
     * @return a {@link com.google.common.util.concurrent.ListenableFuture} which contains true, if a certain client is currently connected and false otherwise
     */
    ListenableFuture<Boolean> isClientConnectedLocal(String clientId);

    /**
     * Returns client information for clients that are connected to this broker instance.
     * <p/>
     * If the client isn't connected, you will receive an {@link Optional} with absent data.
     *
     * @param clientId the client identifier of the client
     * @return a {@link com.google.common.util.concurrent.ListenableFuture} which contains the {@link ClientData} for a specific client.
     */
    ListenableFuture<ClientData> getLocalClientData(String clientId);

    /**
     * Returns all identifiers of connected clients of this HiveMQ instance and all other nodes in a HiveMQ cluster
     * <p/>
     * Calling this method frequently in a clustered environment could have negative performance effects.
     *
     * @return a {@link com.google.common.util.concurrent.ListenableFuture} which contains the client identifiers of all connected clients
     */
    ListenableFuture<Set<String>> getConnectedClients();

    /**
     * Returns all disconnected clients which have a persistent MQTT session on this broker or any other cluster node.
     * <p/>
     * Disconnected MQTT clients which don't have a persistent session won't be returned by this method
     *
     * @return a {@link com.google.common.util.concurrent.ListenableFuture} which contains the of client identifiers of all disconnected clients with a persistent MQTT session
     */
    ListenableFuture<Set<String>> getDisconnectedClients();

    /**
     * Check if a client with a given identifier is currently connected to this HiveMQ broker instance or any other instance in the cluster.
     *
     * @param clientId client, which should be checked
     * @return a {@link com.google.common.util.concurrent.ListenableFuture} which contains true, if a certain client is currently connected and false otherwise
     */
    ListenableFuture<Boolean> isClientConnected(String clientId);

    /**
     * Returns additional client information about a given client with a given client identifier.
     * <p/>
     * This method will also get client information from other cluster nodes if needed.
     * <p/>
     * If the client isn't connected, you will receive an {@link Optional} with absent data.
     *
     * @param clientId the client identifier of the client
     * @return a {@link com.google.common.util.concurrent.ListenableFuture} which contains the {@link ClientData} for a specific client.
     */
    ListenableFuture<ClientData> getClientData(String clientId);

    /**
     * Forcefully disconnect a client with the specified clientId.
     *
     * @param clientId the clientId to disconnect
     * @return a {@link com.google.common.util.concurrent.ListenableFuture} which contains a {@link Boolean} which is true when the client has been disconnected and false if no client with that id was found.
     * @since 3.2
     */
    ListenableFuture<Boolean> disconnectClient(String clientId);

    /**
     * Forcefully disconnect a client with the specified clientId.
     * <p>
     * If the client specified a LWT message it will only be sent if the boolean parameter is set to false.
     *
     * @param clientId          the clientId to disconnect
     * @param preventLwtMessage if true the LWT message for this client is not published when the client gets disconnected
     * @return a {@link com.google.common.util.concurrent.ListenableFuture} which contains a {@link Boolean} which is true when the client has been disconnected and false if no client with that id was found.
     * @since 3.2
     */
    ListenableFuture<Boolean> disconnectClient(String clientId, boolean preventLwtMessage);
}
