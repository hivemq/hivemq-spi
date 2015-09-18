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

import java.util.ArrayList;
import java.util.List;

/**
 * The MQTT UNSUBSCRIBE message
 *
 * @author Dominik Obermaier
 * @since 1.4
 */
public class UNSUBSCRIBE extends MessageWithID {


    private List<String> topics;

    public UNSUBSCRIBE() {
        super(0);
        topics = new ArrayList<>();
    }

    /**
     * @return a list of topic the client wants to unsubscribe to
     */
    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(final List<String> topics) {
        this.topics = topics;
    }
}
