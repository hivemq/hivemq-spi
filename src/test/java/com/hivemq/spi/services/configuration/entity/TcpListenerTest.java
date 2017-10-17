package com.hivemq.spi.services.configuration.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class TcpListenerTest {

    @Test(timeout = 5000, expected = NullPointerException.class)
    public void test_bind_address_must_not_be_null() throws Exception {
        new TcpListener(1883, null);
    }

    @Test(timeout = 5000)
    public void test_smallest_constructor_disables_options() throws Exception {
        final TcpListener tcpListener = new TcpListener(1883, "127.0.0.1");

        assertFalse(tcpListener.isProxyProtocolSupported());
        assertFalse(tcpListener.getSocketOptionsProperties().isPresent());
        assertFalse(tcpListener.getConnectOverloadProtectionProperties().isPresent());
        assertFalse(tcpListener.getClientWriteBufferProperties().isPresent());
    }
}