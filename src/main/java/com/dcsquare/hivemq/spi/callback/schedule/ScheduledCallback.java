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


import com.dcsquare.hivemq.spi.callback.AsynchronousCallback;

/**
 * This callback gets called periodically based on the value provided in the
 * {@link com.dcsquare.hivemq.spi.callback.schedule.ScheduledCallback#cronExpression()} method.
 * <p/>
 * This callback is especially useful for recurring tasks like maintenance tasks.
 *
 * @author Dominik Obermaier
 * @since 2.0
 */
public interface ScheduledCallback extends AsynchronousCallback {


    /**
     * This method gets executed on the given schedules
     */
    void execute();

    /**
     * This method returns the quartz-like cron expression for the callback.
     * <p/>
     * Note that this method only gets called once when adding the callback to the
     * {@link com.dcsquare.hivemq.spi.callback.registry.CallbackRegistry}. If you have dynamic
     * cron expressions in this method, you must manually call the
     * {@link com.dcsquare.hivemq.spi.callback.registry.CallbackRegistry#reloadScheduledCallbackExpression(ScheduledCallback)}
     * method in order to reload the expression
     *
     * @return a String which contains the quartz-like cron expressions.
     * @see <a href="http://quartz-scheduler.org/api/2.2.0/org/quartz/CronExpression.html">
     *      Documentation for quartz cron expressions</a>
     */
    String cronExpression();
}
