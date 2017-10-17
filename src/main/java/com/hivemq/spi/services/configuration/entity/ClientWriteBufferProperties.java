package com.hivemq.spi.services.configuration.entity;

import com.hivemq.spi.annotations.Immutable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Allows the customization of write buffer behaviour.
 *
 * @author Georg Held
 * @since 3.3
 */
@Immutable
public class ClientWriteBufferProperties {

    private final int highThreshold;
    private final int lowThreshold;

    /**
     * @param highThreshold If the write buffer for a client reaches a size in bytes that is greater than {@code highThreshold} no more data will be written to the write buffer.
     * @param lowThreshold  If the write buffer for a client exceeded the highThreshold in the past, writing to the buffer will be resumed once the fill state of the buffer drops below the {@code lowThreshold}.
     */
    public ClientWriteBufferProperties(final int highThreshold, final int lowThreshold) {
        this.highThreshold = highThreshold;
        this.lowThreshold = lowThreshold;
    }

    public int getHighThreshold() {
        return highThreshold;
    }

    public int getLowThreshold() {
        return lowThreshold;
    }
}
