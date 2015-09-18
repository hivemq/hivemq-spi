package com.hivemq.spi.security;

import java.security.cert.Certificate;

/**
 * Represents information of a X509 client certificate.
 *
 * @author Christoph Schäbel
 * @since 3.0
 */
public interface SslClientCertificate {

    /**
     * @return the last certificate in the chain
     */
    Certificate certificate();

    /**
     * @return the whole certificate chain
     */
    Certificate[] certificateChain();

    /**
     * @return the commonName from the last certificate in the chain
     */
    String commonName();

    /**
     * @return the organization from the last certificate in the chain
     */
    String organization();

    /**
     * @return the organizational unit from the last certificate in the chain
     */
    String organizationalUnit();

    /**
     * @return the title from the last certificate in the chain
     */
    String title();

    /**
     * @return the serial number from the last certificate in the chain
     */
    String serial();

    /**
     * @return the country code from the last certificate in the chain
     */
    String country();

    /**
     * @return the locality from the last certificate in the chain
     */
    String locality();

    /**
     * @return the state from the last certificate in the chain
     */
    String state();

}
