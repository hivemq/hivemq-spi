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

package com.hivemq.spi;

import com.google.inject.AbstractModule;

/**
 * The HiveMQ Plugin Guice Module class from which you should inherit when writing a HiveMQ plugin.
 * <p/>
 * The <code>entryPointClass()</code> must return a subclass of {@link PluginEntryPoint}.
 * It's possible to use Dependency Injection with Guice in this entry point
 *
 * @author Dominik Obermaier
 * @author Christoph Schäbel
 * @since 1.4
 */
public abstract class HiveMQPluginModule extends AbstractModule {

    /**
     * {@inheritDoc}
     */
    @Override
    protected final void configure() {

        configurePlugin();

        //Bind the main entry class and immediately start injection
        bind(entryPointClass()).asEagerSingleton();
    }

    /**
     * This method should be used to configure the Guice Bindings and Guice Configurations.
     */
    protected abstract void configurePlugin();

    /**
     * Must return the main plugin class which is the "entry point" of the plugin.
     * <p/>
     * Note: This plugin class can use Dependency Injection via Guice
     *
     * @return the main entry class
     */
    protected abstract Class<? extends PluginEntryPoint> entryPointClass();
}
