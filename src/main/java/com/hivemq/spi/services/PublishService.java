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

import com.hivemq.spi.annotations.NotNull;
import com.hivemq.spi.message.PUBLISH;

/**
 * This service allows plugins to publish new MQTT messages programmatically
 *
 * @author Lukas Brandl
 * @since 1.5
 */
public interface PublishService {

    /**
     * Publishes a new MQTT {@link PUBLISH} message. The standard MQTT topic matching mechanism of HiveMQ will apply.
     * <p/>
     * If the given {@link PUBLISH} or any of its information (topic,qos,message) is null, a {@link NullPointerException}
     * will be thrown
     *
     * @param publish object with topic, QoS and message, which should be published to all subscribed clients
     * @throws NullPointerException if the given object is <code>null</code> or any relevant information like topic, qos
     *                              or message is <code>null</code>
     */
    void publish(@NotNull PUBLISH publish);


    /**
     * Publishes a new MQTT {@link PUBLISH} message.
     * The PUBLISH will only be delivered to the client with the specified client identifier.
     * Also the client needs to be subscribed on the topic of the PUBLISH in order to receive it.
     * <p/>
     * If the given {@link PUBLISH} or any of its information (topic,qos,message) is null, a {@link NullPointerException}
     * will be thrown
     *
     * @param publish object with topic, QoS and message, which should be published to all subscribed clients
     * @throws NullPointerException if the given object is <code>null</code> or any relevant information like topic, qos
     *                              or message is <code>null</code>
     */
    void publishtoClient(@NotNull PUBLISH publish, @NotNull String clientId);
}
