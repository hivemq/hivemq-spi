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

package com.dcsquare.hivemq.spi.services.rest;

import com.dcsquare.hivemq.spi.annotations.Experimental;
import com.dcsquare.hivemq.spi.annotations.ReadOnly;
import com.google.common.base.Preconditions;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Dominik Obermaier
 * @since 2.0
 */
@Experimental
public class RESTConfig {

    public static final String BIND_TO_ALL_INTERFACES = "0.0.0.0";

    private ObjectMapper objectMapper = new ObjectMapper();

    private final List<Class<?>> resources = new ArrayList<Class<?>>();

    private int port = 8080;

    private String bindAddress = BIND_TO_ALL_INTERFACES;

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public int getPort() {
        return port;
    }

    public void setPort(final int port) {
        Preconditions.checkArgument(port < 65535, "The HTTP REST port must not be higher than 65535. Passed: %s", port);
        Preconditions.checkArgument(port > 1, "The HTTP REST port must not be smaller than 1. Passed: %s", port);
        this.port = port;
    }

    /**
     * @since 2.1.1
     */
    public String getBindAddress() {
        return bindAddress;
    }

    /**
     * @since 2.1.1
     */
    public void setBindAddress(final String bindAddress) {
        this.bindAddress = bindAddress;
    }

    public void addResource(final Class resourceClass) {
        resources.add(resourceClass);
    }

    @ReadOnly
    public Collection<Class<?>> getResources() {
        return Collections.unmodifiableCollection(resources);
    }
}
