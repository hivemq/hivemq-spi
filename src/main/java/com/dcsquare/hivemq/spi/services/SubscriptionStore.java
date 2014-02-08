package com.dcsquare.hivemq.spi.services;

import com.dcsquare.hivemq.spi.message.Topic;
import com.google.common.collect.Multimap;

import java.util.Set;

/**
 * The subscription store allows the management of all client subscription from within a plugin
 *
 * @author Lukas Brandl
 * @since 1.5
 */
public interface SubscriptionStore {

    /**
     * @return all subscriptions as a pair of client identifier and subscribed topic
     */
    public Multimap<String, Topic> getSubscriptions();

    /**
     * @param topic the subscribed topic
     * @return client identifier of all subscribers, that subscribed on the topic
     */
    public Set<String> getSubscribers(String topic);

    /**
     * @param clientID of the client
     * @return all topics the client is subscribed to
     */
    public Set<Topic> getTopics(String clientID);

    /**
     * This method adds a subscription for a certain client to a certain topic
     * @param clientID client, which should be subscribed
     * @param topic topic to which the client should be subscribed
     */
    public void addSubscription(String clientID, Topic topic);

    /**
     * This method removes a subscription for a certain client to a certain topic
     * @param clientID client, which should be unsubscribed
     * @param topic topic from which the client should be unsubscribed
     */
    public void removeSubscription(String clientID, String topic);

}
