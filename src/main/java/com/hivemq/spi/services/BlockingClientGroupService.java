/*
 * Copyright 2017 dc-square GmbH
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

import com.google.common.collect.ImmutableSet;
import com.hivemq.spi.annotations.NotNull;
import com.hivemq.spi.security.ClientData;
import com.hivemq.spi.services.exception.IncompatibleHiveMQVersionException;
import com.hivemq.spi.services.exception.NoSuchClientIdException;

/**
 * Through this client group service a plugin can add clients to groups, remove clients from groups, query groups and query clients belonging to a group.
 * <p>
 * The client group service is only available for clients which already have a session available.
 * When a client connects, the earliest point in time to add the client to a group is the {@link com.hivemq.spi.callback.events.OnSessionReadyCallback}.
 * <p>
 * The client group service is only available when all nodes in the HiveMQ cluster are at version 3.3.0 or above.
 *
 * @author Silvio Giebl
 * @since 3.3.0
 */
public interface BlockingClientGroupService {

    /**
     * Adds a client to a group.
     *
     * @param group      the group the client will be added to.
     * @param clientData the clientData of the client which will be added to the group.
     * @throws NoSuchClientIdException            if no session for the client to the given clientData exists.
     * @throws IncompatibleHiveMQVersionException if a node with a version lower than 3.3.0 exists in the cluster.
     */
    void addClientToGroup(@NotNull String group, @NotNull ClientData clientData) throws NoSuchClientIdException;

    /**
     * Adds a client to a group.
     *
     * @param group            the group the client will be added to.
     * @param clientIdentifier the identifier of the client which will be added to the group.
     * @throws NoSuchClientIdException            if no session for the client with the given identifier exists.
     * @throws IncompatibleHiveMQVersionException if a node with a version lower than 3.3.0 exists in the cluster.
     */
    void addClientToGroup(@NotNull String group, @NotNull String clientIdentifier) throws NoSuchClientIdException;

    /**
     * Removes a client from a group.
     *
     * @param group            the group the client will be removed from.
     * @param clientIdentifier the identifier of the client which will be added to the group.
     * @throws IncompatibleHiveMQVersionException if a node with a version lower than 3.3.0 exists in the cluster.
     */
    void removeClientFromGroup(@NotNull String group, @NotNull String clientIdentifier);

    /**
     * Retrieves the clients for a group.
     *
     * @param group the group the clients will be retrieved of.
     * @return the clients belonging to the given group.
     * @throws IncompatibleHiveMQVersionException if a node with a version lower than 3.3.0 exists in the cluster.
     */
    @NotNull
    ImmutableSet<String> getClientsForGroup(@NotNull String group);

    /**
     * Retrieves all available groups.
     *
     * @return all available groups.
     * @throws IncompatibleHiveMQVersionException if a node with a version lower than 3.3.0 exists in the cluster.
     */
    @NotNull
    ImmutableSet<String> getAvailableGroups();

}
