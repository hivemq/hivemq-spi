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
     * @return all retained messages, which are currently stored
     */
    public Set<RetainedMessage> getRetainedMessages();

    /**
     * @param topic a topic
     * @return retained message for the specific topic, otherwise Optional.absent
     */
    public Optional<RetainedMessage> getRetainedMessage(String topic);

    /**
     * This removes the retained message of the given topic
     *
     * @param topic from which the message should be removed
     */
    public void remove(String topic);

    /**
     * This removes the retained message
     *
     * @param retainedMessage which should be removed
     */
    public void remove(RetainedMessage retainedMessage);

    /**
     * Removes all retained messages
     */
    public void clear();

    /**
     * This method adds or replaces a retained message to a topic with a message and QoS
     * @param retainedMessage which should be added
     */
    public void addOrReplace(RetainedMessage retainedMessage);

    /**
     * Checks if the retained message is present in the retained message store
     * @param retainedMessage which should be checked
     * @return true if the message is present, otherwise false
     */
    public boolean contains(RetainedMessage retainedMessage);

    /**
     * @return the number of all retained messages
     */
    public int size();
}
