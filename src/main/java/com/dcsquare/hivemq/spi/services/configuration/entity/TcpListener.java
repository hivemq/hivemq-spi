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
public class TcpListener implements Listener {


    private int port;

    private String bindAddress;

    public TcpListener(final int port, final String bindAddress) {
        checkNotNull(bindAddress);
        this.port = port;
        this.bindAddress = bindAddress;
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
        return "TCP Listener";
    }
}
