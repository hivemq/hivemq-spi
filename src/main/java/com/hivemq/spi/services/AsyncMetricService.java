package com.hivemq.spi.services;

import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricRegistry;
import com.google.common.util.concurrent.ListenableFuture;
import com.hivemq.spi.annotations.Nullable;
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
     * @return the metric (if available) or <code>null</code>
     */
    <T extends Metric> ListenableFuture<T> getHiveMQMetric(HiveMQMetric<T> metric);

    /**
     * Returns a specific HiveMQ metric. If the metric does not exist, this method will return
     * <code>null</code>.
     * <p/>
     * For a list of all available metrics, refer to the {@link com.hivemq.spi.metrics.HiveMQMetrics} constant class.
     *
     * @param metric the metric
     * @param <T>    the metric type
     * @return the metric (if available) or <code>null</code>
     */
    <T extends Metric> ListenableFuture<Map<String, T>> getClusterMetric(HiveMQMetric<T> metric);

    /**
     * Returns the metric registry of HiveMQ.
     *
     * @return the metric registry
     */
    MetricRegistry getMetricRegistry();
}
