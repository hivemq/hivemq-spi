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

package com.hivemq.spi.callback.lowlevel;

import com.hivemq.spi.callback.AsynchronousCallback;
import com.hivemq.spi.callback.LowlevelCallback;
import com.hivemq.spi.message.PUBACK;
import com.hivemq.spi.security.ClientData;

/**
 * The OnPubackSend callback gets called after HiveMQ sent a PUBACK message to a client
 *
 * @author Christian Goetz
 * @since 1.4
 */
public interface OnPubackSend extends AsynchronousCallback, LowlevelCallback {

    /**
     * This method gets called after HiveMQ sent a PUBACK message to a client. This callback does not allow
     * to interfere with HiveMQ and is for information purposes only.
     *
     * @param puback the PUBACK message sent by HiveMQ
     * @param clientData the ClientData of the client HiveMQ sent the PUBACK to
     */
    void onPubackSend(PUBACK puback, ClientData clientData);
}
