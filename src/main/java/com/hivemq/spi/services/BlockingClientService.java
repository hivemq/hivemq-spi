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

import com.hivemq.spi.annotations.Nullable;
import com.hivemq.spi.security.ClientData;
import com.hivemq.spi.services.exception.RateLimitExceededException;

import java.util.Set;

/**
 * Through this client service a plugin can query details about connected or disconnected clients (with a persistent session) from the HiveMQ core.
 *
 * @author Lukas Brandl
 * @since 3.1
 */
public interface BlockingClientService {

    /**
     * Returns all identifiers of connected clients of this HiveMQ Node. You won't receive client identifiers of connected
     * clients from other HiveMQ nodes if HiveMQ runs in a cluster.
     * <p/>
     * If you have many client connections, please note that calling this method frequently could have negative performance
     * effects.
     *
     * @return client identifiers of all connected clients
     * @throws RateLimitExceededException if the plugin service rate limit was exceeded.
     */
    Set<String> getLocalConnectedClients();

    /**
     * Returns all disconnected clients which have a persistent MQTT session on this instance of HiveMQ (MQTT clean session=false).
     * <p/>
     * Disconnected MQTT clients which don't have a persistent session won't be returned by this method
     *
     * @return all disconnected clients with a persistent MQTT session
     * @throws RateLimitExceededException if the plugin service rate limit was exceeded.
     */
    Set<String> getLocalDisconnectedClients();

    /**
     * Check if a client with a given identifier is currently connected to this HiveMQ instance.
     *
     * @param clientId client, which should be checked
     * @return true, if a certain client is currently connected and false otherwise
     * @throws RateLimitExceededException if the plugin service rate limit was exceeded.
     */
    boolean isClientConnectedLocal(String clientId);

    /**
     * Returns client information for clients that are connected to this broker instance.
     * <p/>
     * If the client isn't connected, <code>null</code> will be returned.
     *
     * @param clientId the client identifier of the client
     * @return {@link ClientData} for a specific client or <code>null</code> if the client is not connected
     * @throws RateLimitExceededException if the plugin service rate limit was exceeded.
     */
    @Nullable
    ClientData getLocalClientData(String clientId);

    /**
     * Returns all identifiers of connected clients of this HiveMQ instance and all other nodes in a HiveMQ cluster
     * <p/>
     * Calling this method frequently in a clustered environment could have negative performance effects.
     *
     * @return client identifiers of all connected clients
     * @throws RateLimitExceededException if the plugin service rate limit was exceeded.
     */
    Set<String> getConnectedClients();

    /**
     * Returns all disconnected clients which have a persistent MQTT session on this broker or any other cluster node.
     * <p/>
     * Disconnected MQTT clients which don't have a persistent session won't be returned by this method
     *
     * @return all disconnected clients with a persistent MQTT session
     * @throws RateLimitExceededException if the plugin service rate limit was exceeded.
     */
    Set<String> getDisconnectedClients();

    /**
     * Check if a client with a given identifier is currently connected to this HiveMQ broker instance or any other instance in the cluster.
     *
     * @param clientId client, which should be checked
     * @return true, if a certain client is currently connected and false otherwise
     * @throws RateLimitExceededException if the plugin service rate limit was exceeded.
     */
    boolean isClientConnected(String clientId);

    /**
     * Returns additional client information about a given client with a given client identifier.
     * <p/>
     * This method will also get client information from other cluster nodes if needed.
     * <p/>
     * If the client isn't connected, <code>null</code> will be returned.
     *
     * @param clientId the client identifier of the client
     * @return {@link ClientData} for a specific client or <code>null</code> if the client is not connected
     * @throws RateLimitExceededException if the plugin service rate limit was exceeded.
     */
    @Nullable
    ClientData getClientData(String clientId);

    /**
     * Forcefully disconnect a client with the specified clientId.
     * <p>
     * If the client specified a LWT message, it will be sent.
     * To prevent LWT messages use the {@link #disconnectClient(String, boolean)} method
     *
     * @param clientId the clientId to disconnect
     * @return true if the client has been disconnected, false if no client with that id was found
     * @throws RateLimitExceededException if the plugin service rate limit was exceeded.
     * @since 3.2
     */
    boolean disconnectClient(String clientId);

    /**
     * Forcefully disconnect a client with the specified clientId.
     * <p>
     * If the client specified a LWT message, it will only be sent if the boolean parameter is false
     *
     * @param clientId          the clientId to disconnect
     * @param preventLwtMessage if true the LWT message for this client is not sent
     * @return true if the client has been disconnected, false if no client with that id was found
     * @throws RateLimitExceededException if the plugin service rate limit was exceeded.
     * @since 3.2
     */
    boolean disconnectClient(String clientId, boolean preventLwtMessage);
}
