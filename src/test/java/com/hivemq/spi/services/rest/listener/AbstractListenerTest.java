package com.hivemq.spi.services.rest.listener;

import org.junit.Test;

/**
 * @author Dominik Obermaier
 */
public class AbstractListenerTest {


    @Test(expected = NullPointerException.class)
    public void test_name_null() throws Exception {
        new AbstractListener(null, "host", 123) {
        };

    }

    @Test(expected = NullPointerException.class)
    public void test_host_null() throws Exception {
        new AbstractListener("name", null, 123) {
        };

    }

    @Test(expected = IllegalArgumentException.class)
    public void test_port_too_small() throws Exception {
        new AbstractListener("name", "host", 0) {
        };

    }

    @Test(expected = IllegalArgumentException.class)
    public void test_port_too_high() throws Exception {
        new AbstractListener("name", "host", 65536) {
        };

    }
}