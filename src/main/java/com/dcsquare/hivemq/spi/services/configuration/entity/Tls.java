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

import static com.dcsquare.hivemq.spi.services.configuration.entity.Value.overridableValue;
import static com.dcsquare.hivemq.spi.services.configuration.entity.ValueList.overridableList;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Dominik Obermaier
 * @author Christoph Schäbel
 */
@Immutable
public class Tls extends OverridableConfiguration {


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

    private final Value<String> keystorePath;
    private final Value<String> keystorePassword;
    private final Value<String> keystoreType;
    private final Value<String> privateKeyPassword;
    private final Value<String> truststorePath;
    private final Value<String> truststorePassword;
    private final Value<String> truststoreType;
    private final Value<Integer> handshakeTimeout;

    private final boolean passCertificateToPlugins;
    private final Value<ClientAuthMode> clientAuthMode;

    private final ValueList<String> protocols;

    private final boolean printAvailableSuites;
    private final ValueList<String> cipherSuites;

    private Tls(final boolean overridable, final Value<String> keystorePath,
                final Value<String> keystorePassword, Value<String> keystoreType, Value<String> privateKeyPassword, final Value<String> truststorePath,
                final Value<String> truststorePassword, Value<String> truststoreType, final Value<Integer> handshakeTimeout,
                final boolean passCertificateToPlugins, final Value<ClientAuthMode> clientAuthMode,
                final ValueList<String> protocols, final boolean printAvailableSuites, final ValueList<String> cipherSuites) {
        super(overridable);

        this.keystorePath = keystorePath;
        this.keystorePassword = keystorePassword;
        this.keystoreType = keystoreType;
        this.privateKeyPassword = privateKeyPassword;
        this.truststorePath = truststorePath;
        this.truststorePassword = truststorePassword;
        this.truststoreType = truststoreType;
        this.handshakeTimeout = handshakeTimeout;
        this.passCertificateToPlugins = passCertificateToPlugins;
        this.clientAuthMode = clientAuthMode;
        this.protocols = protocols;
        this.printAvailableSuites = printAvailableSuites;
        this.cipherSuites = cipherSuites;
    }

    public Value<String> getKeystorePath() {
        return keystorePath;
    }

    public Value<String> getKeystorePassword() {
        return keystorePassword;
    }

    public Value<String> getKeystoreType() {
        return keystoreType;
    }

    public Value<String> getPrivateKeyPassword() {
        return privateKeyPassword;
    }

    public Value<String> getTruststorePath() {
        return truststorePath;
    }

    public Value<String> getTruststorePassword() {
        return truststorePassword;
    }

    public Value<String> getTruststoreType() {
        return truststoreType;
    }

    public Value<Integer> getHandshakeTimeout() {
        return handshakeTimeout;
    }

    public boolean isPassCertificateToPlugins() {
        return passCertificateToPlugins;
    }

    public Value<ClientAuthMode> getClientAuthMode() {
        return clientAuthMode;
    }

    public ValueList<String> getProtocols() {
        return protocols;
    }

    public boolean isPrintAvailableSuites() {
        return printAvailableSuites;
    }

    public ValueList<String> getCipherSuites() {
        return cipherSuites;
    }

    public static class Builder {
        private boolean overridable = true;
        private Value<String> keystorePath = overridableValue("");
        private Value<String> keystorePassword = overridableValue("");
        private Value<String> keystoreType = overridableValue("JKS");
        private Value<String> privateKeyPassword = overridableValue("");
        private Value<String> truststorePath = overridableValue("");
        private Value<String> truststorePassword = overridableValue("");
        private Value<String> truststoreType = overridableValue("JKS");
        private Value<Integer> handshakeTimeout = overridableValue(0);
        private boolean passCertificateToPlugins = true;
        private Value<Tls.ClientAuthMode> clientAuthMode = overridableValue(ClientAuthMode.NONE);
        private ValueList<String> protocols = overridableList();
        private boolean printAvailableSuites = false;
        private ValueList<String> cipherSuites = overridableList();

        public Builder overridable(final boolean overridable) {
            this.overridable = overridable;
            return this;
        }

        public Builder keystorePath(final Value<String> keystorePath) {
            checkNotNull(keystorePath);
            this.keystorePath = keystorePath;
            return this;
        }

        public Builder keystorePassword(final Value<String> keystorePassword) {
            checkNotNull(keystorePassword);
            this.keystorePassword = keystorePassword;
            return this;
        }

        public Builder keystoreType(final Value<String> keystoreType) {
            checkNotNull(keystoreType);
            this.keystoreType = keystoreType;
            return this;
        }

        public Builder privateKeyPassword(final Value<String> privateKeyPassword) {
            checkNotNull(privateKeyPassword);
            this.privateKeyPassword = privateKeyPassword;
            return this;
        }

        public Builder truststorePath(final Value<String> truststorePath) {
            checkNotNull(truststorePath);
            this.truststorePath = truststorePath;
            return this;
        }

        public Builder truststorePassword(final Value<String> truststorePassword) {
            checkNotNull(truststorePassword);
            this.truststorePassword = truststorePassword;
            return this;
        }

        public Builder truststoreType(final Value<String> truststoreType) {
            checkNotNull(truststoreType);
            this.truststoreType = truststoreType;
            return this;
        }

        public Builder handshakeTimeout(final Value<Integer> handshakeTimeout) {
            checkNotNull(handshakeTimeout);
            this.handshakeTimeout = handshakeTimeout;
            return this;
        }

        public Builder passCertificateToPlugins(boolean passCertificateToPlugins) {
            this.passCertificateToPlugins = passCertificateToPlugins;
            return this;
        }

        public Builder clientAuthMode(final Value<Tls.ClientAuthMode> clientAuthMode) {
            checkNotNull(clientAuthMode);
            this.clientAuthMode = clientAuthMode;
            return this;
        }

        public Builder protocols(final ValueList<String> protocols) {
            checkNotNull(protocols);
            this.protocols = protocols;
            return this;
        }

        public Builder printAvailableSuites(final boolean printAvailableSuites) {
            this.printAvailableSuites = printAvailableSuites;
            return this;
        }

        public Builder cipherSuites(final ValueList<String> cipherSuites) {
            checkNotNull(cipherSuites);
            this.cipherSuites = cipherSuites;
            return this;
        }

        public Tls build() {
            return new Tls(overridable, keystorePath, keystorePassword, keystoreType, privateKeyPassword, truststorePath,
                    truststorePassword, truststoreType, handshakeTimeout, passCertificateToPlugins,
                    clientAuthMode, protocols, printAvailableSuites, cipherSuites);
        }

    }
}
