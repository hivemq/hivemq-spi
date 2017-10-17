package com.hivemq.spi.message;

/**
 * This interface allows the safe modification of a MQTT PUBLISH message, in the {@link com.hivemq.spi.callback.events.BeforePublishSendCallback}.
 *
 * @author Georg Held
 * @author Christoph Schaebel
 * @since 3.3
 */
public interface ModifiablePUBLISH {
    /**
     * @return the payload of the message. Do not modify the returned {@code byte[]} directly, as the resulting behavior is undefined and can have far reaching consequences, use {@code setPayload()} method instead.
     */
    public byte[] getPayload();

    /**
     * By using this setter the payload of this {@link PUBLISH} can be modified before it is sent to a client
     * <p>
     * Changes made to the payload here are only visible to the specific client to which this {@link ModifiablePUBLISH} is sent.<br/>
     * These changes will have no effect on message routing or authorization.<br/>
     * The modified payload will not be visible in any other callbacks other than {@link com.hivemq.spi.callback.events.BeforePublishSendCallback} and {@link com.hivemq.spi.callback.events.OnPublishSend}.
     *
     * @param payload a <code>byte[]</code> containing the modified payload
     */
    public void setPayload(final byte[] payload);

    /**
     * @return the topic of the message
     */
    public String getTopic();

    /**
     * By using this setter the topic of this {@link PUBLISH} can be modified before it is sent to a client.
     * <p>
     * Changes made to the topic here are only visible to the specific client to which this {@link ModifiablePUBLISH} is sent.<br/>
     * These changes will have no effect on message routing or authorization.<br/>
     * The modified topic will not be visible in any other callbacks other than {@link com.hivemq.spi.callback.events.BeforePublishSendCallback} and {@link com.hivemq.spi.callback.events.OnPublishSend}.
     *
     * @param topic the modified topic
     */
    public void setTopic(final String topic);

    /**
     * @return <code>true</code> if the message is a duplicate message
     */
    public boolean isDuplicateDelivery();

    /**
     * @return if the message should be retained
     */
    public boolean isRetain();

    /**
     * @return the QoS level of the message
     */
    public QoS getQoS();

    /**
     * @return returns a deep copy of the {@link PUBLISH}
     */
    public PUBLISH copy();
}