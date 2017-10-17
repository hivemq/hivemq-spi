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
import com.hivemq.spi.annotations.Nullable;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A listener which allows to listen to MQTT traffic over secure websockets with TLS.
 * <p/>
 * Use the builder if you want to create a new TLS websocket listener.
 *
 * @author Dominik Obermaier
 * @author Christoph Schaebel
 * @since 3.0
 */
@Immutable
public class TlsWebsocketListener extends WebsocketListener {

    private final Tls tls;

    private TlsWebsocketListener(final int port,
                                 final String bindAddress, final String path,
                                 final Boolean allowExtensions, final List<String> subprotocols,
                                 final Tls tls,
                                 final boolean proxyProtocolSupported,
                                 final SocketOptionsProperties socketOptionsProperties,
                                 final ConnectOverloadProtectionProperties connectOverloadProtectionProperties,
                                 final ClientWriteBufferProperties clientWriteBufferProperties) {
        super(port, bindAddress, path, allowExtensions, subprotocols, proxyProtocolSupported, socketOptionsProperties, connectOverloadProtectionProperties, clientWriteBufferProperties);
        this.tls = tls;
    }

    /**
     * @return the TLS configuration
     */
    public Tls getTls() {
        return tls;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String readableName() {
        return "Websocket Listener with TLS";
    }

    /**
     * A builder which allows to conveniently build a listener object with a fluent API
     */
    public static class Builder extends WebsocketListener.Builder {

        private Tls tls;

        /**
         * Sets the TLS configuration of the TLS Websocket listener
         *
         * @param tls the TLS configuration
         * @return the Builder
         */
        public Builder tls(final Tls tls) {
            checkNotNull(tls);
            this.tls = tls;
            return this;
        }

        /**
         * Sets the port of the TLS websocket listener
         *
         * @param port the port
         * @return the Builder
         */
        @Override
        public Builder port(final int port) {
            super.port(port);
            return this;
        }

        /**
         * Sets the bind address of the TLS websocket listener
         *
         * @param bindAddress the bind address
         * @return the Builder
         */
        @Override
        public Builder bindAddress(final String bindAddress) {
            super.bindAddress(bindAddress);
            return this;
        }

        /**
         * Sets the websocket path of the TLS websocket listener
         *
         * @param path the path
         * @return the Builder
         */
        @Override
        public Builder path(final String path) {
            super.path(path);
            return this;
        }

        /**
         * Sets if websocket extensions should be allowed or not
         *
         * @param allowExtensions if websocket extensions should be allowed or not
         * @return the Builder
         */
        @Override
        public Builder allowExtensions(final boolean allowExtensions) {
            super.allowExtensions(allowExtensions);
            return this;
        }

        /**
         * Sets a list of subprotocols the websocket listener should support.
         * <p/>
         * Typically you should use 'mqtt' and/or 'mqttv3.1
         *
         * @param subprotocols a list of websocket subprotocols
         * @return the Builder
         */
        @Override
        public Builder setSubprotocols(final List<String> subprotocols) {
            super.setSubprotocols(subprotocols);
            return this;
        }

        /**
         * Set this to true if you want this listener to support the PROXY protocol
         *
         * @param proxyProtocolSupported if this listener should be able to utilize the PROXY protocol
         * @return the Builder
         * @since 3.2
         */
        @Override
        public Builder proxyProtocolSupported(final boolean proxyProtocolSupported) {
            this.proxyProtocolSupported = proxyProtocolSupported;
            return this;
        }


        /**
         * Set the {@link SocketOptionsProperties} of this listener
         *
         * @param socketOptionsProperties a configuration of {@link SocketOptionsProperties} for this listener or null to use the default
         * @return the Builder
         * @since 3.3
         */
        @Override
        public Builder setSocketOptionsProperties(@Nullable final SocketOptionsProperties socketOptionsProperties) {
            this.socketOptionsProperties = socketOptionsProperties;
            return this;
        }

        /**
         * Set the {@link ConnectOverloadProtectionProperties} of this listener
         *
         * @param connectOverloadProtectionProperties a configuration of {@link ConnectOverloadProtectionProperties} for this listener or null to deactivate connect overload protection
         * @return the Builder
         * @since 3.3
         */
        @Override
        public Builder setConnectOverloadProtectionProperties(@Nullable final ConnectOverloadProtectionProperties connectOverloadProtectionProperties) {
            this.connectOverloadProtectionProperties = connectOverloadProtectionProperties;
            return this;
        }

        /**
         * Set the {@link ClientWriteBufferProperties} of this listener
         *
         * @param clientWriteBufferProperties a configuration of {@link ClientWriteBufferProperties} for this listener or null to use the default
         * @return the Builder
         * @since 3.3
         */
        @Override
        public Builder setClientWriteBufferProperties(@Nullable final ClientWriteBufferProperties clientWriteBufferProperties) {
            this.clientWriteBufferProperties = clientWriteBufferProperties;
            return this;
        }

        /**
         * Creates the TLS Websocket Listener
         *
         * @return the TLS Websocket Listener
         */
        @Override
        public TlsWebsocketListener build() {
            //For validation purposes
            super.build();
            if (tls == null) {
                throw new IllegalStateException("The TLS settings for a TLS Websocket listener was not set.");
            }
            return new TlsWebsocketListener(port, bindAddress, path, allowExtensions, subprotocols, tls, proxyProtocolSupported, socketOptionsProperties, connectOverloadProtectionProperties, clientWriteBufferProperties);
        }

    }
}
