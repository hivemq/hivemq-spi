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

/**
 * A retained message
 *
 * @author Lukas Brandl
 */
public class RetainedMessage {
    private String topic;
    private byte[] message;
    private QoS qoS;

    public RetainedMessage(String topic, byte[] message, QoS qoS) {
        this.topic = topic;
        this.message = message;
        this.qoS = qoS;
    }


    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public byte[] getMessage() {
        return message;
    }

    public void setMessage(byte[] message) {
        this.message = message;
    }

    public QoS getQoS() {
        return qoS;
    }

    public void setQoS(QoS qoS) {
        this.qoS = qoS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RetainedMessage that = (RetainedMessage) o;

        if (topic != null ? !topic.equals(that.topic) : that.topic != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return topic != null ? topic.hashCode() : 0;
    }
}
