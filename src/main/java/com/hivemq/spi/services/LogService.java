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


import com.hivemq.spi.annotations.NotNull;

/**
 * The logservice which allows to change the internal logger
 * of HiveMQ at runtime programmatically
 *
 * @author Dominik Obermaier
 * @since 3.0
 */
public interface LogService {

    /**
     * The loglevels HiveMQ supports
     */
    enum LogLevel {

        /**
         * The TRACE log level for finest HiveMQ logging
         */
        TRACE,
        /**
         * The DEBUG log level for fine HiveMQ logging
         */
        DEBUG,
        /**
         * The INFO log level. INFO logging is the default HiveMQ log behaviour
         */
        INFO,
        /**
         * The WARN log level which only logs warnings
         */
        WARN,
        /**
         * The ERROR log level which only logs severe HiveMQ errors
         */
        ERROR

    }

    /**
     * Changes the log level of the internal HiveMQ logger
     * <p/>
     * This does not support <code>null</code> parameters. If you pass
     * <code>null</code>, this method is lenient and will ignore the parameter
     *
     * @param logLevel the new loglevel
     */
    void setLogLevel(@NotNull LogLevel logLevel);

    /**
     * Returns the current log level of the internal HiveMQ logger
     *
     * @return the current log level of the internal HiveMQ logger
     */
    LogLevel getLogLevel();
}
