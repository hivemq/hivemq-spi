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

package com.dcsquare.hivemq.spi.callback.events;

import com.dcsquare.hivemq.spi.callback.SynchronousCallback;
import com.dcsquare.hivemq.spi.callback.exception.RefusedConnectionException;
import com.dcsquare.hivemq.spi.message.CONNECT;
import com.dcsquare.hivemq.spi.security.ClientData;

/**
 * This callback is called when a CONNECT MQTT message arrives.
 * <p/>
 * This callback is called after successful {@link com.dcsquare.hivemq.spi.callback.security.OnAuthenticationCallback}s.
 * That means, <strong>when this callback gets executed, Authentication already took place </strong>
 * <p/>
 * This callback is not designed to be used for performing authentication.
 * Use {@link com.dcsquare.hivemq.spi.callback.security.OnAuthenticationCallback} for this
 * purpose
 *
 * @author Dominik Obermaier
 * @author Christian Goetz
 * @since 1.4
 */
public interface OnConnectCallback extends SynchronousCallback {

    /**
     * This method gets called when a {@link CONNECT} message arrives.
     *
     * @param connect    the {@link CONNECT} object which arrived
     * @param clientData information about the authenticated/anonymous client
     * @throws RefusedConnectionException if the client should be disconnected with a
     *                                    {@link com.dcsquare.hivemq.spi.message.CONNACK} with a specific
     *                                    {@link com.dcsquare.hivemq.spi.message.ReturnCode}
     */
    void onConnect(CONNECT connect, ClientData clientData) throws RefusedConnectionException;
}
