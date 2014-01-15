package com.dcsquare.hivemq.spi.util;

import com.dcsquare.hivemq.spi.message.PUBLISH;

/**
 * @author Lukas Brandl
 */
public interface PublishService {

    public void publish(PUBLISH publish);
}
