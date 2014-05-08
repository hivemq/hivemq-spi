package com.dcsquare.hivemq.spi.services;

import com.dcsquare.hivemq.spi.topic.sys.SYSTopicEntry;
import com.dcsquare.hivemq.spi.topic.sys.Type;
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
     *         <code>false</code> otherwise
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


}
