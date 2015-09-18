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
import com.hivemq.spi.annotations.NotNull;
import com.hivemq.spi.annotations.Nullable;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The TLS configuration
 *
 * @author Dominik Obermaier
 * @author Christoph Schäbel
 * @since 3.0
 */
@Immutable
public class Tls {


    /**
     * The X509 client certificate authentication mode.
     */
    public enum ClientAuthMode {
        /**
         * Clients are not allowed to send X509 client certificates
         */
        NONE("none"),
        /**
         * Clients can send X509 client certificates but they're not required to do so
         */
        OPTIONAL("optional"),
        /**
         * Clients must send X509 client certificates
         */
        REQUIRED("required");
        private final String clientAuthMode;

        private ClientAuthMode(final String clientAuthMode) {

            this.clientAuthMode = clientAuthMode;
        }

        /**
         * @return the client authentication mode
         */
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

    /**
     * Creates a new TLS configuration
     *
     * @param keystorePath       the path to the keystore
     * @param keystorePassword   the password for the keystore
     * @param keystoreType       the keystore type. When in doubt, use <b>JKS</b>
     * @param privateKeyPassword the password to the private key
     * @param truststorePath     the path to the truststore
     * @param truststorePassword the password for the truststore
     * @param truststoreType     the truststore type. When in doubt, use <b>JKS</b>
     * @param handshakeTimeout   the TLS handshake timeout
     * @param clientAuthMode     the client authentication mode
     * @param protocols          the supported protocols. <code>null</code> means that all enabled protocols by the JVM are enabled
     * @param cipherSuites       the supported cipher suites. <code>null</code> means that all enabled cipher suites by the JVM are enabled
     */
    public Tls(@NotNull final String keystorePath,
               @NotNull final String keystorePassword, @NotNull final String keystoreType,
               final String privateKeyPassword,
               @Nullable final String truststorePath,
               @Nullable final String truststorePassword,
               @Nullable final String truststoreType, final int handshakeTimeout, @NotNull final ClientAuthMode clientAuthMode,
               @Nullable final List<String> protocols, @Nullable final List<String> cipherSuites) {

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

    /**
     * @return the keystore path
     */
    public String getKeystorePath() {
        return keystorePath;
    }

    /**
     * @return the keystore password
     */
    public String getKeystorePassword() {
        return keystorePassword;
    }

    /**
     * @return the keystore type
     */
    public String getKeystoreType() {
        return keystoreType;
    }

    /**
     * @return the password of the private key
     */
    public String getPrivateKeyPassword() {
        return privateKeyPassword;
    }

    /**
     * @return the truststore path
     */
    @Nullable
    public String getTruststorePath() {
        return truststorePath;
    }

    /**
     * @return the truststore password
     */
    @Nullable
    public String getTruststorePassword() {
        return truststorePassword;
    }

    /**
     * @return the truststore type
     */
    @Nullable
    public String getTruststoreType() {
        return truststoreType;
    }

    /**
     * @return the TLS handshake timeout
     */
    public int getHandshakeTimeout() {
        return handshakeTimeout;
    }

    /**
     * @return the client authentication mode
     */
    public ClientAuthMode getClientAuthMode() {
        return clientAuthMode;
    }

    /**
     * @return the enabled TLS protocols
     */
    public List<String> getProtocols() {
        return protocols;
    }

    /**
     * @return the enabled cipher suites
     */
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
