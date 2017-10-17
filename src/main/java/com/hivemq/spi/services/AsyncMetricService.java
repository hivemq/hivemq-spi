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
import com.google.common.util.concurrent.ListenableFuture;
import com.hivemq.spi.metrics.HiveMQMetric;

import java.util.Map;

/**
 * This service allows plugins to get or add global HiveMQ metrics.
 *
 * @author Lukas Brandl
 */
public interface AsyncMetricService {

    /**
     * Returns a specific HiveMQ metric. If the metric does not exist, this method will return
     * <code>null</code>.
     * <p/>
     * For a list of all available metrics, refer to the {@link com.hivemq.spi.metrics.HiveMQMetrics} constant class.
     *
     * @param metric the metric
     * @param <T>    the metric type
     * @return a {@link com.google.common.util.concurrent.ListenableFuture} with the metric (if available) or a <code>null</code> result.
     */
    <T extends Metric> ListenableFuture<T> getHiveMQMetric(HiveMQMetric<T> metric);

    /**
     * Returns a map which contains an entry for every node in the cluster with the given HiveMQ metric. An entry represents the name of the node (key) and the associated metric for the node (value).
     * If the metric does not exist, this method will return <code>null</code>.
     * <p/>
     * For a list of all available metrics, refer to the {@link com.hivemq.spi.metrics.HiveMQMetrics} constant class.
     *
     * @param metric the metric
     * @param <T>    the metric type
     * @return a {@link com.google.common.util.concurrent.ListenableFuture} with a map that contains node names and their sought-after metrics or <code>null</code>.
     */
    <T extends Metric> ListenableFuture<Map<String, T>> getClusterMetric(HiveMQMetric<T> metric);

    /**
     * Returns the metric registry of HiveMQ.
     *
     * @return the metric registry
     */
    MetricRegistry getMetricRegistry();
}
