package com.dcsquare.hivemq.spi.services;

import com.dcsquare.hivemq.spi.message.RetainedMessage;
import com.google.common.base.Optional;

import java.util.Set;

/**
 * The retained message store allows the management of retained messages from within plugins
 *
 * @author Lukas Brandl
 * @since 1.5
 */
public interface RetainedMessageStore {

    /**
     * @return all retained messages which are currently stored
     */
    public Set<RetainedMessage> getRetainedMessages();

    /**
     * @param topic a topic
     * @return retained message for the specific topic, otherwise an {@link com.google.common.base.Optional}
     *         instance with an empty reference
     */
    public Optional<RetainedMessage> getRetainedMessage(String topic);

    /**
     * Removes the retained message from given topic. <code>null</code> values are ignored.
     * If there isn't any retained message on the topic yet, nothing will happen.
     *
     * @param topic from which the message should be removed
     */
    public void remove(String topic);

    /**
     * Removes a given retained message. <code>null</code> values are ignored.
     * If the given retained message doesn't exist, nothing will happen.
     *
     * @param retainedMessage which should be removed
     */
    public void remove(RetainedMessage retainedMessage);

    /**
     * Removes all retained messages from the message store.
     */
    public void clear();

    /**
     * This method adds or replaces a retained message
     *
     * @param retainedMessage which should be added or replaced
     */
    public void addOrReplace(RetainedMessage retainedMessage);

    /**
     * Checks if the retained message is present in the retained message store.
     * Only the topic is of a retained message is considered for this check, QoS and message are ignored.
     *
     * @param retainedMessage to check if it's already in the message store
     * @return true if there's already a message on the topic of the given retained message
     */
    public boolean contains(RetainedMessage retainedMessage);

    /**
     * @return the number of all retained messages
     */
    public int size();
}
