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
import com.dcsquare.hivemq.spi.callback.exception.InvalidSubscriptionException;
import com.dcsquare.hivemq.spi.message.SUBSCRIBE;
import com.dcsquare.hivemq.spi.security.ClientData;

/**
 * Gets called when a {@link SUBSCRIBE} message arrives
 * <p/>
 * When a subscription was invalid it's possible to throw a
 * {@link InvalidSubscriptionException}.
 * <p/>
 * Please note that the recommended way to handle Topic Permissions is
 * to use the {@link com.dcsquare.hivemq.spi.callback.security.OnAuthorizationCallback}.
 *
 * @author Dominik Obermaier
 * @since 1.4
 */
public interface OnSubscribeCallback extends SynchronousCallback {


    /**
     * Called when a {@link SUBSCRIBE} message arrives.
     *
     * @param message    the SUBSCRIBE message
     * @param clientData the information about the client
     * @throws InvalidSubscriptionException when a subscription was invalid
     */
    void onSubscribe(SUBSCRIBE message, ClientData clientData) throws InvalidSubscriptionException;
}
