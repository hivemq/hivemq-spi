package com.hivemq.spi.callback.events;

import com.hivemq.spi.callback.SynchronousCallback;
import com.hivemq.spi.message.Topic;
import com.hivemq.spi.security.ClientData;

/**
 * @author Georg Held
 */
public interface OnTopicSubscriptionCallback extends SynchronousCallback {

    SubackReturnCode getSubackReturnCodeForClient(final Topic topic, final SubackReturnCode authorizationResult, final ClientData clientData);
}
