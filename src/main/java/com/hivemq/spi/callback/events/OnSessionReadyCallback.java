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

package com.hivemq.spi.callback.events;

import com.hivemq.spi.callback.SynchronousCallback;
import com.hivemq.spi.security.ClientData;

/**
 * This callback gets called once the MQTT Session is ready for a client. This callback gets called once the MQTT Session for the client is ready to be used. This is guaranteed to happen before the client receives a MQTT {@link com.hivemq.spi.message.CONNACK} message.
 * <p>
 * It gets called for all clients regardless of the value of the clean-session flag or the existence of a previous session..
 *
 * @author Georg Held
 * @since 3.3.0
 */
public interface OnSessionReadyCallback extends SynchronousCallback {

    /**
     * This method gets called once the MQTT Session for the client is ready to be used.
     *
     * @param clientData information about the client.
     */
    public void onSessionReady(final ClientData clientData);
}
