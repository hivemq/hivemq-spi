package com.hivemq.spi.callback.events;

import com.hivemq.spi.callback.SynchronousCallback;
import com.hivemq.spi.callback.exception.BeforePublishSendException;
import com.hivemq.spi.message.ModifiablePUBLISH;
import com.hivemq.spi.message.PUBLISH;
import com.hivemq.spi.security.ClientData;

/**
 * Gets called just before a {@link PUBLISH} message is sent to a client.
 * <p>
 * <b>This callback gets called very often, be sure to have a efficient implementation.</b>
 * <p>
 * This callback allows the modification of MQTT {@link PUBLISH} messages at the last moment before they are sent to the client.
 * <p>
 * If more than one BeforePublishSendCallback is registered, they will get called ordered by decreasing {@link com.hivemq.spi.callback.CallbackPriority}. If an exception is thrown in a {@link BeforePublishSendCallback}, all pending callbacks will not be executed.
 *
 * @author Georg Held
 * @author Christoph Schaebel
 * @since 3.3
 */
public interface BeforePublishSendCallback extends SynchronousCallback {

    /**
     * @param publish    A {@link ModifiablePUBLISH} that will be sent to the client or the result of BeforePublishSendCallbacks with higher {@link com.hivemq.spi.callback.CallbackPriority}.
     * @param clientData The client {@link ClientData} for the client, to which this {@link PUBLISH} will be sent to.
     * @throws BeforePublishSendException if the underlying PUBLISH should be dropped. All pending {@link BeforePublishSendCallback}s will not be executed.
     */
    void beforePublishSend(ModifiablePUBLISH publish, ClientData clientData) throws BeforePublishSendException;
}
