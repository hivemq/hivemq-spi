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

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Dominik Obermaier
 * @author Christoph Schäbel
 */
@Immutable
public class Tls {


    public enum ClientAuthMode {
        NONE("none"),
        OPTIONAL("optional"),
        REQUIRED("required");
        private final String clientAuthMode;

        private ClientAuthMode(final String clientAuthMode) {

            this.clientAuthMode = clientAuthMode;
        }

        public String getClientAuthMode() {
            return clientAuthMode;
        }

        @Override
        public String toString() {
            return clientAuthMode;
        }
    }

    private final String keystorePath;
    private final String keystorePassword;
    private final String keystoreType;
    private final String privateKeyPassword;
    private final String truststorePath;
    private final String truststorePassword;
    private final String truststoreType;
    private final Integer handshakeTimeout;

    private final ClientAuthMode clientAuthMode;

    private final List<String> protocols;

    private final List<String> cipherSuites;

    public Tls(final String keystorePath,
               final String keystorePassword, final String keystoreType, final String privateKeyPassword, final String truststorePath,
               final String truststorePassword, String truststoreType, final int handshakeTimeout, final ClientAuthMode clientAuthMode,
               final List<String> protocols, final List<String> cipherSuites) {

        checkNotNull(clientAuthMode, "clientAuthMode must not be null");
        checkNotNull(protocols, "protocols must not be null");
        checkNotNull(cipherSuites, "cipher suites must not be null");
        this.keystorePath = keystorePath;
        this.keystorePassword = keystorePassword;
        this.keystoreType = keystoreType;
        this.privateKeyPassword = privateKeyPassword;
        this.truststorePath = truststorePath;
        this.truststorePassword = truststorePassword;
        this.truststoreType = truststoreType;
        this.handshakeTimeout = handshakeTimeout;
        this.clientAuthMode = clientAuthMode;
        this.protocols = protocols;
        this.cipherSuites = cipherSuites;
    }

    public String getKeystorePath() {
        return keystorePath;
    }

    public String getKeystorePassword() {
        return keystorePassword;
    }

    public String getKeystoreType() {
        return keystoreType;
    }

    public String getPrivateKeyPassword() {
        return privateKeyPassword;
    }

    public String getTruststorePath() {
        return truststorePath;
    }

    public String getTruststorePassword() {
        return truststorePassword;
    }

    public String getTruststoreType() {
        return truststoreType;
    }

    public int getHandshakeTimeout() {
        return handshakeTimeout;
    }

    public ClientAuthMode getClientAuthMode() {
        return clientAuthMode;
    }

    public List<String> getProtocols() {
        return protocols;
    }

    public List<String> getCipherSuites() {
        return cipherSuites;
    }
}
