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

package com.dcsquare.hivemq.spi.message;

/**
 * @author Dominik Obermaier
 * @since 1.4
 */
public class PUBLISH extends Message {

    private byte[] payload;

    private String topic;

    private int messageId;

    public byte[] getPayload() {
        return payload;
    }

    public void setPayload(final byte[] payload) {
        this.payload = payload;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(final String topic) {
        this.topic = topic;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(final int messageId) {
        this.messageId = messageId;
    }

    public static PUBLISH copy(final PUBLISH original) {
        final PUBLISH publish = new PUBLISH();
        publish.setQoS(original.getQoS());
        publish.setRetain(original.isRetain());
        publish.setPayload(original.getPayload());
        publish.setTopic(original.getTopic());
        publish.setDuplicateDelivery(original.isDuplicateDelivery());
        publish.setMessageId(original.getMessageId());
        return publish;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof PUBLISH)) return false;

        final PUBLISH publish = (PUBLISH) o;

        if (messageId != publish.messageId) return false;
        if (topic != null ? !topic.equals(publish.topic) : publish.topic != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = topic != null ? topic.hashCode() : 0;
        result = 31 * result + messageId;
        return result;
    }
}
