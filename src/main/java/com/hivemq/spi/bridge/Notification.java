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

import com.hivemq.spi.message.QoS;

/**
 * A concrete notification which consists of a topic and a quality of service level
 *
 * @author Dominik Obermaier
 * @since 2.0
 */
public class Notification {

    /**
     * The default notification topic on remote brokers:
     * <code>$SYS/broker/connection/#{clientId}/state</code>
     */
    public final static String DEFAULT_NOTIFICATION_TOPIC = "$SYS/broker/connection/#{clientId}/state";

    private String topic;

    private QoS qoS;

    public Notification(final String topic, final QoS qoS) {
        this.topic = topic;
        this.qoS = qoS;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(final String topic) {
        this.topic = topic;
    }

    public QoS getQoS() {
        return qoS;
    }

    public void setQoS(final QoS qoS) {
        this.qoS = qoS;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Notification that = (Notification) o;

        if (qoS != that.qoS) return false;
        if (topic != null ? !topic.equals(that.topic) : that.topic != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = topic != null ? topic.hashCode() : 0;
        result = 31 * result + (qoS != null ? qoS.hashCode() : 0);
        return result;
    }
}
