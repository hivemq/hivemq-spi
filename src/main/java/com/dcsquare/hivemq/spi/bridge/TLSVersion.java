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

package com.dcsquare.hivemq.spi.bridge;

/**
 * The TLS version for a bridge connection
 *
 * @author Dominik Obermaier
 * @since 2.0
 */
public enum TLSVersion {
    /**
     * SSLv3
     */
    SSLv3("SSLv3"),
    /**
     * TLS 1.0
     */
    TLS("TLS"),
    /**
     * TLS 1.0
     */
    TLSv1("TLSv1"),
    /**
     * TLS 1.1 (may not be available with all Java versions)
     */
    TLSv1_1("TLSv1.1"),
    /**
     * TLS 1.2 (may not be available with all Java versions)
     */
    TLSv1_2("TLSv1.2");

    private final String tlsVersion;

    TLSVersion(final String tlsVersion) {
        this.tlsVersion = tlsVersion;
    }

    public String getTlsVersion() {
        return tlsVersion;
    }

    /**
     * Returns the {@link TLSVersion} for this String.
     * <p/>
     * If the String is invalid, it will return <code>null</code>
     *
     * @param tlsVersion the TLS version string
     * @return the {@link TLSVersion}
     */
    public static TLSVersion fromString(final String tlsVersion) {

        for (final TLSVersion version : TLSVersion.values()) {
            if (version.getTlsVersion().equals(tlsVersion)) {
                return version;
            }
        }
        return null;
    }

}