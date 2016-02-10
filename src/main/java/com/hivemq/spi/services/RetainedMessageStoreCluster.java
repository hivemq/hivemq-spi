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
     * Checks if the retained message is present in the retained message store.
     * Only the topic is of a retained message is considered for this check, QoS and message are ignored.
     *
     * @param retainedMessage to check if it's already in the message store
     * @return true if there's already a message on the topic of the given retained message
     */
    ListenableFuture<Boolean> contains(RetainedMessage retainedMessage);

    /**
     * @return the number of all retained messages
     */
    ListenableFuture<Integer> size();

}
