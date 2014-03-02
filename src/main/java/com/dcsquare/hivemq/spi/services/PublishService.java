package com.dcsquare.hivemq.spi.services;

import com.dcsquare.hivemq.spi.message.PUBLISH;

/**
 * This service allows plugins to publish new MQTT messages
 *
 * @author Lukas Brandl
 * @since 1.5
 */
public interface PublishService {

    /**
     * Publishes a new MQTT {@link PUBLISH} message. The standard MQTT topic matching mechanism of HiveMQ will apply.
     * <p/>
     * If the given {@link PUBLISH} or any of its information (topic,qos,message) is null, a {@link NullPointerException}
     * will be thrown
     *
     * @param publish object with topic, QoS and message, which should be published to all subscribed clients
     * @throws NullPointerException if the given object is <code>null</code> or any relevant information like topic, qos
     *                              or message is <code>null</code>
     */
    public void publish(PUBLISH publish);
}
