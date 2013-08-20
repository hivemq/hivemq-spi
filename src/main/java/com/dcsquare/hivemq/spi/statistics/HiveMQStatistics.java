/*
 * Copyright 2013 dc-square GmbH
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

package com.dcsquare.hivemq.spi.statistics;

/**
 * General statistics about the HiveMQ instance.
 *
 * @author Dominik Obermaier
 * @since 1.4
 */
public interface HiveMQStatistics {

    /**
     * @return the broker uptime in seconds
     */
    long getBrokerUptime();

    /**
     * Adds the given amount of bytes to the global counter of total received bytes
     *
     * @param received the amount of bytes to add
     */
    void addBytesReceived(long received);

    /**
     * @return the total bytes received by the broker
     */
    long getBytesReceived();

    /**
     * Adds the given amount of MQTT messages to the global counter of total received MQTT messages
     *
     * @param received the amount of received MQTT messages to add to the global counter
     */
    void addMessagesReceived(long received);

    /**
     * @return the total amount of MQTT messages received
     */
    long getMessagesReceived();

    /**
     * Adds the given amount of MQTT PUBLISH messages to the global counter of total received MQTT PUBLISH messages
     *
     * @param received the amount of received MQTT PUBLISH messages to add to the global counter
     */
    void addPublishesReceived(long received);

    /**
     * @return the total amount of MQTT PUBLISH messages received
     */
    long getPublishesReceived();

    /**
     * Adds the given amount of bytes to the global counter of total sent bytes
     *
     * @param sent the amount of bytes to add
     */
    void addBytesSent(long sent);

    /**
     * @return the total bytes sent by the broker
     */
    long getBytesSent();

    /**
     * Adds the given amount of MQTT messages to the global counter of total sent MQTT messages
     *
     * @param sent the amount of sent MQTT messages to add to the global counter
     */
    void addMessagesSent(long sent);

    /**
     * @return the total amount of MQTT messages sent by the broker
     */
    long getMessagesSent();

    /**
     * Adds the given amount of MQTT PUBLISH messages to the global counter of total sent MQTT PUBLISH messages
     *
     * @param sent the amount of sent MQTT PUBLISH messages to add to the global counter
     */
    void addPublishesSent(long sent);

    /**
     * @return the total amount of MQTT PUBLISH messages sent by the broker
     */
    long getPublishesSent();

    /**
     * @return the number of currently connected (= active) clients
     */
    long getActiveClients();

    /**
     * @return the maximum number of currently connected (= active) clients since the broker started
     */
    long getMaxActiveClients();

    /**
     * @return the number of currently disconnected clients which have a persistent session on the broker
     */
    long getInactiveClients();

    /**
     * @return the sum of all active and inactive clients on the broker
     */
    long getTotalClients();

    /**
     * @return the total number of subscriptions on the broker
     */
    long getTotalSubscriptions();

    /**
     * @return the total number of retained messages stored on the broker
     */
    long getTotalRetainedMessages();

    /**
     * @return the total number of retained messages and stored messages for inactive clients
     */
    long getStoredMessages();

    /**
     * @return the current heap size on the broker
     */
    long getHeapSize();

    /**
     * @return the maximum possible heap size on the broker
     */
    long getMaxHeapSize();

    /**
     * @return the maximum used heap size on the broker
     */
    long getMaxUsedHeapSize();
}
