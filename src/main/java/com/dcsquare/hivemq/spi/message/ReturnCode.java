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
 * The return code of a {@link CONNACK} message.
 *
 * @author Dominik Obermaier
 * @author Christian Goetz
 * @since 1.4
 */
public enum ReturnCode {
    ACCEPTED(0),
    REFUSED_UNACCEPTABLE_PROTOCOL_VERSION(1),
    REFUSED_IDENTIFIER_REJECTED(2),
    REFUSED_SERVER_UNAVAILABLE(3),
    REFUSED_BAD_USERNAME_OR_PASSWORD(4),
    REFUSED_NOT_AUTHORIZED(5);

    private final int code;

    ReturnCode(int code) {

        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static ReturnCode with(final int code) {
        for (ReturnCode returnCode : ReturnCode.values()) {
            if (returnCode.code == code) {
                return returnCode;
            }
        }
        throw new IllegalArgumentException("No Return code with value " + code + " defined");
    }
}