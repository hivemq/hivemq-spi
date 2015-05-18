package com.dcsquare.hivemq.spi.services;

/**
 * @author Christoph Schäbel
 */

import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricRegistry;
import com.dcsquare.hivemq.spi.metrics.HiveMQMetric;

/**
 * This service allows plugins to get or add global Statistics
 *
 * @author Christoph Schäbel
 * @since 3.0
 */
public interface MetricService {

    public <T extends Metric> T getHiveMQMetric(final HiveMQMetric<T> metric);

    public MetricRegistry getMetricRegistry();

}