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
public class TlsWebsocketListener extends WebsocketListener {

    private final Tls tls;

    private TlsWebsocketListener(final boolean overridable, final Value<Integer> port,
                                 final Value<String> bindAddress, final Value<String> path,
                                 final Value<Boolean> allowExtensions, final ValueList<String> subprotocols,
                                 final Tls tls) {
        super(overridable, port, bindAddress, path, allowExtensions, subprotocols);
        this.tls = tls;
    }

    public Tls getTls() {
        return tls;
    }

    @Override
    public String readableName() {
        return "Websocket Listener with TLS";
    }


    public static class Builder extends WebsocketListener.Builder {

        private Tls tls;

        public Builder tls(final Tls tls) {
            checkNotNull(tls);
            this.tls = tls;
            return this;
        }

        @Override
        public Builder overridable(final boolean overridable) {
            super.overridable(overridable);
            return this;
        }

        @Override
        public Builder port(final Value<Integer> port) {
            super.port(port);
            return this;
        }

        @Override
        public Builder bindAddress(final Value<String> bindAddress) {
            super.bindAddress(bindAddress);
            return this;
        }

        @Override
        public Builder path(final Value<String> path) {
            super.path(path);
            return this;
        }

        @Override
        public Builder allowExtensions(final Value<Boolean> allowExtensions) {
            super.allowExtensions(allowExtensions);
            return this;
        }

        @Override
        public Builder setSubprotocols(final ValueList<String> subprotocols) {
            super.setSubprotocols(subprotocols);
            return this;
        }

        public TlsWebsocketListener build() {
            //For validation purposes
            super.build();
            if (tls == null) {
                throw new IllegalStateException("The TLS settings for a TLS Websocket listener was not set.");
            }
            return new TlsWebsocketListener(overridable, port, bindAddress, path, allowExtensions, subprotocols, tls);
        }

    }
}
