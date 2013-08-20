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
public class CONNACK extends Message {


    private final ReturnCode returnCode;

    public CONNACK(final ReturnCode returnCode) {
        this.returnCode = returnCode;
    }

    public ReturnCode getReturnCode() {
        return returnCode;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final CONNACK connack = (CONNACK) o;

        if (returnCode != connack.returnCode) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return returnCode != null ? returnCode.hashCode() : 0;
    }
}
