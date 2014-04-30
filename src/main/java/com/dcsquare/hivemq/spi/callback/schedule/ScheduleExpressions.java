package com.dcsquare.hivemq.spi.callback.schedule;

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
