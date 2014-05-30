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

package com.dcsquare.hivemq.spi.callback.events.broker;

import com.dcsquare.hivemq.spi.callback.SynchronousCallback;

/**
 * This callback gets executed when the broker shuts down.
 * At the time this callback gets executed, all connections are already closed
 * and there is no way to prevent HiveMQ from shutting down.
 * <p/>
 * It is recommended to do <b>blocking actions</b> in this callback because the VM
 * could quit before your asynchronous actions are finished
 *
 * @author Dominik Obermaier
 * @since 1.4
 */
public interface OnBrokerStop extends SynchronousCallback {

    /**
     * Called when the broker shuts down
     * <p/>
     * You can use blocking actions in this callback.
     */
    void onBrokerStop();
}
