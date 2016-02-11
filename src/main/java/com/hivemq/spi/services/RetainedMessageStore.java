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

package com.hivemq.spi.services;

import com.google.common.base.Optional;
import com.hivemq.spi.message.RetainedMessage;

import java.util.Set;

/**
 * The retained message store allows the management of retained messages from within plugins
 *
 * @author Lukas Brandl
 * @since 1.5
 * @deprecated Use RetainedMessageStoreLocal or RetainedMessageStoreCluster instead.
 */
@Deprecated
public interface RetainedMessageStore {

    /**
     * @return all retained messages which are currently stored
     */
    @Deprecated
    Set<RetainedMessage> getRetainedMessages();

    /**
     * @param topic a topic
     * @return retained message for the specific topic, otherwise an {@link com.google.common.base.Optional}
     * instance with an empty reference
     * @deprecated Use RetainedMessageStoreLocal or RetainedMessageStoreCluster instead.
     */
    @Deprecated
    Optional<RetainedMessage> getRetainedMessage(String topic);

    /**
     * Removes the retained message from given topic. <code>null</code> values are ignored.
     * If there isn't any retained message on the topic yet, nothing will happen.
     *
     * @param topic from which the message should be removed
     * @deprecated Use RetainedMessageStoreLocal or RetainedMessageStoreCluster instead.
     */
    @Deprecated
    void remove(String topic);

    /**
     * Removes a given retained message. <code>null</code> values are ignored.
     * If the given retained message doesn't exist, nothing will happen.
     *
     * @param retainedMessage which should be removed
     * @deprecated Use RetainedMessageStoreLocal or RetainedMessageStoreCluster instead.
     */
    @Deprecated
    void remove(RetainedMessage retainedMessage);

    /**
     * Removes all retained messages from the message store.
     *
     * @deprecated Use RetainedMessageStoreLocal or RetainedMessageStoreCluster instead.
     */
    @Deprecated
    void clear();

    /**
     * This method adds or replaces a retained message
     *
     * @param retainedMessage which should be added or replaced
     * @deprecated Use RetainedMessageStoreLocal or RetainedMessageStoreCluster instead.
     */
    @Deprecated
    void addOrReplace(RetainedMessage retainedMessage);

    /**
     * Checks if the retained message is present in the retained message store.
     * Only the topic is of a retained message is considered for this check, QoS and message are ignored.
     *
     * @param retainedMessage to check if it's already in the message store
     * @return true if there's already a message on the topic of the given retained message
     * @deprecated Use RetainedMessageStoreLocal or RetainedMessageStoreCluster instead.
     */
    @Deprecated
    boolean contains(RetainedMessage retainedMessage);

    /**
     * @return the number of all retained messages
     * @deprecated Use RetainedMessageStoreLocal or RetainedMessageStoreCluster instead.
     */
    @Deprecated
    int size();
}
