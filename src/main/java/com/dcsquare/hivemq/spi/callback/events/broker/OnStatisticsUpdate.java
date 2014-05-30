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

package com.dcsquare.hivemq.spi.callback.events.broker;

import com.dcsquare.hivemq.spi.callback.AsynchronousCallback;
import com.dcsquare.hivemq.spi.statistics.HiveMQStatistics;

/**
 * This callback gets executed once in a statistics callback interval.
 * <p/>
 * This callback may never (or rarely) get executed when statistics are disabled or when the callback
 * interval is too high.
 * <p/>
 * Although possible, it is recommended to avoid writing access to the {@link HiveMQStatistics} object
 * passed to the <code>onStatisticsUpdate</code> method
 *
 * @author Dominik Obermaier
 * @since 1.4
 */
public interface OnStatisticsUpdate extends AsynchronousCallback {

    /**
     * Gets called once in the statistics callback interval. If statistics are disabled, this callback
     * is never called
     *
     * @param statistics the {@link HiveMQStatistics} which provides
     *                   access to general HiveMQ statistics
     */
    void onStatisticsUpdate(HiveMQStatistics statistics);
}
