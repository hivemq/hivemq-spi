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

package com.hivemq.spi.services.configuration.validation.validators;

import com.google.common.collect.ImmutableList;
import com.hivemq.spi.services.configuration.entity.Listener;
import com.hivemq.spi.services.configuration.entity.Tls;
import com.hivemq.spi.services.configuration.entity.TlsTcpListener;
import com.hivemq.spi.services.configuration.entity.TlsWebsocketListener;
import com.hivemq.spi.services.configuration.validation.ValidationError;
import com.hivemq.spi.services.configuration.validation.Validator;
import com.hivemq.spi.util.DefaultSslEngineUtil;
import com.hivemq.spi.util.SslException;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.security.Security;
import java.util.List;

/**
 * The validator for listener configuration
 *
 * @author Dominik Obermaier
 * @author Christoph Schäbel
 * @since 3.0
 */
public class ListenerValidator implements Validator<Listener> {
    @Override
    public List<ValidationError> validate(final Listener listener, final String name) {

        final ImmutableList.Builder<ValidationError> validationErrors = ImmutableList.builder();

        validatePort(listener.getPort(), validationErrors);


        if (listener instanceof TlsWebsocketListener) {
            final Tls tls = ((TlsWebsocketListener) listener).getTls();
            validateTls(tls, validationErrors);
        } else if (listener instanceof TlsTcpListener) {
            final Tls tls = ((TlsTcpListener) listener).getTls();
            validateTls(tls, validationErrors);
        }


        return validationErrors.build();
    }

    private void validateTls(final Tls tls, final ImmutableList.Builder<ValidationError> validationErrors) {

        validateHandshake(tls, validationErrors);

        validateKeyStoreType(tls, validationErrors);
        validateTrustStoreType(tls, validationErrors);

        validateKeyStorePath(tls, validationErrors);
        validateTrustStorePath(tls, validationErrors);

        validateCipherSuites(tls, validationErrors);

        validateProtocols(tls, validationErrors);

    }

    private void validatePort(final Integer port, final ImmutableList.Builder<ValidationError> validationErrors) {
        if (port < 1 || port > 65535) {
            validationErrors.add(new ValidationError("%d is a invalid port. A valid port must have a value between 1 and 65535.", port));
        }
    }

    private void validateHandshake(final Tls tls, final ImmutableList.Builder<ValidationError> builder) {
        final Integer handshakeValue = tls.getHandshakeTimeout();
        if (handshakeValue < 0) {
            final ValidationError handshakeValidationError = new ValidationError("%d is a invalid handshake timeout. A valid handshake timeout must be >= 0", handshakeValue);
            builder.add(handshakeValidationError);
        }
    }

    private void validateKeyStoreType(final Tls tls, final ImmutableList.Builder<ValidationError> builder) {

        if (!Security.getAlgorithms("KeyStore").contains(tls.getKeystoreType())) {
            final ValidationError keystoreTypeValidationError = new ValidationError("Keystore Type '%s' is not supported", tls.getKeystoreType());
            builder.add(keystoreTypeValidationError);
        }

    }

    private void validateTrustStoreType(final Tls tls, final ImmutableList.Builder<ValidationError> builder) {
        if (!Security.getAlgorithms("KeyStore").contains(tls.getTruststoreType())) {
            final ValidationError truststoreTypeValidationError = new ValidationError("Truststore Type '%s' is not supported", tls.getTruststoreType());
            builder.add(truststoreTypeValidationError);
        }
    }

    private void validateKeyStorePath(final Tls tls, final ImmutableList.Builder<ValidationError> builder) {
        if (tls.getKeystorePath().trim().length() > 0) {

            File keystoreFile = new File(tls.getKeystorePath());

            if (!keystoreFile.exists()) {
                final ValidationError validationError = new ValidationError("Keystore file '%s' does not exist", tls.getKeystorePath());
                builder.add(validationError);
                return;
            }

            if (!Files.isReadable(FileSystems.getDefault().getPath(keystoreFile.getAbsolutePath()))) {
                final ValidationError validationError = new ValidationError("Keystore file '%s' is not readable, please check file permissions", tls.getKeystorePath());
                builder.add(validationError);
            }
        }
    }

    private void validateTrustStorePath(final Tls tls, final ImmutableList.Builder<ValidationError> builder) {
        if (tls.getTruststorePath().trim().length() > 0) {

            File truststoreFile = new File(tls.getTruststorePath());

            if (!truststoreFile.exists()) {
                final ValidationError validationError = new ValidationError("Truststore file '%s' does not exist", tls.getTruststorePath());
                builder.add(validationError);
                return;
            }

            //Do not use if (!truststoreFile.canRead()) { // because it seems to be buggy on windows
            if (!Files.isReadable(FileSystems.getDefault().getPath(truststoreFile.getAbsolutePath()))) {
                final ValidationError validationError = new ValidationError("Truststore file '%s' is not readable, please check file permissions", tls.getTruststorePath());
                builder.add(validationError);
            }
        }
    }

    private void validateProtocols(final Tls tls, final ImmutableList.Builder<ValidationError> builder) {

        try {

            final List<String> tlsProtocols = tls.getProtocols();
            final List<String> supportedProtocols = new DefaultSslEngineUtil().getSupportedProtocols();

            checkSupportedList(supportedProtocols,
                    tlsProtocols,
                    builder,
                    "the protocol '%s' is not supported by this JVM",
                    "None of the chosen TLS protocols is supported by this JVM");

        } catch (SslException e) {
            final ValidationError validationError = new ValidationError(e.getMessage());
            builder.add(validationError);
        }

    }

    private void validateCipherSuites(final Tls tls, final ImmutableList.Builder<ValidationError> builder) {

        try {
            final List<String> tlsCipherSuites = tls.getCipherSuites();
            final List<String> supportedCipherSuites = new DefaultSslEngineUtil().getSupportedCipherSuites();

            checkSupportedList(supportedCipherSuites,
                    tlsCipherSuites,
                    builder,
                    "the cipher suite '%s' is not supported by this JVM",
                    "None of the chosen TLS cipher suites is supported by this JVM");

        } catch (SslException e) {
            final ValidationError validationError = new ValidationError(e.getMessage());
            builder.add(validationError);
        }
    }

    private void checkSupportedList(final List supportedElements,
                                    final List chosenElements,
                                    final ImmutableList.Builder<ValidationError> builder,
                                    final String warnText,
                                    final String errorText) {

        int supportedCount = 0;

        if (chosenElements.size() < 1) {
            return;
        }

        for (Object element : chosenElements) {
            if (supportedElements.contains(element)) {
                supportedCount++;
            } else {
                final ValidationError validationError = new ValidationError(warnText, element.toString());
                builder.add(validationError);
            }
        }

        if (supportedCount == 0) {
            final ValidationError validationError = new ValidationError(errorText);
            builder.add(validationError);
        }
    }

}
