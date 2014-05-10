package com.dcsquare.hivemq.spi.bridge;

public enum TLSVersion {
    SSLv3("SSLv3"),
    TLS("TLS"),
    TLSv1("TLSv1"),
    TLSv1_1("TLSv1.1"),
    TLSv1_2("TLSv1.2");

    private final String tlsVersion;

    TLSVersion(final String tlsVersion) {
        this.tlsVersion = tlsVersion;
    }

    public String getTlsVersion() {
        return tlsVersion;
    }

    public static TLSVersion fromString(final String tlsVersion) {

        for (final TLSVersion version : TLSVersion.values()) {
            if (version.getTlsVersion().equals(tlsVersion)) {
                return version;
            }
        }
        return null;
    }

}