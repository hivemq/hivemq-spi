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
public class TcpListener extends OverridableConfiguration implements Listener {


    private Value<Integer> port;

    private Value<String> bindAddress;

    public TcpListener(final Value<Integer> port, final Value<String> bindAddress) {
        this(port, bindAddress, true);

    }

    public TcpListener(final Value<Integer> port, final Value<String> bindAddress, final boolean overridable) {
        super(overridable);
        checkNotNull(port);
        checkNotNull(bindAddress);
        this.port = port;
        this.bindAddress = bindAddress;

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
        return "TCP Listener";
    }
}
