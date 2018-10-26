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
import com.hivemq.spi.services.exception.NoSuchClientIdException;
import com.hivemq.spi.services.exception.RateLimitExceededException;
import com.hivemq.spi.topic.exception.InvalidTopicException;

import java.util.Set;

/**
 * The subscription store allows the management of all client subscriptions from within a plugin
 *
 * @author Lukas Brandl
 * @author Florian Limpöck
 * @since 3.1
 */
public interface AsyncSubscriptionStore {

    /**
     * This method returns all subscriptions on this HiveMQ Node as a {@link com.google.common.collect.Multimap} of client identifiers and topics.
     * You won't receive subscriptions of connected
     * clients from other HiveMQ nodes if HiveMQ runs in a cluster.
     * <p/>
     * Please be aware that calling this method on HiveMQ instances with many subscriptions could have
     * negative performance effects.
     * <p/>
     * The returned Multimap is read-only and must not be modified.
     *
     * @return a {@link ListenableFuture} which contains a {@link com.google.common.collect.Multimap} of client identifiers and their topic subscriptions
     * failing with a {@link RateLimitExceededException} if the plugin service rate limit is exceeded.
     */
    @ReadOnly
    ListenableFuture<Multimap<String, Topic>> getLocalSubscriptions();

    /**
     * Returns all MQTT client subscriber identifiers for a given topic, for this HiveMQ instance.
     * MQTT Wildcards are allowed.
     * <p/>
     * Don't pass <code>null</code> as topic. This method is lenient, so
     * it will just return an empty Set.
     * <p/>
     * The returned Set is read-only and must not be modified.
     *
     * @param topic the topic
     * @return a {@link ListenableFuture} which contains the client identifiers of all subscribers that subscribed to the topic
     * failing with a {@link RateLimitExceededException} if the plugin service rate limit is exceeded.
     */
    @ReadOnly
    ListenableFuture<Set<String>> getLocalSubscribers(@NotNull String topic);

    /**
     * Returns all topics a client is subscribed to, on this HiveMQ instance.
     * <p/>
     * If the client does not exist, an empty Set is returned.
     * <p/>
     * Don't pass <code>null</code> as clientId. This method is lenient, so
     * it will just return an empty Set.
     * <p/>
     * The returned Set is read-only and must not be modified.
     *
     * @param clientID of the client
     * @return a {@link ListenableFuture} which contains all topics the client subscribed to
     * failing with a {@link RateLimitExceededException} if the plugin service rate limit is exceeded.
     */
    @ReadOnly
    ListenableFuture<Set<Topic>> getLocalTopics(@NotNull String clientID);

    /**
     * This method adds a subscription for a certain client to a certain topic.
     * If HiveMQ is connected to a cluster, the subscription will be broadcast to all other Cluster Nodes.
     * <p/>
     * This method is lenient, so if the clientId or the topic
     * is <code>null</code>, nothing will happen.
     *
     * @param clientID client, which should be subscribed
     * @param topic    topic to which the client should be subscribed
     * @return a {@link ListenableFuture} object that will succeed, as soon es the subscription was added by all Cluster Nodes.
     * failing with a {@link RateLimitExceededException} if the plugin service rate limit was exceeded.
     */
    ListenableFuture<Void> addSubscription(@NotNull String clientID, @NotNull Topic topic);

    /**
     * This method adds subscriptions for a certain client to certain topics.
     * If HiveMQ is connected to a cluster, the subscription will be broadcast to all other Cluster Nodes.
     * <p>
     *
     * @param clientID client, which should be subscribed
     * @param topics   topics to which the client should be subscribed
     * @return a {@link ListenableFuture} object that will succeed, as soon es the subscriptions were added by all Cluster Nodes.
     * failing with a {@link RateLimitExceededException} if the plugin service rate limit was exceeded.
     * failing with a {@link InvalidTopicException} if any topic is not utf-8 well-formed or empty.
     * failing with a {@link NoSuchClientIdException} if a client does not exist.
     * <p>
     * @throws NullPointerException     if clientID or topics is <code>null</code>.
     * @throws IllegalArgumentException if clientID or topics is empty.
     */
    ListenableFuture<Void> addSubscriptions(@NotNull String clientID, @NotNull Set<Topic> topics);

    /**
     * This method removes a subscription for a certain client and a certain topic.
     * If HiveMQ is connected to a cluster, the subscription will be removed by other Cluster Nodes as well.
     *
     * @param clientID client, which should get unsubscribed
     * @param topic    topic from which the client should get unsubscribed
     * @return a {@link ListenableFuture} object that will succeed, as soon es the subscription was removed by all Cluster Nodes.
     * failing with a {@link RateLimitExceededException} if the plugin service rate limit was exceeded.
     */
    ListenableFuture<Void> removeSubscription(@NotNull String clientID, @NotNull String topic);

    /**
     * This method removes subscriptions for a certain client and certain topics.
     * If HiveMQ is connected to a cluster, the subscriptions will be removed by other Cluster Nodes as well.
     * <p>
     *
     * @param clientID client, which should get unsubscribed
     * @param topics   topics from which the client should get unsubscribed
     * @return a {@link ListenableFuture} object that will succeed, as soon es the subscriptions were removed by all Cluster Nodes.
     * failing with a {@link RateLimitExceededException} if the plugin service rate limit was exceeded.
     * <p>
     * @throws NullPointerException     if clientID or topics is <code>null</code>.
     * @throws IllegalArgumentException if clientID or topics is empty.
     */
    ListenableFuture<Void> removeSubscriptions(@NotNull String clientID, @NotNull Set<String> topics);

    /**
     * This method returns all subscriptions this HiveMQ instance and all other nodes in a HiveMQ cluster,
     * as a {@link com.google.common.collect.Multimap} of client identifiers and topics.
     * <p/>
     * Please be aware that calling this method on HiveMQ instances with many subscriptions could have
     * negative performance effects.
     * <p/>
     * The returned Multimap is read-only and must not be modified.
     *
     * @return a {@link ListenableFuture} which contains a {@link com.google.common.collect.Multimap} of client identifiers and their topic subscriptions
     * failing with a {@link RateLimitExceededException} if the plugin service rate limit was exceeded.
     */
    @ReadOnly
    ListenableFuture<Multimap<String, Topic>> getSubscriptions();

    /**
     * Returns all MQTT client subscriber identifiers for a given topic, this HiveMQ instance and all other nodes in a HiveMQ cluster.
     * MQTT Wildcards are allowed.
     * <p/>
     * Don't pass <code>null</code> as topic. This method is lenient, so
     * it will just return an empty Set.
     * <p/>
     * The returned Set is read-only and must not be modified.
     *
     * @param topic the topic
     * @return a {@link ListenableFuture} which contains the client identifiers of all subscribers that subscribed to the topic
     * failing with a {@link RateLimitExceededException} if the plugin service rate limit was exceeded.
     */
    @ReadOnly
    ListenableFuture<Set<String>> getSubscribers(@NotNull String topic);

    /**
     * Returns all topics a client is subscribed to, on this HiveMQ instance and all other nodes in a HiveMQ cluster.
     * <p/>
     * If the client does not exist, an empty Set is returned.
     * <p/>
     * Don't pass <code>null</code> as clientId. This method is lenient, so
     * it will just return an empty Set.
     * <p/>
     * The returned Set is read-only and must not be modified.
     *
     * @param clientID of the client
     * @return a {@link ListenableFuture} which contains which contains all topics the client subscribed to
     * failing with a {@link RateLimitExceededException} if the plugin service rate limit was exceeded.
     */
    @ReadOnly
    ListenableFuture<Set<Topic>> getTopics(@NotNull String clientID);
}
