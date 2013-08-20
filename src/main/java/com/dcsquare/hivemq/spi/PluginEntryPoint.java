/*
 * Copyright 2013 dc-square GmbH
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

package com.dcsquare.hivemq.spi;

import com.dcsquare.hivemq.spi.callback.registry.CallbackRegistry;

import javax.inject.Inject;

/**
 * The abstract class where all HiveMQ plugin Entry Points should extend from.
 * <p/>
 * You can use standard Guice injections like constructor injections. It is also possible to use LifeCycle
 * management annotations like {@link javax.annotation.PostConstruct} and {@link javax.annotation.PreDestroy}
 * <p/>
 * To get the global callback registry to register your own callbacks to HiveMQ, use <code>getCallbackRegistry()</code>
 * in your constructor or {@link javax.annotation.PostConstruct} annotated method
 *
 * @author Dominik Obermaier
 * @since 1.4
 */
public abstract class PluginEntryPoint {

    private CallbackRegistry callbackRegistry;

    /**
     * You should never call this method by your own except
     * for testing purposes.
     *
     * @param callbackRegistry the callback Registry object
     */
    @Inject
    final void setCallbackRegistry(final CallbackRegistry callbackRegistry) {

        this.callbackRegistry = callbackRegistry;
    }

    /**
     * Returns global Callback registry
     *
     * @return the global Callback Registry
     */
    public CallbackRegistry getCallbackRegistry() {
        return callbackRegistry;
    }
}
