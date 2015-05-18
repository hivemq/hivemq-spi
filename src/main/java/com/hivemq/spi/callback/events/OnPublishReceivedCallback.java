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

import com.hivemq.spi.callback.SynchronousCallback;
import com.hivemq.spi.callback.exception.OnPublishReceivedException;
import com.hivemq.spi.callback.security.OnAuthorizationCallback;
import com.hivemq.spi.message.PUBLISH;
import com.hivemq.spi.security.ClientData;

/**
 * Gets called when a {@link PUBLISH} MQTT message arrives.
 * <p/>
 * <b>This callback gets called very often, so make sure you are NOT blocking and you are using
 * proper caching</b>
 * <p/>
 * When throwing a {@link OnPublishReceivedException} it's possible to optionally disconnect the
 * publishing client.
 * <p/>
 * This callback is <b>not</b> meant to implement authorization (= Topic Restrictions). You should
 * use the {@link OnAuthorizationCallback} for that purpose
 * as this offers more fine grained actions and extension points.
 *
 * @author Dominik Obermaier
 * @since 1.4
 */
public interface OnPublishReceivedCallback extends SynchronousCallback {

    /**
     * Called when a {@link PUBLISH} MQTT message arrives
     * <p/>
     * The publish parameter references the publish object, that is sent to the subscribers,
     * after the onPublishReceived method was called. So if you don´t want your plugin to
     * interfere in the regular publishing process, you must copy the {@link PUBLISH} object.
     * Use the static copy method of the PUBLISH class for this purpose. Similar to the following code example.
     * PUBLISH copy = PUBLISH.copy(publish);
     *
     * @param publish    the {@link PUBLISH} message which was sent
     * @param clientData the information of the publishing client
     * @throws OnPublishReceivedException when the PUBLISH was invalid.
     */
    void onPublishReceived(PUBLISH publish, ClientData clientData) throws OnPublishReceivedException;

}
