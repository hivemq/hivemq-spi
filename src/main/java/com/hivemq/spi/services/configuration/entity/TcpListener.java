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
import com.hivemq.spi.annotations.Immutable;
import com.hivemq.spi.annotations.NotNull;
import com.hivemq.spi.annotations.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A listener which allows to listen to MQTT traffic via TCP
 *
 * @author Dominik Obermaier
 * @author Christoph Schaebel
 * @author Georg Held
 * @since 3.0
 */
@Immutable
public class TcpListener implements Listener {


    private final int port;

    private final String bindAddress;

    private final boolean proxyProtocolSupported;

    private final Optional<SocketOptionsProperties> socketOptionsProperties;

    private final Optional<ConnectOverloadProtectionProperties> connectOverloadProtectionProperties;

    private final Optional<ClientWriteBufferProperties> clientWriteBufferProperties;

    /**
     * Creates a new TCP listener which listens to a specific port and bind address
     *
     * @param port        the port
     * @param bindAddress the bind address
     */
    public TcpListener(final int port, @NotNull final String bindAddress) {
        this(port, bindAddress, false);
    }

    /**
     * Creates a new TCP listener which listens to a specific port, bind address and proxy setting
     *
     * @param port                   the port
     * @param bindAddress            the bind address
     * @param proxyProtocolSupported if this listener should be able to utilize the PROXY protocol
     */
    public TcpListener(final int port, @NotNull final String bindAddress, final boolean proxyProtocolSupported) {
        this(port, bindAddress, proxyProtocolSupported, null, null, null);
    }

    /**
     * Creates a new TCP listener which listens to a specific port, bind address, proxy setting, socket options, overload protection and write buffer properties
     *
     * @param port                                the port
     * @param bindAddress                         the bind address
     * @param proxyProtocolSupported              if this listener should be able to utilize the PROXY protocol
     * @param socketOptionsProperties             a configuration of {@link SocketOptionsProperties} for this listener or null to use the default
     * @param connectOverloadProtectionProperties a configuration of {@link ConnectOverloadProtectionProperties} for this listener or null to deactivate connect overload protection
     * @param clientWriteBufferProperties         a configuration of {@link ClientWriteBufferProperties} for this listener or null to use the default
     */
    public TcpListener(final int port,
                       @NotNull final String bindAddress,
                       final boolean proxyProtocolSupported,
                       @Nullable final SocketOptionsProperties socketOptionsProperties,
                       @Nullable final ConnectOverloadProtectionProperties connectOverloadProtectionProperties,
                       @Nullable final ClientWriteBufferProperties clientWriteBufferProperties) {

        checkNotNull(bindAddress, "bindAddress must not be null");

        this.port = port;
        this.bindAddress = bindAddress;
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
        return "TCP Listener";
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
}
