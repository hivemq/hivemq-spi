package com.hivemq.spi.callback.events;

import com.hivemq.spi.callback.SynchronousCallback;
import com.hivemq.spi.message.SubackReturnCode;
import com.hivemq.spi.message.Topic;
import com.hivemq.spi.security.ClientData;

/**
 * This callback returns the {@link SubackReturnCode} for a {@link Topic} and a {@link ClientData}.
 * <p/>
 * This callback is called after the {@link com.hivemq.spi.callback.security.OnAuthorizationCallback} but before the {@link OnSubscribeCallback}.
 * When more {@link OnTopicSubscriptionCallback}s are added the one that fires last (with the least priority)
 * has the final say.
 * It is possible to override the <a href="http://docs.oasis-open.org/mqtt/mqtt/v3.1.1/os/mqtt-v3.1.1-os.html#_Toc398718067">MQTT specification</a>
 * for SUBACK payloads with this callback.
 * <p/>
 * This callback is called once for every topic for every SUBSCRIBE,
 * it is <strong>highly</strong> recommended to make the implementation as efficient as possible.
 *
 * @author Georg Held
 * @since 3.2
 */
public interface OnTopicSubscriptionCallback extends SynchronousCallback {

    /**
     * Returns a {@link SubackReturnCode} for a {@link Topic}, a previous {@link SubackReturnCode} and a {@link ClientData}.
     *
     * @param topic               the topic to which the client wants to subscribe
     * @param authorizationResult the result of a {@link OnTopicSubscriptionCallback} with higher priority if available,
     *                            or of the last registered {@link com.hivemq.spi.callback.security.OnAuthorizationCallback} if available,
     *                            or the QoS the client requested.
     * @param clientData          information about the client
     * @return the {@link SubackReturnCode} that corresponds to the topic in the {@link com.hivemq.spi.message.SUBACK} to be sent to the client
     */
    SubackReturnCode getSubackReturnCodeForClient(final Topic topic, final SubackReturnCode authorizationResult, final ClientData clientData);
}
