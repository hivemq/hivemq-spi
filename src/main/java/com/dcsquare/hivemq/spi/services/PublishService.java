package com.dcsquare.hivemq.spi.services;

import com.dcsquare.hivemq.spi.message.PUBLISH;

/**
 * Through this class a plugin can publish messages
 *
 * @author Lukas Brandl
 * @since 1.5
 */
public interface PublishService {
    /**
     * @param publish object with topic, QoS and message, which should be published to all subscribed clients
     */
    public void publish(PUBLISH publish);
}
