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

package com.dcsquare.hivemq.spi.config;

import com.google.inject.Provider;
import org.apache.commons.configuration.AbstractConfiguration;

import static com.google.common.collect.Lists.newArrayList;

/**
 * General utilities and convenient methods for dealing with configurations and properties files.
 *
 * @author Dominik Obermaier
 * @since 1.4
 */
public class Configurations {


    /**
     * Creates a new configuration provider with the given {@link AbstractConfiguration}s.
     *
     * @param configurations the configurations
     * @return a provider for the given configurations
     */
    public static Provider<Iterable<? extends AbstractConfiguration>> newConfigurationProvider(final AbstractConfiguration... configurations) {

        return new Provider<Iterable<? extends AbstractConfiguration>>() {
            @Override
            public Iterable<? extends AbstractConfiguration> get() {
                return newArrayList(configurations);
            }
        };
    }

    /**
     * A utility method which returns an empty Configuration Provider. This is a convenient method which can be used
     * by plugins which don't need configurations
     *
     * @return an empty Configuration Provider
     */
    public static Provider<Iterable<? extends AbstractConfiguration>> noConfigurationNeeded() {
        return new Provider<Iterable<? extends AbstractConfiguration>>() {
            @Override
            public Iterable<? extends AbstractConfiguration> get() {
                return newArrayList();
            }
        };
    }

}
