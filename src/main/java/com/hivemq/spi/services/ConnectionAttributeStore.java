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
import com.hivemq.spi.annotations.ThreadSafe;
import com.hivemq.spi.callback.exception.LimitExceededException;

import java.nio.charset.Charset;

/**
 * Through this service a plugin can manage client connection attributes with the same lifetime as the connection.
 * <p>
 * The ConnectionAttributeStore is a key-value store for storing arbitrary data as additional information
 * within the MQTT client connection. All data is stored in-memory and the maximum amount of a single key-value
 * pair is 10 kilobytes.
 * <p>
 * A Connection Attribute is arbitrary binary data. For convenience purposes methods like {@link #putAsString(String, String)}
 * are available in case String representations should be stored. If complex objects are desired to be stored in
 * the ConnectionAttributeStore, manual serialization and deserialization must be implemented by the plugin developer.
 * <p>
 * The ConnectionAttributeStore is useful for storing temporary data or data that needs to be cleaned up automatically after
 * the MQTT client disconnected. This store is also useful for storing temporary infomation that needs to be shared across
 * callbacks.
 * <p>
 * For data that needs to be stored for the lifetime of a MQTT session (in case of persistent sessions), the
 * {@link AsyncSessionAttributeStore} may be used as an alternative.
 * <p>
 * IMPORTANT: The store is not necessarily available if the {@link com.hivemq.spi.security.ClientData instance} was
 * not acquired via a {@link com.hivemq.spi.callback.Callback} parameter.
 * If you e.g. receive a {@link com.hivemq.spi.security.ClientData} object from
 * calling {@link AsyncClientService#getClientData(String)}, it's required to call {@link #isAccessible()} before
 * using the ConnectionAttributeStore.
 * <p>
 * The ConnectionAttributeStore is thread safe.
 *
 * @author Silvio Giebl
 * @since 3.3.0
 */
@ThreadSafe
public interface ConnectionAttributeStore {

    /**
     * Sets the given connection attribute for the connected client.
     *
     * @param key   the key of the connection attribute.
     * @param value the value of the connection attribute.
     * @throws LimitExceededException a {@link LimitExceededException} is thrown when the size of the passed value exceeds the maximum allowed size of 10 kilobytes for the value
     */
    void put(@NotNull String key, @NotNull byte[] value);

    /**
     * Sets the given connection attribute as UTF-8 String representation for the connected client.
     *
     * @param key   the key of the connection attribute.
     * @param value the value of the connection attribute as a string.
     * @throws LimitExceededException a {@link LimitExceededException} is thrown when the size of the passed value exceeds the maximum allowed size of 10 kilobytes for the value
     */
    void putAsString(@NotNull String key, @NotNull String value);

    /**
     * Sets the given connection attribute as String representation for the connected client with a given charset.
     *
     * @param key     the key of the connection attribute.
     * @param value   the value of the connection attribute as a string with the given charset.
     * @param charset the {@link Charset} of the given value.
     * @throws LimitExceededException a {@link LimitExceededException} is thrown when the size of the passed value exceeds the maximum allowed size of 10 kilobytes for the value
     */
    void putAsString(@NotNull String key, @NotNull String value, @NotNull Charset charset);

    /**
     * Retrieves the value of the connection attribute with the given key for the connected client.
     *
     * @param key the key of the connection attribute.
     * @return an {@link Optional} containing the value of the connection attribute if present.
     */
    @NotNull
    Optional<byte[]> get(@NotNull String key);

    /**
     * Retrieves the value of the connection attribute with the given key for the connected client as UTF-8 string.
     *
     * @param key the key of the connection attribute.
     * @return an {@link Optional} containing the value of the connection attribute as a string if present.
     */
    @NotNull
    Optional<String> getAsString(@NotNull String key);

    /**
     * Retrieves the value of the connection attribute with the given key for the connected client as string with the given charset.
     *
     * @param key     the key of the connection attribute.
     * @param charset the {@link Charset} of the value of the connection attribute.
     * @return an {@link Optional} containing the value of the connection attribute as a string with the given charset if present.
     */
    @NotNull
    Optional<String> getAsString(@NotNull String key, @NotNull Charset charset);

    /**
     * Retrieves all connection attributes for the connected client.
     *
     * @return an {@link Optional} containing all connection attributes as a map of key and value pairs if present.
     */
    @NotNull
    Optional<ImmutableMap<String, byte[]>> getAll();

    /**
     * Removes the connection attribute with the given key for the connected client.
     *
     * @param key the key of the connection attribute.
     * @return an {@link Optional} containing the value of the removed connection attribute if it was present.
     */
    @NotNull
    Optional<byte[]> remove(@NotNull String key);

    /**
     * Clears all connection attributes for the connected client.
     */
    void clear();

    /**
     * Returns whether this connection attribute store is accessible.
     * The connection attribute store can only be accessed on the HiveMQ node, which the client is connected to.
     * A cluster-wide alternative is the {@link AsyncSessionAttributeStore}.
     * <p>
     * The ConnectionAttributeStore is not necessarily available if the {@link com.hivemq.spi.security.ClientData instance} was
     * not acquired via a {@link com.hivemq.spi.callback.Callback} parameter.
     * If you e.g. receive a {@link com.hivemq.spi.security.ClientData} object from calling
     * {@link AsyncClientService#getClientData(String)}, it's required to call {@link #isAccessible()} before
     * using the ConnectionAttributeStore.
     *
     * @return <code>true</code> if this connection attribute store is accessible, otherwise <code>false</code>
     */
    boolean isAccessible();

}
