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

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.multibindings.Multibinder;
import org.apache.commons.configuration.AbstractConfiguration;

/**
 * The HiveMQ Plugin Guice Module class from which you should inherit when writing a HiveMQ plugin.
 * <p/>
 * Essentially you have to provide a {@link Provider} with subclasses of {@link AbstractConfiguration}s which provide
 * the configuration properties for your plugin. {@link com.dcsquare.hivemq.spi.config.Configurations} provides convenient
 * methods for simplifying implementing this Provider.
 * <p/>
 * The <code>entryPointClass()</code> must return a subclass of {@link PluginEntryPoint}.
 * It's possible to use Dependency Injection with Guice in this entry point
 *
 * @author Dominik Obermaier
 * @since 1.4
 */
public abstract class HiveMQPluginModule extends AbstractModule {

    /**
     * Returns all configurations which are needed for the plugin. It's possible to return
     * different configuration sources like databases, files, etc.
     * <p/>
     * The {@link com.dcsquare.hivemq.spi.config.Configurations} class offers some useful utilities
     * for dealing with configuration providers.
     *
     * @return a Provider with a list of {@link AbstractConfiguration}s.
     */
    public abstract Provider<Iterable<? extends AbstractConfiguration>> getConfigurations();


    /**
     * {@inheritDoc}
     */
    @Override
    protected final void configure() {

        final Multibinder<AbstractConfiguration> multibinder = Multibinder.newSetBinder(binder(), AbstractConfiguration.class);

        final Provider<Iterable<? extends AbstractConfiguration>> configurations = getConfigurations();
        final Iterable<? extends AbstractConfiguration> abstractConfigurations = configurations.get();

        for (AbstractConfiguration abstractConfiguration : abstractConfigurations) {
            multibinder.addBinding().toInstance(abstractConfiguration);
        }
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
