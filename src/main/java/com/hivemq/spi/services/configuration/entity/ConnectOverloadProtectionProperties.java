package com.hivemq.spi.services.configuration.entity;

import com.hivemq.spi.annotations.Immutable;

/**
 * Enables the overload protection for MQTT CONNECT messages processed by HiveMQ.
 *
 * @author Georg Held
 * @since 3.3
 */
@Immutable
public class ConnectOverloadProtectionProperties {

    private final double connectRate;
    private final long connectBurstSize;

    /**
     * @param connectRate      the maximum sustained rate by which CONNECT messages shall be processed by the listener in CONNECT/seconds
     * @param connectBurstSize maximum amount of CONNECT messages that the listener shall handle at any time
     */
    public ConnectOverloadProtectionProperties(final double connectRate,
                                               final long connectBurstSize) {
        this.connectRate = connectRate;
        this.connectBurstSize = connectBurstSize;
    }

    /**
     * @param connectRate the maximum sustained rate by which CONNECT messages shall be processed by the listener in CONNECT/seconds
     */
    public ConnectOverloadProtectionProperties(final double connectRate) {
        this.connectRate = connectRate;
        this.connectBurstSize = (long) (connectRate * 2);
    }

    public double getConnectRate() {
        return connectRate;
    }

    public long getConnectBurstSize() {
        return connectBurstSize;
    }
}
