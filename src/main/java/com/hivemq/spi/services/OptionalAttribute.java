package com.hivemq.spi.services;

import com.hivemq.spi.annotations.Nullable;

/**
 * @author Florian Limpöck
 */
public class OptionalAttribute {

    /**
     * the replaced value.
     * may be <null> if no value was replaced.
     */
    private final byte[] value;

    /**
     * true if a value was replaced, else false.
     */
    private final boolean replaced;

    public OptionalAttribute(@Nullable byte[] value, boolean replaced) {
        this.value = value;
        this.replaced = replaced;
    }

    public byte[] getValue() {
        return value;
    }

    public boolean isReplaced() {
        return replaced;
    }
}
