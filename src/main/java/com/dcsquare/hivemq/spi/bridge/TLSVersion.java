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