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
import com.dcsquare.hivemq.spi.callback.exception.BrokerUnableToStartException;

/**
 * This callback gets called when the broker starts but before network interfaces are bound
 * and before other bootstrapping actions takes place.
 * <p/>
 * To prevent HiveMQ from starting, throw a {@link BrokerUnableToStartException}
 *
 * @author Dominik Obermaier
 * @since 1.4
 */
public interface OnBrokerStart extends SynchronousCallback {

    /**
     * Called when the broker starts up
     *
     * @throws BrokerUnableToStartException when the broker must not be started
     *                                      because some preconditions are not satisfied
     */
    void onBrokerStart() throws BrokerUnableToStartException;
}
