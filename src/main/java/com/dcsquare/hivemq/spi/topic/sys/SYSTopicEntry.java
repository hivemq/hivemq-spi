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

package com.dcsquare.hivemq.spi.topic.sys;

import com.google.common.base.Supplier;

/**
 * An implementation of this interface representa a concrete $SYS topic entry
 * which can be added to the {@link com.dcsquare.hivemq.spi.services.SYSTopicService}.
 *
 * @author Dominik Obermaier
 * @since 2.0
 */
public interface SYSTopicEntry {


    /**
     * Returns the topic of the $SYS Topic Entry.
     * <p/>
     * Must start with '$SYS/'
     *
     * @return the topic of the entry
     */
    String topic();

    /**
     * Returns a {@link Supplier} for the payload of this $SYS topic. The supplier will be called
     * every time the broker publishes on the $SYS topics.
     * <p/>
     * If the payload is static or if you need to cache, you can consider using the utility methods in the
     * {@link com.google.common.base.Suppliers} util class.
     *
     * @return a supplier for the payload of this $SYS topic
     */
    Supplier<byte[]> payload();

    /**
     * Returns the Type of this $SYS topic
     *
     * @return the {@link Type} of this $SYS topics
     */
    Type type();

    /**
     * Returns a description of this $SYS topic
     *
     * @return a textual description of this $SYS topic
     */
    String description();

}
