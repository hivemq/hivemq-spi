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
     * This method returns all subscriptions on HiveMQ as a {@link Multimap} of client identifiers and topics.
     * <p/>
     * Please be aware that calling this method very often on HiveMQ instances with many subscriptions could have
     * negative performance effects
     *
     * @return a {@link Multimap} of client identifiers and their topic subscriptions
     */
    public Multimap<String, Topic> getSubscriptions();

    /**
     * Returns all MQTT client subscriber identifiers for a given topic. MQTT Wildcards are allowed.
     *
     * @param topic the topic
     * @return client identifiers of all subscribers that subscribed to the topic
     */
    public Set<String> getSubscribers(String topic);

    /**
     * Returns all topics a client subscribed to.
     *
     * @param clientID of the client
     * @return all topics the client subscribed to
     */
    public Set<Topic> getTopics(String clientID);

    /**
     * This method adds a subscription for a certain client to a certain topic.
     * <p/>
     * If the clientId or the topic are <code>null</code>, nothing will happen.
     *
     * @param clientID client, which should be subscribed
     * @param topic    topic to which the client should be subscribed
     */
    public void addSubscription(String clientID, Topic topic);

    /**
     * This method removes a subscription for a certain client to a certain topic
     *
     * @param clientID client, which should be unsubscribed
     * @param topic    topic from which the client should be unsubscribed
     */
    public void removeSubscription(String clientID, String topic);

}
