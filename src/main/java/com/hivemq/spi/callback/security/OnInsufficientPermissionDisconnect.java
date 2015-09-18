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

package com.hivemq.spi.callback.security;

import com.hivemq.spi.callback.AsynchronousCallback;
import com.hivemq.spi.message.QoS;
import com.hivemq.spi.security.ClientData;

/**
 * This callback gets executed after a client was disconnected due to insufficient permissions.
 *
 * @author Dominik Obermaier
 * @author Christian Goetz
 * @since 1.4
 */
public interface OnInsufficientPermissionDisconnect extends AsynchronousCallback {

    /**
     * Gets executed after a client was disconnected due to insufficient permissions when publishing
     *
     * @param clientData information about the client
     * @param topic      the topic the client wanted to publish to
     * @param qoS        the quality of service the client wanted to publish with
     */
    void onPublishDisconnect(ClientData clientData, String topic, QoS qoS);

    /**
     * Gets executed after a client was disconnected due to insufficient permissions when subscribing
     *
     * @param clientData information about the client
     * @param topic      the topic the client wanted to publish to
     * @param qoS        the quality of service the client wanted to publish with
     */
    void onSubscribeDisconnect(ClientData clientData, String topic, QoS qoS);
}
