package com.hivemq.spi.message;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Dominik Obermaier
 */
public class CONNACKTest {

    @Test(expected = IllegalArgumentException.class)
    public void test_throw_illegal_argument_if_session_present_on_non_accepted_return_code() throws Exception {
        new CONNACK(ReturnCode.REFUSED_NOT_AUTHORIZED, true);
    }

    @Test
    public void test_session_present_non_accepted_return_code() throws Exception {
        final CONNACK connack = new CONNACK(ReturnCode.ACCEPTED, true);

        assertEquals(true, connack.isSessionPresent());
        assertEquals(ReturnCode.ACCEPTED, connack.getReturnCode());
    }
}
