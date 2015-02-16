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

package com.dcsquare.hivemq.spi.services.configuration.entity;

import com.dcsquare.hivemq.spi.annotations.Immutable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Dominik Obermaier
 */
@Immutable
public class WebsocketListener extends OverridableConfiguration implements Listener {

    private Value<Integer> port;

    private Value<String> bindAddress;

    private Value<String> path;

    private Value<Boolean> allowExtensions;

    private ValueList<String> subprotocols;


    protected WebsocketListener(final boolean overridable, final Value<Integer> port,
                                final Value<String> bindAddress, final Value<String> path,
                                final Value<Boolean> allowExtensions, final ValueList<String> subprotocols) {
        super(overridable);
        this.port = port;
        this.bindAddress = bindAddress;
        this.path = path;
        this.allowExtensions = allowExtensions;
        this.subprotocols = subprotocols;
    }


    @Override
    public Value<Integer> getPort() {
        return port;
    }

    @Override
    public Value<String> getBindAddress() {
        return bindAddress;
    }

    @Override
    public String readableName() {
        return "Websocket Listener";
    }

    public Value<String> getPath() {
        return path;
    }

    public Value<Boolean> getAllowExtensions() {
        return allowExtensions;
    }

    public ValueList<String> getSubprotocols() {
        return subprotocols;
    }

    public static class Builder {
        protected boolean overridable = true;
        protected Value<Integer> port;
        protected Value<String> bindAddress;
        protected Value<String> path = Value.overridableValue("");
        protected Value<Boolean> allowExtensions = Value.overridableValue(false);
        protected ValueList<String> subprotocols = ValueList.overridableList();


        public Builder() {
            //Add default subprotocol which is required by the MQTT spec
            subprotocols.add("mqtt");
        }

        public Builder overridable(boolean overridable) {
            this.overridable = overridable;
            return this;
        }

        public Builder port(final Value<Integer> port) {
            checkNotNull(path);
            this.port = port;
            return this;
        }

        public Builder bindAddress(final Value<String> bindAddress) {
            checkNotNull(path);
            this.bindAddress = bindAddress;
            return this;
        }

        public Builder path(final Value<String> path) {
            checkNotNull(path);
            this.path = path;
            return this;
        }

        public Builder allowExtensions(final Value<Boolean> allowExtensions) {
            checkNotNull(allowExtensions);
            this.allowExtensions = allowExtensions;
            return this;
        }

        public Builder setSubprotocols(final ValueList<String> subprotocols) {
            checkNotNull(subprotocols);
            this.subprotocols = subprotocols;
            return this;
        }

        public WebsocketListener build() throws IllegalStateException {
            if (port == null) {
                throw new IllegalStateException("The port for a Websocket listener was not set.");
            }

            if (bindAddress == null) {
                throw new IllegalStateException("The bind address for a Websocket listener was not set.");
            }

            return new WebsocketListener(overridable, port, bindAddress, path, allowExtensions, subprotocols);
        }
    }
}
