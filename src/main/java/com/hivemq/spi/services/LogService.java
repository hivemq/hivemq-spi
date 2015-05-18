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
 * of HiveMQ at runtime
 *
 * @author Dominik Obermaier
 */
public interface LogService {

    /**
     * The loglevels HiveMQ supports
     */
    enum LogLevel {

        TRACE,
        DEBUG,
        INFO,
        WARN,
        ERROR

    }

    /**
     * Changes the log level of the internal HiveMQ logger
     * <p/>
     * This does not support <code>null</code> parameters. If you pass
     * <code>null</code>, this method is lenient and will ignore it
     *
     * @param logLevel the new loglevel
     */
    void setLogLevel(@NotNull LogLevel logLevel);

    /**
     * Returns the current loglevel of the internal HiveMQ logger
     *
     * @return the current loglevel of the internal HiveMQ logger
     */
    LogLevel getLogLevel();
}
