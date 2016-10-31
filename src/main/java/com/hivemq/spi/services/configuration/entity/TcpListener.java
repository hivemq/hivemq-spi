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

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A listener which allows to listen to MQTT traffic via TCP
 *
 * @author Dominik Obermaier
 * @author Christoph Schaebel
 *
 * @since 3.0
 */
@Immutable
public class TcpListener implements Listener {


    private int port;

    private String bindAddress;

    private boolean proxyProtocolSupported;

    /**
     * Creates a new TCP listener which listens to a specific port and bind address
     *
     * @param port        the port
     * @param bindAddress the bind address
     */
    public TcpListener(final int port, final String bindAddress) {
        checkNotNull(bindAddress);
        this.port = port;
        this.bindAddress = bindAddress;
        this.proxyProtocolSupported = false;
    }

    /**
     * Creates a new TCP listener which listens to a specific port and bind address
     *
     * @param port        the port
     * @param bindAddress the bind address
     * @param proxyProtocolSupported if this listener should be able to utilize the PROXY protocol
     */
    public TcpListener(final int port, final String bindAddress, final boolean proxyProtocolSupported) {
        checkNotNull(bindAddress);
        this.port = port;
        this.bindAddress = bindAddress;
        this.proxyProtocolSupported = proxyProtocolSupported;
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
}
