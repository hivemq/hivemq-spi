package com.hivemq.spi.metrics;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.Metric;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A convenience class to specify constant names and types of the internal HiveMQ metrics
 *
 * @author Christoph Schäbel
 */
public class HiveMQMetric<T extends Metric> {

    private final String name;
    private final Class<? extends Metric> clazz;


    private HiveMQMetric(final String name, final Class<? extends Metric> clazz) {
        this.name = name;
        this.clazz = clazz;
    }

    public static <T extends Metric> HiveMQMetric<T> valueOf(String name, Class<T> metricClass) {
        checkNotNull(name, "Name cannot be null");

        return new HiveMQMetric<>(name, metricClass);
    }

    public static HiveMQMetric<Gauge<Number>> gaugeValue(String name) {
        checkNotNull(name, "Name cannot be null");

        return new HiveMQMetric<>(name, Gauge.class);
    }

    public String name() {
        return name;
    }

    public Class<? extends Metric> getClazz() {
        return clazz;
    }
}