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

package com.dcsquare.hivemq.spi.callback.registry;

import com.dcsquare.hivemq.spi.callback.Callback;
import com.dcsquare.hivemq.spi.callback.schedule.ScheduledCallback;

import java.util.List;
import java.util.Set;

/**
 * The callback registry is used by HiveMQ to determine, which callbacks are implemented.
 * <p/>
 * In order to make your desired callback available to HiveMQ, implement one of the
 * callbacks and add it to the callback registry.
 * <p/>
 * The callback registry can be obtained in the implementation of the
 * {@link com.dcsquare.hivemq.spi.PluginEntryPoint} by calling
 * {@link com.dcsquare.hivemq.spi.PluginEntryPoint#getCallbackRegistry()}.
 *
 * @author Dominik Obermaier
 * @author Christian Goetz
 * @since 1.4
 */
public interface CallbackRegistry {

    /**
     * Makes a callback available to HiveMQ.
     *
     * @param callback which should be added
     */
    void addCallback(Callback callback);

    /**
     * Makes one or more callbacks available to HiveMQ.
     *
     * @param callbacks which should be added
     */
    void addCallbacks(Callback... callbacks);

    /**
     * Returns a list of all available callbacks for a specific callback class.
     *
     * @param callbackClass interface of the callback for which the
     *                      classes should be retrieved
     * @return all callbacks found for the callbackClass
     */
    <T extends Callback> List<T> getCallbacks(Class<T> callbackClass);

    /**
     * Returns a set of all callback interfaces for which callbacks are available.
     *
     * @return all callback interfaces for which callbacks are available
     */
    Set<Class<? extends Callback>> getAllRegisteredCallbackClasses();


    /**
     * Removes a callback from the Callback Registry
     *
     * @param callback which should be removed
     */
    void removeCallback(Callback callback);

    /**
     * Remove all callbacks for a specific callback class
     *
     * @param callbackClass all callbacks for this class are going to be removed completely
     */
    void removeAllCallbacks(Class<? extends Callback> callbackClass);


    /**
     * Removes all callbacks from the Callback Registry
     */
    void clear();

    /**
     * Returns a set of all available callbacks in the Callback Registry
     *
     * @return list of all callbacks
     */
    Set<Callback> getAllCallbacks();


    /**
     * Manually triggers a reload of the Scheduled Expression of a {@link ScheduledCallback}.
     * <br/>
     * By default, the Cron expressions of a {@link ScheduledCallback}s don't get reloaded.
     * This reload trigger is useful if your Cron expression can change at runtime. If the cron expression
     * of your {@link ScheduledCallback} is static, it's not recommended to do a manual reload.
     */
    void reloadScheduledCallbackExpression(final ScheduledCallback scheduledCallback);
}
