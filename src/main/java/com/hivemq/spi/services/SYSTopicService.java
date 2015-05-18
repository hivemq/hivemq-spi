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

import com.hivemq.spi.topic.sys.SYSTopicEntry;
import com.hivemq.spi.topic.sys.Type;
import com.google.common.base.Optional;

import java.util.Collection;

/**
 * This Service allows plugin developers to
 * <p/>
 * * Add custom $SYS topics at runtime      <br/>
 * * Remove existing $SYS topics at runtime <br/>
 * * Query for existing $SYS topics at runtime   <br/>
 * <p/>
 *
 * @author Dominik Obermaier
 * @author Christoph Schäbel
 * @since 2.0
 */
public interface SYSTopicService {

    /**
     * Returns a Collection of all {@link SYSTopicEntry}s topics available.
     * It is possible to filter the returned list by {@link Type}.
     * By default, this method will return all {@link SYSTopicEntry}s.
     *
     * @param types the {@link Type}s to filter
     * @return a Collection of all $SYS topics registered to HiveMQ of the given Types
     */
    Collection<SYSTopicEntry> getAllEntries(Type... types);

    /**
     * Checks if the SYSTopicService contains a given {@link SYSTopicEntry}.
     *
     * @param entry the {@link SYSTopicEntry} to check
     * @return <code>true</code> if the Service contains the given {@link SYSTopicEntry},
     * <code>false</code> otherwise
     */
    boolean contains(final SYSTopicEntry entry);

    /**
     * Returns the {@link SYSTopicEntry} for a given topic.
     *
     * @param topic the topic
     * @return an {@link Optional} of a {@link SYSTopicEntry}
     */
    Optional<SYSTopicEntry> getEntry(String topic);

    /**
     * Adds a new $SYS topic entry.
     * The topic of the given {@link SYSTopicEntry} must start with '$SYS'.
     *
     * @param entry a new {@link SYSTopicEntry} to add
     */
    void addEntry(final SYSTopicEntry entry);

    /**
     * Removes a {@link SYSTopicEntry}
     *
     * @param entry the entry to remove
     * @return <code>true</code> if the {@link SYSTopicEntry} could be removed, <code>false</code> otherwise
     */
    boolean removeEntry(final SYSTopicEntry entry);

    /**
     * Triggers a PUBLISH of all registered SYSTopicEntries with {@link Type} STANDARD to all clients which are subscribed to the SYSTopics
     *
     * @since 3.0
     */
    void triggerStandardSysTopicPublish();

    /**
     * Triggers a PUBLISH of all registered SYSTopicEntries with {@link Type} STATIC to a specified client.
     * The client receives all messages from topics it is subscribed on.
     *
     * @param clientId The clientid for which to trigger the publishes
     * @since 3.0
     */
    void triggerStaticSysTopicPublishToClient(final String clientId);

    /**
     * Triggers a PUBLISH of all registered SYSTopicEntries with {@link Type} STANDARD to a specified client.
     * The client receives all messages from topics it is subscribed on.
     *
     * @param clientId The clientid for which to trigger the publishes
     * @since 3.0
     */
    void triggerStandardSysTopicPublishToClient(final String clientId);

}
