package com.hivemq.spi.services.configuration;


/**
 * A callback which is executed when a value for a configuration changes
 * at runtime
 *
 * @author Christoph Schäbel
 * @since 3.0
 */
public interface ValueChangedCallback<T> {

    /**
     * This method gets executed when the valuef or a configuration changes
     *
     * @param newValue the new value
     */
    void valueChanged(T newValue);

}
