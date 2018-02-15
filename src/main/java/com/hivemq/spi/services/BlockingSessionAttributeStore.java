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

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.hivemq.spi.annotations.NotNull;
import com.hivemq.spi.services.exception.IncompatibleHiveMQVersionException;
import com.hivemq.spi.services.exception.NoSuchClientIdException;
import com.hivemq.spi.services.exception.RateLimitExceededException;

import java.nio.charset.Charset;

/**
 * Through this service a plugin can manage client session attributes with the same lifetime as the MQTT client session.
 * <p>
 * The session attribute store is only available for clients which already have a session available.
 * When a client connects, the earliest point in time to use the session attribute store for the client is the {@link com.hivemq.spi.callback.events.OnSessionReadyCallback}.
 * <p>
 * The session attribute store is only available when all nodes in the HiveMQ cluster are at version 3.3.0 or above.
 *
 * @author Georg Held
 * @since 3.3.0
 */
public interface BlockingSessionAttributeStore {

    /**
     * Sets the given session attribute for a persistent client.
     *
     * @param clientId the clientId of a persistent client.
     * @param key      the key of the session attribute.
     * @param value    the value of the session attribute.
     * @throws NoSuchClientIdException if no session for the given clientId exists.
     * @throws IncompatibleHiveMQVersionException if a node with a version lower than 3.3.0 exists in the cluster.
     * @throws RateLimitExceededException if the plugin service rate limit was exceeded.
     */
    void put(@NotNull String clientId, @NotNull String key, @NotNull byte[] value) throws NoSuchClientIdException;

    /**
     * Sets the given session attribute for a persistent client.
     *
     * @param clientId the clientId of a persistent client.
     * @param key      the key of the session attribute.
     * @param value    the value of the session attribute as a string.
     * @throws NoSuchClientIdException if no session for the given clientId exists.
     * @throws IncompatibleHiveMQVersionException if a node with a version lower than 3.3.0 exists in the cluster.
     * @throws RateLimitExceededException if the plugin service rate limit was exceeded.
     */
    void putAsString(@NotNull String clientId, @NotNull String key, @NotNull String value) throws NoSuchClientIdException;

    /**
     * Sets the given session attribute for a persistent client.
     *
     * @param clientId the clientId of a persistent client.
     * @param key      the key of the session attribute.
     * @param value    the value of the session attribute as a string.
     * @param charset  the {@link Charset} of the given value.
     * @throws NoSuchClientIdException if no session for the given clientId exists.
     * @throws IncompatibleHiveMQVersionException if a node with a version lower than 3.3.0 exists in the cluster.
     * @throws RateLimitExceededException if the plugin service rate limit was exceeded.
     */
    void putAsString(@NotNull String clientId, @NotNull String key, @NotNull String value, @NotNull Charset charset) throws NoSuchClientIdException;

    /**
     * Retrieves the value of the session attribute with the given key for a persistent client.
     *
     * @param clientId the clientId of a persistent client.
     * @param key      the key of the session attribute.
     * @return an {@link Optional} containing the value of the session attribute if present.
     * @throws NoSuchClientIdException if no session for the given clientId exists.
     * @throws IncompatibleHiveMQVersionException if a node with a version lower than 3.3.0 exists in the cluster.
     * @throws RateLimitExceededException if the plugin service rate limit was exceeded.
     */
    @NotNull
    Optional<byte[]> get(@NotNull String clientId, @NotNull String key) throws NoSuchClientIdException;

    /**
     * Retrieves the value of the session attribute with the given key for a persistent client.
     *
     * @param clientId the clientId of a persistent client.
     * @param key      the key of the session attribute.
     * @return an {@link Optional} containing the value of the session attribute if present.
     * @throws NoSuchClientIdException if no session for the given clientId exists.
     * @throws IncompatibleHiveMQVersionException if a node with a version lower than 3.3.0 exists in the cluster.
     * @throws RateLimitExceededException if the plugin service rate limit was exceeded.
     */
    @NotNull
    Optional<String> getAsString(@NotNull String clientId, @NotNull String key) throws NoSuchClientIdException;

    /**
     * Retrieves the value of the session attribute with the given key for a persistent client.
     *
     * @param clientId the clientId of a persistent client.
     * @param key      the key of the session attribute.
     * @param charset  the {@link Charset} of the given value.
     * @return an {@link Optional} containing the value of the session attribute if present.
     * @throws NoSuchClientIdException if no session for the given clientId exists.
     * @throws IncompatibleHiveMQVersionException if a node with a version lower than 3.3.0 exists in the cluster.
     * @throws RateLimitExceededException if the plugin service rate limit was exceeded.
     */
    @NotNull
    Optional<String> getAsString(@NotNull String clientId, @NotNull String key, @NotNull Charset charset) throws NoSuchClientIdException;

    /**
     * Removes the session attribute with the given key for a persistent client.
     *
     * @param clientId the clientId of a persistent client.
     * @param key      the key of the session attribute.
     * @return an {@link Optional} containing the value of the removed session attribute if it was present.
     * @throws NoSuchClientIdException if no session for the given clientId exists.
     * @throws IncompatibleHiveMQVersionException if a node with a version lower than 3.3.0 exists in the cluster.
     * @throws RateLimitExceededException if the plugin service rate limit was exceeded.
     */
    @NotNull
    Optional<byte[]> remove(@NotNull String clientId, @NotNull String key) throws NoSuchClientIdException;

    /**
     * Retrieves all session attributes for a persistent client.
     *
     * @param clientId the clientId of a persistent client.
     * @return a Future of an {@link Optional} containing all session attributes as a map of key and value pairs if present.
     * @throws NoSuchClientIdException if no session for the given clientId exists.
     * @throws IncompatibleHiveMQVersionException if a node with a version lower than 3.3.0 exists in the cluster.
     * @throws RateLimitExceededException if the plugin service rate limit was exceeded.
     */
    @NotNull
    Optional<ImmutableMap<String, byte[]>> getAll(@NotNull String clientId) throws NoSuchClientIdException;

    /**
     * Clears all session attributes for the connected client.
     *
     * @param clientId the clientId of a persistent client.
     * @throws NoSuchClientIdException if no session for the given clientId exists.
     * @throws IncompatibleHiveMQVersionException if a node with a version lower than 3.3.0 exists in the cluster.
     * @throws RateLimitExceededException if the plugin service rate limit was exceeded.
     */
    void clear(@NotNull String clientId) throws NoSuchClientIdException;
}
