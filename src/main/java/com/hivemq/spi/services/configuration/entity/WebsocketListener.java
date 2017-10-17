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

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.hivemq.spi.annotations.Immutable;
import com.hivemq.spi.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A listener which allows to listen to MQTT traffic over websockets.
 * <p/>
 * Use the builder if you want to create a new websocket listener.
 *
 * @author Dominik Obermaier
 * @author Christoph Schaebel
 * @since 3.0
 */
@Immutable
public class WebsocketListener implements Listener {

    private final Integer port;

    private final String bindAddress;

    private final String path;

    private final Boolean allowExtensions;

    private final List<String> subprotocols;

    private final boolean proxyProtocolSupported;

    private final Optional<SocketOptionsProperties> socketOptionsProperties;

    private final Optional<ConnectOverloadProtectionProperties> connectOverloadProtectionProperties;

    private final Optional<ClientWriteBufferProperties> clientWriteBufferProperties;

    protected WebsocketListener(final int port,
                                final String bindAddress,
                                final String path,
                                final boolean allowExtensions,
                                final List<String> subprotocols,
                                final boolean proxyProtocolSupported,
                                final SocketOptionsProperties socketOptionsProperties,
                                final ConnectOverloadProtectionProperties connectOverloadProtectionProperties,
                                final ClientWriteBufferProperties clientWriteBufferProperties) {
        this.port = port;
        this.bindAddress = bindAddress;
        this.path = path;
        this.allowExtensions = allowExtensions;
        this.subprotocols = subprotocols;
        this.proxyProtocolSupported = proxyProtocolSupported;
        this.socketOptionsProperties = Optional.fromNullable(socketOptionsProperties);
        this.connectOverloadProtectionProperties = Optional.fromNullable(connectOverloadProtectionProperties);
        this.clientWriteBufferProperties = Optional.fromNullable(clientWriteBufferProperties);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPort() {
        return port;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBindAddress() {
        return bindAddress;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String readableName() {
        return "Websocket Listener";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isProxyProtocolSupported() {
        return proxyProtocolSupported;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<SocketOptionsProperties> getSocketOptionsProperties() {
        return this.socketOptionsProperties;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ConnectOverloadProtectionProperties> getConnectOverloadProtectionProperties() {
        return this.connectOverloadProtectionProperties;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ClientWriteBufferProperties> getClientWriteBufferProperties() {
        return this.clientWriteBufferProperties;
    }

    /**
     * @return the path of the websocket
     */
    public String getPath() {
        return path;
    }

    /**
     * @return if websocket extensions are allowed or not
     */
    public Boolean getAllowExtensions() {
        return allowExtensions;
    }

    /**
     * @return a list of all supported subprotocols
     */
    public List<String> getSubprotocols() {
        return subprotocols;
    }

    /**
     * A builder which allows to conveniently build a listener object with a fluent API
     */
    public static class Builder {
        protected Integer port;
        protected String bindAddress;
        protected String path = "";
        protected boolean allowExtensions = false;
        protected List<String> subprotocols = new ArrayList<>();
        protected boolean proxyProtocolSupported = false;
        protected SocketOptionsProperties socketOptionsProperties = null;
        protected ConnectOverloadProtectionProperties connectOverloadProtectionProperties = null;
        protected ClientWriteBufferProperties clientWriteBufferProperties = null;


        public Builder() {
            //Add default subprotocol which is required by the MQTT spec
            subprotocols.add("mqtt");
        }

        /**
         * Sets the port of the websocket listener
         *
         * @param port the port
         * @return the Builder
         */
        public Builder port(final int port) {
            this.port = port;
            return this;
        }

        /**
         * Sets the bind address of the websocket listener
         *
         * @param bindAddress the bind address
         * @return the Builder
         */
        public Builder bindAddress(final String bindAddress) {
            checkNotNull(bindAddress);
            this.bindAddress = bindAddress;
            return this;
        }

        /**
         * Sets the websocket path of the websocket listener
         *
         * @param path the path
         * @return the Builder
         */
        public Builder path(final String path) {
            checkNotNull(path);
            this.path = path;
            return this;
        }

        /**
         * Sets if websocket extensions should be allowed or not
         *
         * @param allowExtensions if websocket extensions should be allowed or not
         * @return the Builder
         */
        public Builder allowExtensions(final boolean allowExtensions) {
            this.allowExtensions = allowExtensions;
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
        public Builder setSubprotocols(final List<String> subprotocols) {
            checkNotNull(subprotocols);
            this.subprotocols = ImmutableList.copyOf(subprotocols);
            return this;
        }

        /**
         * Set this to true if you want this listener to support the PROXY protocol
         *
         * @param proxyProtocolSupported if this listener should be able to utilize the PROXY protocol
         * @return the Builder
         * @since 3.2
         */
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
        public Builder setClientWriteBufferProperties(@Nullable final ClientWriteBufferProperties clientWriteBufferProperties) {
            this.clientWriteBufferProperties = clientWriteBufferProperties;
            return this;
        }

        /**
         * Creates the Websocket Listener
         *
         * @return the Websocket Listener
         */
        public WebsocketListener build() throws IllegalStateException {
            if (port == null) {
                throw new IllegalStateException("The port for a Websocket listener was not set.");
            }

            if (bindAddress == null) {
                throw new IllegalStateException("The bind address for a Websocket listener was not set.");
            }

            return new WebsocketListener(port, bindAddress, path, allowExtensions, subprotocols, proxyProtocolSupported, socketOptionsProperties, connectOverloadProtectionProperties, clientWriteBufferProperties);
        }
    }
}
