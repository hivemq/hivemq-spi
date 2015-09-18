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

package com.hivemq.spi.message;

import com.google.common.base.Preconditions;
import com.hivemq.spi.annotations.NotNull;
import com.hivemq.spi.annotations.Nullable;

import java.io.Serializable;

/**
 * Represents a MQTT topic with a string for the topic name and a quality of service.
 *
 * @author Dominik Obermaier
 * @since 1.4
 */
public class Topic implements Serializable, Comparable<Topic> {

    /**
     * Name of the topic
     */
    private final String topic;

    /**
     * Quality of service of the topic
     */
    private final QoS qoS;


    public Topic(@NotNull final String topic, @Nullable final QoS qoS) {

        Preconditions.checkArgument(topic != null, "A Topic must not be null");
        this.topic = topic;
        this.qoS = qoS;
    }

    public static Topic topicFromString(@NotNull final String s) {
        return new Topic(s, null);
    }

    /**
     * @return the topic as String representation
     */
    @NotNull
    public String getTopic() {
        return topic;
    }

    /**
     * @return the QoS of a Topic
     */
    @Nullable
    public QoS getQoS() {
        return qoS;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Topic topic1 = (Topic) o;

        if (!topic.equals(topic1.topic)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return topic.hashCode();
    }


    @Override
    public String toString() {
        return "Topic{" +
                "topic='" + topic + '\'' +
                ", qoS=" + qoS +
                '}';
    }

    @Override
    public int compareTo(final Topic o) {

        return this.topic.compareTo(o.getTopic());
    }
}
