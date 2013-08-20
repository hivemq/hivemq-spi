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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dominik Obermaier
 * @since 1.4
 */
public class SUBACK extends MessageWithID {

    private List<Byte> grantedQos;

    public SUBACK() {
        this(0, new ArrayList<Byte>());
    }

    public SUBACK(final int messageId) {
        this(messageId, new ArrayList<Byte>());
    }

    public SUBACK(final int messageId, final List<Byte> grantedQos) {
        super(messageId);
        this.grantedQos = grantedQos;
    }

    public List<Byte> getGrantedQos() {
        return grantedQos;
    }

    public void setGrantedQos(final List<Byte> grantedQos) {
        this.grantedQos = grantedQos;
    }
}
