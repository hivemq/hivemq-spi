package com.dcsquare.hivemq.spi.services;

import com.dcsquare.hivemq.spi.message.Topic;
import com.google.common.collect.Multimap;

import java.util.Set;

/**
 * @author Lukas Brandl
 */
public interface SubscriptionStore {

    /**
     * @return all Pairs of client identifier and Subscribed Topic
     */
    public Multimap<String, Topic> getSubscriptions();

    /**
     * @param topic the subscribed topic
     * @return client identifier of all Subscribers, that subscribed to the topic
     */
    public Set<String> getSubscribers(String topic);

    /**
     * @return all topics, that the client associated with the clientID hast subscribed to
     */
    public Set<Topic> getTopics(String clientID);

    public void addSubscription(String clientID, Topic topic);

    public void removeSubscription(String clientID, String topic);

}
