package com.hivemq.spi.services;


import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricRegistry;
import com.hivemq.spi.annotations.Nullable;
import com.hivemq.spi.metrics.HiveMQMetric;

import java.util.Map;

/**
 * This service allows plugins to get or add global HiveMQ metrics.
 *
 * @author Christoph Schäbel
 * @since 3.0
 * @deprecated Use MetricServiceLocal or MetricServiceCluster instead.
 */
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
     */
    @Nullable
    <T extends Metric> T getHiveMQMetric(HiveMQMetric<T> metric);

    /**
     * Returns the metric registry of HiveMQ.
     *
     * @return the metric registry
     */
    MetricRegistry getMetricRegistry();

}