package com.dcsquare.hivemq.spi.services;

import com.dcsquare.hivemq.spi.message.RetainedMessage;
import com.google.common.base.Optional;

import java.util.Set;

/**
 * @author Lukas Brandl
 */
public interface RetainedMessageStore {

    public Set<RetainedMessage> getRetainedMessages();

    public Optional<RetainedMessage> getRetainedMessage(String topic);

    public void remove(String topic);

    public void remove(RetainedMessage retainedMessage);

    public void clear();

    public void addOrReplace(RetainedMessage retainedMessage);

    public boolean contains(RetainedMessage retainedMessage);

    public int size();
}
