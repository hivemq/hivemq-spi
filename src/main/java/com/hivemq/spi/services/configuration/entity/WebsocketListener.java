/*
 * Copyright 2015 dc-square GmbH
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

package com.hivemq.spi.services.configuration.entity;

import com.hivemq.spi.annotations.Immutable;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Dominik Obermaier
 */
@Immutable
public class WebsocketListener implements Listener {

    private Integer port;

    private String bindAddress;

    private String path;

    private Boolean allowExtensions;

    private List<String> subprotocols;


    protected WebsocketListener(final int port,
                                final String bindAddress, final String path,
                                final boolean allowExtensions, final List<String> subprotocols) {
        this.port = port;
        this.bindAddress = bindAddress;
        this.path = path;
        this.allowExtensions = allowExtensions;
        this.subprotocols = subprotocols;
    }


    @Override
    public int getPort() {
        return port;
    }

    @Override
    public String getBindAddress() {
        return bindAddress;
    }

    @Override
    public String readableName() {
        return "Websocket Listener";
    }

    public String getPath() {
        return path;
    }

    public Boolean getAllowExtensions() {
        return allowExtensions;
    }

    public List<String> getSubprotocols() {
        return subprotocols;
    }

    public static class Builder {
        protected Integer port;
        protected String bindAddress;
        protected String path = "";
        protected boolean allowExtensions = false;
        protected List<String> subprotocols = new ArrayList<>();


        public Builder() {
            //Add default subprotocol which is required by the MQTT spec
            subprotocols.add("mqtt");
        }

        public Builder port(final int port) {
            this.port = port;
            return this;
        }

        public Builder bindAddress(final String bindAddress) {
            checkNotNull(bindAddress);
            this.bindAddress = bindAddress;
            return this;
        }

        public Builder path(final String path) {
            checkNotNull(path);
            this.path = path;
            return this;
        }

        public Builder allowExtensions(final boolean allowExtensions) {
            this.allowExtensions = allowExtensions;
            return this;
        }

        public Builder setSubprotocols(final List<String> subprotocols) {
            checkNotNull(subprotocols);
            this.subprotocols = ImmutableList.copyOf(subprotocols);
            return this;
        }

        public WebsocketListener build() throws IllegalStateException {
            if (port == null) {
                throw new IllegalStateException("The port for a Websocket listener was not set.");
            }

            if (bindAddress == null) {
                throw new IllegalStateException("The bind address for a Websocket listener was not set.");
            }

            return new WebsocketListener(port, bindAddress, path, allowExtensions, subprotocols);
        }
    }
}
