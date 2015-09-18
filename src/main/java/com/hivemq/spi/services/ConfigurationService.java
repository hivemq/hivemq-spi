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

package com.hivemq.spi.services;

import com.hivemq.spi.services.configuration.GeneralConfigurationService;
import com.hivemq.spi.services.configuration.MqttConfigurationService;
import com.hivemq.spi.services.configuration.ThrottlingConfigurationService;
import com.hivemq.spi.services.configuration.listener.ListenerConfigurationService;

/**
 * The Configuration Service interface which allows to change HiveMQ configuration
 * programmatically.
 * <p/>
 * Runtime configuration changes via plugins are possible for most of the configuration subsystem services.
 *
 * @author Dominik Obermaier
 * @since 3.0
 */

public interface ConfigurationService {

    /**
     * Returns the configuration service for general HiveMQ configuration
     *
     * @return the general HiveMQ configuration service
     */
    GeneralConfigurationService generalConfiguration();

    /**
     * Returns the configuration service which allows to add and inspect listeners add runtime.
     *
     * @return the listener configuration service
     */
    ListenerConfigurationService listenerConfiguration();

    /**
     * Returns the configuration service for MQTT configuration
     *
     * @return the mqtt configuration service
     */
    MqttConfigurationService mqttConfiguration();

    /**
     * Returns the throttling configuration service for global throttling
     *
     * @return the global throttling service
     */
    ThrottlingConfigurationService throttlingConfiguration();

}


