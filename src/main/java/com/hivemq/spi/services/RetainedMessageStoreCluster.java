package com.hivemq.spi.services;

import com.google.common.util.concurrent.ListenableFuture;
import com.hivemq.spi.message.RetainedMessage;

import java.util.Set;

/**
 * The retained message store allows the management of retained messages from within plugins
 *
 * @author Lukas Brandl
 */
public interface RetainedMessageStoreCluster {

    /**
     * @return all retained messages which are currently stored
     */
    ListenableFuture<Set<RetainedMessage>> getRetainedMessages();

    /**
     * @param topic a topic
     * @return retained message for the specific topic or <code>null</code>.
     * instance with an empty reference
     */
    ListenableFuture<RetainedMessage> getRetainedMessage(String topic);

    /**
     * Removes the retained message from given topic.
     * If there isn't any retained message on the topic yet, nothing will happen.
     *
     * @param topic from which the message should be removed
     */
    ListenableFuture<Void> remove(String topic);

    /**
     * Removes all retained messages from the message store.
     */
    ListenableFuture<Void> clear();

    /**
     * This method adds or replaces a retained message
     *
     * @param retainedMessage which should be added or replaced
     */
    ListenableFuture<Void> addOrReplace(RetainedMessage retainedMessage);

    /**
     * Checks if a retained message is present in the retained message store.
     *
     * @param topic the topic associated with the retained message
     * @return true if there's a message for the given topic
     */
    ListenableFuture<Boolean> contains(String topic);

    /**
     * @return the number of all retained messages
     */
    ListenableFuture<Long> size();

}
