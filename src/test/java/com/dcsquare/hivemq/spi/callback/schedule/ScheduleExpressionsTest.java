/*
 * Copyright 2014 dc-square GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
