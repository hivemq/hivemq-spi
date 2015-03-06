/*
 * Copyright 2014 dc-square GmbH
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

package com.dcsquare.hivemq.spi.security;

import com.google.common.base.Optional;

/**
 * Represents information available of a connected client.
 *
 * @author Christian Goetz
 * @author Dominik Obermaier
 * @since 1.4
 */
public interface ClientData {

    /**
     * @return the client identifier which was provided in the MQTT CONNECT message
     */
    public String getClientId();

    /**
     * @return an {@link Optional} with the username which was provided in the MQTT CONNECT message.
     */
    public Optional<String> getUsername();

    /**
     * @return <code>true</code> if the client is authenticated, <code>false</code> otherwise
     */
    public boolean isAuthenticated();

    /**
     * @return an {@link Optional} of a {@link com.dcsquare.hivemq.spi.security.SslClientCertificate} which was provided at transport level
     * client certificate authentication.
     * @since 3.0
     */
    public Optional<SslClientCertificate> getCertificate();

    /**
     * @return <code>true</code> if the client is anonymous. That means the client is <b>not</b> authenticated
     */
    public boolean isAnonymous();

    /**
     * @return <code>true</code> if this client is a bridge.
     * @since 2.0
     */
    public boolean isBridge();

}
