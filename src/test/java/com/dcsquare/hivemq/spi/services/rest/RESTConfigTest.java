package com.dcsquare.hivemq.spi.services.rest;

import org.junit.Test;

/**
 * @author Dominik Obermaier
 */
public class RESTConfigTest {

    @Test(expected = IllegalArgumentException.class)
    public void test_port_higher_than_allowed() throws Exception {
        final RESTConfig restConfig = new RESTConfig();
        restConfig.setPort(100000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_port_smaller_than_allowed() throws Exception {
        final RESTConfig restConfig = new RESTConfig();
        restConfig.setPort(0);
    }

    @Test
    public void test_port_ok() throws Exception {
        final RESTConfig restConfig = new RESTConfig();
        restConfig.setPort(8080);

        //No exception
    }

}
