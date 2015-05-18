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

package com.hivemq.spi.bridge;

import java.io.File;

/**
 * A concrete address a bridge tries to connect to
 *
 * @author Dominik Obermaier
 * @since 2.0
 */
public class Address {

    private String host;

    private int port;

    private TLSVersion tlsVersion;

    private boolean tlsEnabled = false;

    private File keystore;

    private String keystorePassword;

    private File trustStore;

    private String trustStorePassword;

    private boolean clientCertAuthentication;

    public Address(final String host, final int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(final String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(final int port) {
        this.port = port;
    }

    public TLSVersion getTlsVersion() {
        return tlsVersion;
    }

    public void setTlsVersion(final TLSVersion tlsVersion) {
        this.tlsVersion = tlsVersion;
    }

    public boolean isTlsEnabled() {
        return tlsEnabled;
    }

    public void setTlsEnabled(final boolean tlsEnabled) {
        this.tlsEnabled = tlsEnabled;
    }

    public File getKeystore() {
        return keystore;
    }

    public void setKeystore(final File keystore) {
        this.keystore = keystore;
    }

    public File getTrustStore() {
        return trustStore;
    }

    public void setTrustStore(final File trustStore) {
        this.trustStore = trustStore;
    }

    public boolean isClientCertAuthentication() {
        return clientCertAuthentication;
    }

    public void setClientCertAuthentication(final boolean clientCertAuthentication) {
        this.clientCertAuthentication = clientCertAuthentication;
    }

    public String getTrustStorePassword() {
        return trustStorePassword;
    }

    public void setTrustStorePassword(final String trustStorePassword) {
        this.trustStorePassword = trustStorePassword;
    }

    public String getKeystorePassword() {
        return keystorePassword;
    }

    public void setKeystorePassword(final String keystorePassword) {
        this.keystorePassword = keystorePassword;
    }
}
