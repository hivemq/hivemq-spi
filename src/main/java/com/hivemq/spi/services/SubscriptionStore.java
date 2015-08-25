/*
 * Copyright 2014 dc-square GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hivemq.spi.services;

import com.google.common.collect.Multimap;
import com.google.common.util.concurrent.ListenableFuture;
import com.hivemq.spi.annotations.NotNull;
import com.hivemq.spi.annotations.ReadOnly;
import com.hivemq.spi.message.Topic;

import java.util.Set;

/**
 * The subscription store allows the management of all client subscriptions from within a plugin
 *
 * @author Lukas Brandl
 * @author Dominik Obermaier
 * @since 1.5
 */
public interface SubscriptionStore {

    /**
     * This method returns all subscriptions on HiveMQ as a {@link Multimap} of client identifiers and topics.
     * <p/>
     * Please be aware that calling this method on HiveMQ instances with many subscriptions could have
     * negative performance effects.
     * <p/>
     * The returned Multimap is read-only and must not be modified.
     *
     * @return a {@link Multimap} of client identifiers and their topic subscriptions
     */
    @ReadOnly
    Multimap<String, Topic> getLocalSubscriptions();

    /**
     * Returns all MQTT client subscriber identifiers for a given topic. MQTT Wildcards are allowed.
     * <p/>
     * Don't pass <code>null</code> as topic. This method is lenient, so
     * it will just return an empty Set.
     * <p/>
     * The returned Set is read-only and must not be modified.
     *
     * @param topic the topic
     * @return client identifiers of all subscribers that subscribed to the topic
     */
    @ReadOnly
    Set<String> getLocalSubscribers(@NotNull String topic);

    /**
     * Returns all topics a client is subscribed to.
     * <p/>
     * If the client does not exist, an empty Set is returned.
     * <p/>
     * Don't pass <code>null</code> as clientId. This method is lenient, so
     * it will just return an empty Set.
     * <p/>
     * The returned Set is read-only and must not be modified.
     *
     * @param clientID of the client
     * @return all topics the client subscribed to
     */
    @ReadOnly
    Set<Topic> getLocalTopics(@NotNull String clientID);

    /**
     * This method adds a subscription for a certain client to a certain topic.
     * <p/>
     * This method is lenient, so if the clientId or the topic
     * is <code>null</code>, nothing will happen.
     *
     * @param clientID client, which should be subscribed
     * @param topic    topic to which the client should be subscribed
     */
    ListenableFuture<Void> addSubscription(@NotNull String clientID, @NotNull Topic topic);

    /**
     * This method removes a subscription for a certain client and a certain topic
     *
     * @param clientID client, which should get unsubscribed
     * @param topic    topic from which the client should get unsubscribed
     */
    ListenableFuture<Void> removeSubscription(@NotNull String clientID, @NotNull String topic);

    @ReadOnly
    ListenableFuture<Multimap<String, Topic>> getSubscriptions();

    @ReadOnly
    ListenableFuture<Set<String>> getSubscribers(@NotNull String topic);

    @ReadOnly
    ListenableFuture<Set<Topic>> getTopics(@NotNull String clientID);


    ListenableFuture<Void> addSubscription(@NotNull String clientID, @NotNull Topic topic, long timeout);

    ListenableFuture<Void> removeSubscription(@NotNull String clientID, @NotNull String topic, long timeout);

    @ReadOnly
    ListenableFuture<Multimap<String, Topic>> getSubscriptions(long timeout);

    @ReadOnly
    ListenableFuture<Set<String>> getSubscribers(@NotNull String topic, long timeout);

    @ReadOnly
    ListenableFuture<Set<Topic>> getTopics(@NotNull String clientID, long timeout);

}
