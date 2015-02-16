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

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Dominik Obermaier
 */
public class TlsTcpListener extends TcpListener {

    private Tls tls;

    public TlsTcpListener(final Value<Integer> port, final Value<String> bindAddress, final Tls tls) {
        this(port, bindAddress, tls, true);
    }

    public TlsTcpListener(final Value<Integer> port, final Value<String> bindAddress, final Tls tls, final boolean overridable) {
        super(port, bindAddress, overridable);
        checkNotNull(tls);
        this.tls = tls;

    }

    public Tls getTls() {
        return tls;
    }

    @Override
    public String readableName() {
        return "TCP Listener with TLS";
    }
}
