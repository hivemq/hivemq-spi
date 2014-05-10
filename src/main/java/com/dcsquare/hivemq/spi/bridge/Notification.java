package com.dcsquare.hivemq.spi.bridge;

import com.dcsquare.hivemq.spi.message.QoS;

/**
 * @author Dominik Obermaier
 * @since 2.0
 */
public class Notification {

    public final static String DEFAULT_NOTIFICATION_TOPIC = "$SYS/broker/connection/#{clientId}/state";

    private String topic;

    private QoS qoS;

    public Notification(final String topic, final QoS qoS) {
        this.topic = topic;
        this.qoS = qoS;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(final String topic) {
        this.topic = topic;
    }

    public QoS getQoS() {
        return qoS;
    }

    public void setQoS(final QoS qoS) {
        this.qoS = qoS;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Notification that = (Notification) o;

        if (qoS != that.qoS) return false;
        if (topic != null ? !topic.equals(that.topic) : that.topic != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = topic != null ? topic.hashCode() : 0;
        result = 31 * result + (qoS != null ? qoS.hashCode() : 0);
        return result;
    }
}
