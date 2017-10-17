/*
 * Copyright 2017 dc-square GmbH
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

package com.hivemq.spi.services.exception;

/**
 * This exception is used to signal that a HiveMQ cluster contains an instance which is incompatible to the called method.
 *
 * @author Georg Held
 * @since 3.3.0
 */
public class IncompatibleHiveMQVersionException extends RuntimeException {

    private final String minimumNeededVersion;
    private final boolean fillInStacktrace;

    /**
     * Creates a new IncompatibleHiveMQVersionException.
     *
     * @param minimumNeededVersion the minimum version that all nodes in a HiveMQ cluster need for the called method.
     */
    public IncompatibleHiveMQVersionException(final String minimumNeededVersion) {
        this(minimumNeededVersion, false);
    }

    /**
     * Creates a new IncompatibleHiveMQVersionException.
     *
     * @param minimumNeededVersion the minimum version that all nodes in a HiveMQ cluster need for the called method.
     * @param fillInStacktrace     whether the created exception should fill in a stacktrace.
     */
    public IncompatibleHiveMQVersionException(final String minimumNeededVersion, final boolean fillInStacktrace) {
        this.minimumNeededVersion = minimumNeededVersion;
        this.fillInStacktrace = fillInStacktrace;
    }

    /**
     * Returns the minimum needed version of the HiveMQ broker.
     *
     * @return the minimum needed version (e.g. 3.3.0)
     */
    public String getMinimumNeededVersion() {
        return minimumNeededVersion;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        if (fillInStacktrace) {
            return super.fillInStackTrace();
        }
        return this;
    }

}
