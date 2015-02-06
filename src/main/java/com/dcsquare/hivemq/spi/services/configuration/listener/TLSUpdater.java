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

package com.dcsquare.hivemq.spi.services.configuration.listener;

import com.dcsquare.hivemq.spi.services.configuration.entity.Tls;
import com.dcsquare.hivemq.spi.services.configuration.exception.ConfigurationNotOverridableException;
import com.dcsquare.hivemq.spi.services.configuration.exception.ConfigurationValidationException;

import java.io.File;

/**
 * @author Dominik Obermaier
 */
public interface TlsUpdater<T> {

    TlsUpdater<T> keystore(final File path, String password) throws ConfigurationNotOverridableException;

    TlsUpdater<T> truststore(final File path, String password) throws ConfigurationNotOverridableException;

    TlsUpdater<T> handshakeTimeout(final int timeout) throws ConfigurationNotOverridableException;

    TlsUpdater<T> clientAuthenticationMode(final Tls.ClientAuthMode mode) throws ConfigurationNotOverridableException;

    TlsUpdater<T> passCertificateToPlugins(final boolean passCertificates);

    TlsUpdater<T> addProtocol(final String protocol) throws ConfigurationNotOverridableException;

    TlsUpdater<T> removeProtocol(final String protocol) throws ConfigurationNotOverridableException;

    TlsUpdater<T> removeAllProtocols() throws ConfigurationNotOverridableException;

    TlsUpdater<T> addCipherSuite(final String cipherSuite) throws ConfigurationNotOverridableException;

    TlsUpdater<T> removeCipherSuite(final String cipherSuite) throws ConfigurationNotOverridableException;

    TlsUpdater<T> removeAllCipherSuites() throws ConfigurationNotOverridableException;

    T update() throws ConfigurationValidationException;

}
