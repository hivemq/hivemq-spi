/*
 * Copyright 2015 dc-square GmbH
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

package com.hivemq.spi.services.configuration;

/**
 * A Configuration service which allows to get information about the current throttling configuration
 * and allows to change the global throttling configuration of HiveMQ at runtime.
 *
 * @author Dominik Obermaier
 * @since 3.0
 */
public interface ThrottlingConfigurationService {


    /**
     * Returns the maximum allowed connections.
     * <p/>
     * <b>This method only returns the configuration of maximum concurrent MQTT connections, not the value
     * your HiveMQ license is limited to.</b>
     *
     * @return the maximum allowed connections.
     */
    long maxConnections();

    /**
     * @return the maximum allowed MQTT message size in bytes
     */
    int maxMessageSize();

    /**
     * @return the outgoing bandwidth limit in bytes
     */
    long outgoingLimit();

    /**
     * @return the incoming bandwidth limit in bytes
     */
    long incomingLimit();


    /**
     * Changes the maximum concurrent connections at runtime.
     *
     * @param maxConnections the maximum concurrent MQTT connections
     */
    void setMaxConnections(long maxConnections);

    /**
     * Changes the maximum allowed MQTT message size at runtime
     *
     * @param maxMessageSize the maximum allowed MQTT message size in bytes
     */
    void setMaxMessageSize(int maxMessageSize);

    /**
     * Changes the outgoing bandwidth limit at runtime
     *
     * @param outgoingLimit the outgoing bandwidth limit in bytes
     */
    void setOutgoingLimit(long outgoingLimit);

    /**
     * Changes the incoming bandwidth limit at runtime
     *
     * @param incomingLimit the incoming bandwidth limit in bytes
     */
    void setIncomingLimit(long incomingLimit);


    /**
     * Adds a callback which gets called when the maximum concurrent connections changes at runtime
     *
     * @param callback the ValueChangedCallback
     */
    void maxConnectionsChanged(ValueChangedCallback<Long> callback);

    /**
     * Adds a callback which gets called when the maximum message size changes at runtime
     *
     * @param callback the ValueChangedCallback
     */
    void maxMessageSizeChanged(ValueChangedCallback<Integer> callback);

    /**
     * Adds a callback which gets called when the outgoing bandwidth limit changes at runtime
     *
     * @param callback the ValueChangedCallback
     */
    void outgoingLimitChanged(ValueChangedCallback<Long> callback);

    /**
     * Adds a callback which gets called when the incoming bandwidth limit changes at runtime
     *
     * @param callback the ValueChangedCallback
     */
    void incomingLimitChanged(ValueChangedCallback<Long> callback);

}
