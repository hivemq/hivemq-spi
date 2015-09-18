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

package com.hivemq.spi.callback.events;

import com.hivemq.spi.callback.AsynchronousCallback;
import com.hivemq.spi.message.DISCONNECT;
import com.hivemq.spi.security.ClientData;

/**
 * This callback gets called when a client disconnects gracefully with a
 * {@link DISCONNECT} message or when disconnects
 * abruptly by closing the TCP connection .
 *
 * @author Dominik Obermaier
 * @author Christian Goetz
 * @since 1.4
 */
public interface OnDisconnectCallback extends AsynchronousCallback {


    /**
     * @param clientData  client information
     * @param abruptAbort when <code>true</code> the TCP connection was closed before
     *                    and no {@link DISCONNECT}
     *                    message was sent
     */
    void onDisconnect(ClientData clientData, boolean abruptAbort);
}
