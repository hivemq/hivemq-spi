package com.hivemq.spi.message;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Georg Held
 */
public class SubackReturnCodeTest {
    @Test(expected = IllegalArgumentException.class)
    public void test_throw_illegal_argument_exception() throws Exception {
        SubackReturnCode.valueOf((byte) 3);
    }

    @Test
    public void test_suback_codes() throws Exception {
        assertEquals(SubackReturnCode.FAILURE, SubackReturnCode.valueOf((byte) 128));
        assertEquals(SubackReturnCode.SUCCESS_QOS_0, SubackReturnCode.valueOf((byte) 0));
        assertEquals(SubackReturnCode.SUCCESS_QOS_1, SubackReturnCode.valueOf((byte) 1));
        assertEquals(SubackReturnCode.SUCCESS_QOS_2, SubackReturnCode.valueOf((byte) 2));
    }

}