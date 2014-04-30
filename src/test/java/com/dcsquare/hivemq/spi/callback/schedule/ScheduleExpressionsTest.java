package com.dcsquare.hivemq.spi.callback.schedule;

import org.junit.Test;

import static com.dcsquare.hivemq.spi.callback.schedule.ScheduleExpressions.ONCE_A_MINUTE;
import static com.dcsquare.hivemq.spi.callback.schedule.ScheduleExpressions.everyMinutes;
import static org.junit.Assert.assertEquals;

/**
 * Common schedule expressions which can be used with the {@link com.dcsquare.hivemq.spi.callback.schedule.ScheduledCallback}
 *
 * @author Dominik Obermaier
 */
public class ScheduleExpressionsTest {


    @Test(expected = IllegalArgumentException.class)
    public void test_every_minutes_invalid_argument_zero() throws Exception {
        everyMinutes(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_every_minutes_invalid_argument_minus() throws Exception {
        everyMinutes(-1);
    }

    @Test
    public void test_every_five_minutes() throws Exception {

        assertEquals("0 0/5 * * * ?", everyMinutes(5));
    }

    @Test
    public void test_every_MAX_INT_minutes() throws Exception {

        assertEquals("0 0/" + Integer.MAX_VALUE + " * * * ?", everyMinutes(Integer.MAX_VALUE));
    }

    @Test
    public void test_every_minute() throws Exception {
        assertEquals(ONCE_A_MINUTE, everyMinutes(1));
    }


}
