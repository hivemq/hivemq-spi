package com.dcsquare.hivemq.spi.services;

import com.dcsquare.hivemq.spi.annotations.Experimental;
import com.dcsquare.hivemq.spi.services.rest.RESTConfig;

/**
 * @author Dominik Obermaier
 */
@Experimental
public interface RESTService {

    void start(RESTConfig restConfig) throws Exception;

    void stop();

    boolean isRunning();
}
