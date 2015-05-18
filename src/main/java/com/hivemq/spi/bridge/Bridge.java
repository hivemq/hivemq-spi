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

package com.hivemq.spi.bridge;

import java.util.List;

/**
 * A concrete bridge configuration.
 *
 * @author Dominik Obermaier
 * @since 2.0
 */
public class Bridge {


    private List<Address> addresses;

    private List<TopicPattern> topicPatterns;

    private boolean cleanSession = false;

    private String clientId;

    private String connectionName;

    private int keepAlive;

    private int idleTimeout;

    private String username;

    private String password;

    private int restartTimeout;

    private boolean roundRobin = true;

    private StartType startType = StartType.AUTOMATIC;

    private int threshold;

    private boolean tryPrivate = true;

    private boolean notificationsEnabled = true;

    private Notification notification;


    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(final List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<TopicPattern> getTopicPatterns() {
        return topicPatterns;
    }

    public void setTopicPatterns(final List<TopicPattern> topicPatterns) {
        this.topicPatterns = topicPatterns;
    }

    public boolean isCleanSession() {
        return cleanSession;
    }

    public void setCleanSession(final boolean cleanSession) {
        this.cleanSession = cleanSession;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(final String clientId) {
        this.clientId = clientId;
    }

    public String getConnectionName() {
        return connectionName;
    }

    public void setConnectionName(final String connectionName) {
        this.connectionName = connectionName;
    }

    public int getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(int keepAlive) {
        this.keepAlive = keepAlive;
    }

    public int getIdleTimeout() {
        return idleTimeout;
    }

    public void setIdleTimeout(final int idleTimeout) {
        this.idleTimeout = idleTimeout;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public int getRestartTimeout() {
        return restartTimeout;
    }

    public void setRestartTimeout(final int restartTimeout) {
        this.restartTimeout = restartTimeout;
    }

    public boolean isRoundRobin() {
        return roundRobin;
    }

    public void setRoundRobin(final boolean roundRobin) {
        this.roundRobin = roundRobin;
    }

    public StartType getStartType() {
        return startType;
    }

    public void setStartType(final StartType startType) {
        this.startType = startType;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(final int threshold) {
        this.threshold = threshold;
    }

    public boolean isTryPrivate() {
        return tryPrivate;
    }

    public void setTryPrivate(final boolean tryPrivate) {
        this.tryPrivate = tryPrivate;
    }

    public boolean isNotificationsEnabled() {
        return notificationsEnabled;
    }

    public void setNotificationsEnabled(final boolean notificationsEnabled) {
        this.notificationsEnabled = notificationsEnabled;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }
}
