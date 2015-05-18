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

package com.hivemq.spi.callback.schedule;

import static com.google.common.base.Preconditions.checkArgument;
import static java.text.MessageFormat.format;

/**
 * This utility class has several methods and constants for working with quartz-style cron expressions.
 *
 * @author Dominik Obermaier
 */
public class ScheduleExpressions {

    /**
     * Returns the a Schedule Expression which schedules to the beginning of every minute
     */
    public static final String ONCE_A_MINUTE = "0 0/1 * * * ?";

    /**
     * Returns a quartz-style cron expression which is used to schedule something every X minutes.
     * <p/>
     * This is useful if you want to run a scheduled callback e.g. every 10 minutes.
     * <p/>
     *
     * @param minutes the minutes
     * @return the quartz-style cron expression for the number of minutes passed
     * @throws IllegalArgumentException if the number of minutes is < 1
     */
    public static String everyMinutes(final int minutes) {
        final String minutePattern = "0 0/{0,number,#} * * * ?";
        checkArgument(minutes > 0, "Only a number of minutes > 0 can be used for scheduling. %s was provided.", minutes);

        return format(minutePattern, minutes);
    }
}
