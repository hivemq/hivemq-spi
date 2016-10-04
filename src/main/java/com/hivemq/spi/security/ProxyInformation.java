package com.hivemq.spi.security;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;

import java.net.InetAddress;

/**
 * This class encapsulates all information that is forwarded by a load
 * balancer which uses the PROXY protocol. Except from the source connection
 * information and the load balancer information, most of the values are optional.
 * <p>
 * HiveMQ supports arbitrary TLVs which can be retrieved as raw TLV values (see {@link #rawTLVs()})
 *
 * @author Dominik Obermaier
 * @since 3.2
 */
public interface ProxyInformation {

    /**
     * Return the original source port of the MQTT client.
     *
     * @return the original source port of the MQTT client.
     */
    int sourcePort();

    /**
     * Returns the original source address of the MQTT client.
     *
     * @return the original source address of the MQTT client.
     */
    InetAddress sourceAddress();

    /**
     * Returns the port of the load balancer that is used to proxy the client connection.
     *
     * @return the port of the load balancer that is used to proxy the client connection.
     */
    int proxyPort();

    /**
     * Returns the address of the load balancer that is used to proxy the client connection.
     *
     * @return the address of the load balancer that is used to proxy the client connection.
     */
    InetAddress proxyAddress();

    /**
     * If the PROXY protocol implementation of the load balancer supports TLVs and proxies
     * a SSL connection, this method returns the TLS version of the original SSL connection
     *
     * @return an {@link Optional} that contains the original TLS version if supported by the load balancer
     */
    Optional<String> tlsVersion();

    /**
     * If the PROXY protocol implementation of the load balancer supports TLVs and proxies
     * a SSL connection with a X509 client certificate that is sent by the MQTT client,
     * this method returns the forwarded common name of the X509 client certificate
     * (if the client used one to authenticate the SSL connection).
     *
     * @return an {@link Optional} that contains the Common Name of the X509 client certificate
     */
    Optional<String> sslCertificateCN();

    /**
     * HiveMQ supports arbitrary TLVs, even TLVs that aren't specified by the PROXY protocol. This map
     * contains all the raw TLVs that are sent by the load balancer.
     * <p>
     * The key is the byte value of the TLV type and the value is the raw TLV as byte value.
     *
     * @return a Map with raw TLVs
     */
    ImmutableMap<Byte, byte[]> rawTLVs();

}
