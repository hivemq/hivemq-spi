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

package com.hivemq.spi.services.configuration;

import com.hivemq.spi.services.configuration.validation.annotation.Validate;
import com.hivemq.spi.services.configuration.validation.validators.MaxClientIdValidator;
import com.hivemq.spi.services.configuration.validation.validators.ZeroablePositiveNumber;

/**
 * A Configuration service which allows to get information about the current MQTT configuration
 * and allows to change the global MQTT configuration of HiveMQ at runtime.
 *
 * @author Dominik Obermaier
 * @since 3.0
 */
public interface MqttConfigurationService {

    /**
     * @return the global maximum allowed client identifier length
     */
    int maxClientIdLength();

    /**
     * @return the global retry interval in seconds for resending unacknowledged MQTT packets
     */
    int retryInterval();

    /**
     * @return the global maximum in-flight queued messages per client
     */
    long maxQueuedMessages();

    /**
     * @return the global maximum timeout for idle TCP connections of clients which didn't send a CONNECT message.
     */
    long noConnectIdleTimeoutMillis();

    @Validate(MaxClientIdValidator.class)
    void setMaxClientIdLength(int maxClientIdLength);

    @Validate(value = ZeroablePositiveNumber.class, name = "retryInterval'")
    void setRetryInterval(int retryInterval);

    @Validate(value = ZeroablePositiveNumber.class, name = "max queued messages")
    void setMaxQueuedMessages(long maxQueuedMessages);

    @Validate(value = ZeroablePositiveNumber.class, name = "no connect packet idle timeout millis")
    void setNoConnectIdleTimeoutMillis(final long noConnectPacketIdleTimeoutMillis);
}
