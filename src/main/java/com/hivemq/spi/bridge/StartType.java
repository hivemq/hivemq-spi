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

package com.hivemq.spi.bridge;

import com.hivemq.spi.services.BridgeManagerService;

/**
 * The start type of a bridge.
 *
 * @author Dominik Obermaier
 * @since 2.0
 */
public enum StartType {

    /**
     * The bridge connection will be started automatically when the broker starts
     * and it will try with best effort to reconnect if the connection drops or if
     * the connection failed.
     * <p/>
     * This is the default connection type
     */
    AUTOMATIC,
    /**
     * This bridge will not start automatically on broker startup. Lazy bridges queue messages
     * until a threshold is reached and then they connect to the remote broker. After a idle
     * timeout it will automatically disconnect again if no messages are delivered in the meantime
     * <p/>
     * This type is very useful if you only want to connect a bridge if needed for if you want to do
     * "batching". If you want to use the lazy mode for batching, consider setting the idle timeout to
     * a very low value.
     * <p/>
     * Please make sure that the idle timeout is shorter than the keepAlive value, otherwise
     * a bridge connection is never considered idle
     */
    LAZY,
    /**
     * This bridge will not start and stop automatically. You must use a plugin which utilizes
     * the {@link BridgeManagerService} to start and stop
     * this type of bridge.
     * <p/>
     * This bridge won't reconnect automatically if it disconnected unexpectedly, so make sure
     * you implemented some kind of heartbeat when using this bridge type.
     */
    MANUAL,
    /**
     * This bridge will start automatically on broker startup. If the connection drops,
     * this bridge type won't try to reconnect.
     * <p/>
     * It is possible to restart this type of bridge manually with the
     * {@link BridgeManagerService} in HiveMQ plugins
     */
    ONCE
}