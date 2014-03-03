package com.dcsquare.hivemq.spi.message;

/**
 * @author Lukas Brandl
 */
public class RetainedMessage {
    private String topic;
    private byte[] message;
    private QoS qoS;

    public RetainedMessage(String topic, byte[] message, QoS qoS){
        this.topic = topic;
        this.message = message;
        this.qoS = qoS;
    }


    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public byte[] getMessage() {
        return message;
    }

    public void setMessage(byte[] message) {
        this.message = message;
    }

    public QoS getQoS() {
        return qoS;
    }

    public void setQoS(QoS qoS) {
        this.qoS = qoS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RetainedMessage that = (RetainedMessage) o;

        if (topic != null ? !topic.equals(that.topic) : that.topic != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return topic != null ? topic.hashCode() : 0;
    }
}
