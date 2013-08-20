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
public class REMOTE_PUBLISH extends PUBLISH {

    private final PUBLISH original;

    public REMOTE_PUBLISH(final PUBLISH original) {

        this.original = original;
    }

    @Override
    public byte[] getPayload() {
        return original.getPayload();
    }

    @Override
    public String getTopic() {
        return original.getTopic();
    }

    @Override
    public int getMessageId() {
        return original.getMessageId();
    }

    @Override
    public QoS getQoS() {
        return original.getQoS();
    }

    @Override
    public boolean isRetain() {
        return original.isRetain();
    }

    @Override
    public boolean isDuplicateDelivery() {
        return original.isDuplicateDelivery();
    }

    @Override
    public boolean equals(final Object o) {
        return original.equals(o);
    }

    @Override
    public int hashCode() {
        return original.hashCode();
    }

}
