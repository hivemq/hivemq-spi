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
 * The MQTT CONNACK message
 *
 * @author Dominik Obermaier
 * @since 1.4
 */
public class CONNACK implements Message {


    private final ReturnCode returnCode;

    /**
     * Session Present is a MQTT 3.1.1 Feature and
     * is ignored in MQTT 3.1 environments.
     *
     * @see <a href="http://docs.oasis-open.org/mqtt/mqtt/v3.1.1/mqtt-v3.1.1.html#_Toc385349255">
     * MQTT Session Present Flag</a>
     */
    private boolean sessionPresent;

    public CONNACK(final ReturnCode returnCode) {
        this(returnCode, false);
    }

    public CONNACK(final ReturnCode returnCode, final boolean sessionPresent) {

        if (returnCode != ReturnCode.ACCEPTED && sessionPresent) {
            throw new IllegalArgumentException("The sessionPresent flag is only allowed for return code " + ReturnCode.ACCEPTED);
        }
        this.returnCode = returnCode;
        this.sessionPresent = sessionPresent;
    }

    /**
     * @return the {@link ReturnCode} of the CONNACK message
     */
    public ReturnCode getReturnCode() {
        return returnCode;
    }

    /**
     * Returns <code>true</code> if there is already a session present on the
     * MQTT Broker for a client. Returns <false></false> if the client
     * has a clean session
     *
     * @return if there is a session present on the MQTT broker
     * @since 2.1.0
     */
    public boolean isSessionPresent() {
        return sessionPresent;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final CONNACK connack = (CONNACK) o;

        if (sessionPresent != connack.sessionPresent) return false;
        if (returnCode != connack.returnCode) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = returnCode.hashCode();
        result = 31 * result + (sessionPresent ? 1 : 0);
        return result;
    }
}
