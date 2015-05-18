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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Tls tls = (Tls) o;

        if (cipherSuites != null ? !cipherSuites.equals(tls.cipherSuites) : tls.cipherSuites != null) return false;
        if (clientAuthMode != tls.clientAuthMode) return false;
        if (handshakeTimeout != null ? !handshakeTimeout.equals(tls.handshakeTimeout) : tls.handshakeTimeout != null)
            return false;
        if (keystorePassword != null ? !keystorePassword.equals(tls.keystorePassword) : tls.keystorePassword != null)
            return false;
        if (keystorePath != null ? !keystorePath.equals(tls.keystorePath) : tls.keystorePath != null) return false;
        if (keystoreType != null ? !keystoreType.equals(tls.keystoreType) : tls.keystoreType != null) return false;
        if (privateKeyPassword != null ? !privateKeyPassword.equals(tls.privateKeyPassword) : tls.privateKeyPassword != null)
            return false;
        if (protocols != null ? !protocols.equals(tls.protocols) : tls.protocols != null) return false;
        if (truststorePassword != null ? !truststorePassword.equals(tls.truststorePassword) : tls.truststorePassword != null)
            return false;
        if (truststorePath != null ? !truststorePath.equals(tls.truststorePath) : tls.truststorePath != null)
            return false;
        if (truststoreType != null ? !truststoreType.equals(tls.truststoreType) : tls.truststoreType != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = keystorePath != null ? keystorePath.hashCode() : 0;
        result = 31 * result + (keystorePassword != null ? keystorePassword.hashCode() : 0);
        result = 31 * result + (keystoreType != null ? keystoreType.hashCode() : 0);
        result = 31 * result + (privateKeyPassword != null ? privateKeyPassword.hashCode() : 0);
        result = 31 * result + (truststorePath != null ? truststorePath.hashCode() : 0);
        result = 31 * result + (truststorePassword != null ? truststorePassword.hashCode() : 0);
        result = 31 * result + (truststoreType != null ? truststoreType.hashCode() : 0);
        result = 31 * result + (handshakeTimeout != null ? handshakeTimeout.hashCode() : 0);
        result = 31 * result + (clientAuthMode != null ? clientAuthMode.hashCode() : 0);
        result = 31 * result + (protocols != null ? protocols.hashCode() : 0);
        result = 31 * result + (cipherSuites != null ? cipherSuites.hashCode() : 0);
        return result;
    }
}
