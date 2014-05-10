package com.dcsquare.hivemq.spi.bridge;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Dominik Obermaier
 */
public class TLSVersionTest {

    @Test
    public void test_fromString() throws Exception {
        final TLSVersion tlsVersion1 = TLSVersion.fromString("TLSv1.1");
        assertEquals(TLSVersion.TLSv1_1, tlsVersion1);

        final TLSVersion tlsVersion2 = TLSVersion.fromString("TLS");
        assertEquals(TLSVersion.TLS, tlsVersion2);

        final TLSVersion tlsVersion3 = TLSVersion.fromString("SSLv3");
        assertEquals(TLSVersion.SSLv3, tlsVersion3);

        final TLSVersion tlsVersion4 = TLSVersion.fromString("TLSv1");
        assertEquals(TLSVersion.TLSv1, tlsVersion4);

        final TLSVersion tlsVersion5 = TLSVersion.fromString("TLSv1.2");
        assertEquals(TLSVersion.TLSv1_2, tlsVersion5);
    }
}
