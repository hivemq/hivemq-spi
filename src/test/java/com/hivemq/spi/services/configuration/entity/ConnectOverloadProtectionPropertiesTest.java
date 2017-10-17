package com.hivemq.spi.services.configuration.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConnectOverloadProtectionPropertiesTest {

    @Test(timeout = 5000)
    public void test_default_burst_size_is_set() throws Exception {
        final ConnectOverloadProtectionProperties connectOverloadProtectionProperties = new ConnectOverloadProtectionProperties(500.25);
        assertEquals(1000, connectOverloadProtectionProperties.getConnectBurstSize());
    }

    @Test(timeout = 5000)
    public void test_default_burst_size_is_overridden() throws Exception {
        final ConnectOverloadProtectionProperties connectOverloadProtectionProperties = new ConnectOverloadProtectionProperties(500.25, 750);
        assertEquals(750, connectOverloadProtectionProperties.getConnectBurstSize());
    }
}