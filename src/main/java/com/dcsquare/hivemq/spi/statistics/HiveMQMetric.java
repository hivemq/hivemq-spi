package com.dcsquare.hivemq.spi.statistics;

import com.codahale.metrics.Metric;

import static com.google.common.base.Preconditions.checkNotNull;

/**
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

    public String name() {
        return name;
    }

    public Class<? extends Metric> getClazz() {
        return clazz;
    }
}

