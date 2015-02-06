/*
 * Copyright 2015 dc-square GmbH
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

package com.dcsquare.hivemq.spi.services.configuration.entity;

/**
 * @author Dominik Obermaier
 */
public class Mqtt extends OverridableConfiguration {

    private Value<Integer> maxClientIDLength;
    private Value<Integer> retryInterval;
    private Value<Integer> maxQueuedMessages;

    public Mqtt(final Value<Integer> maxClientIDLength,
                final Value<Integer> retryInterval,
                final Value<Integer> maxQueuedMessages) {
        super(true);
        this.maxClientIDLength = maxClientIDLength;
        this.retryInterval = retryInterval;
        this.maxQueuedMessages = maxQueuedMessages;
    }

    public Value<Integer> getMaxClientIDLength() {
        return maxClientIDLength;
    }

    public Value<Integer> getRetryInterval() {
        return retryInterval;
    }

    public Value<Integer> getMaxQueuedMessages() {
        return maxQueuedMessages;
    }
}