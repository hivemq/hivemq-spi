package com.dcsquare.hivemq.spi.services.configuration;


/**
 * @author Christoph Schäbel
 */
public interface ValueChangedCallback<T> {

    void valueChanged(T newValue);

}
