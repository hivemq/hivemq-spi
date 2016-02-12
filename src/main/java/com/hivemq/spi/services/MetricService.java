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
package com.hivemq.spi.services;


import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricRegistry;
import com.hivemq.spi.annotations.Nullable;
import com.hivemq.spi.metrics.HiveMQMetric;

/**
 * This service allows plugins to get or add global HiveMQ metrics.
 *
 * @author Christoph Schäbel
 * @since 3.0
 * @deprecated Use {@link BlockingMetricService} or {@link AsyncMetricService} instead.
 */
@Deprecated
public interface MetricService {

    /**
     * Returns a specific HiveMQ metric. If the metric does not exist, this method will return
     * <code>null</code>.
     * <p/>
     * For a list of all available metrics, refer to the {@link com.hivemq.spi.metrics.HiveMQMetrics} constant class.
     *
     * @param metric the metric
     * @param <T>    the metric type
     * @return the metric (if available) or <code>null</code>
     * @deprecated Use {@link BlockingMetricService} or {@link AsyncMetricService} instead.
     */
    @Nullable
    @Deprecated
    <T extends Metric> T getHiveMQMetric(HiveMQMetric<T> metric);

    /**
     * Returns the metric registry of HiveMQ.
     *
     * @return the metric registry
     * @deprecated Use {@link BlockingMetricService} or {@link AsyncMetricService} instead.
     */
    @Deprecated
    MetricRegistry getMetricRegistry();

}