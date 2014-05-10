package com.dcsquare.hivemq.spi.bridge;

import com.dcsquare.hivemq.spi.message.QoS;

/**
 * @author Dominik Obermaier
 */
public class TopicPattern {

    public enum Type {
        IN("in"),
        OUT("out"),
        BOTH("both");
        private final String type;

        Type(final String type) {

            this.type = type;
        }

        public String getType() {
            return type;
        }

        public static Type from(final String type) {
            for (final Type theType : Type.values()) {
                if (theType.getType().equals(type)) {
                    return theType;
                }
            }
            return null;
        }
    }

    private String pattern;

    private Type type;

    private QoS qoS;

    private String localPrefix;

    private String remotePrefix;

    public String getPattern() {
        return pattern;
    }

    public void setPattern(final String pattern) {
        this.pattern = pattern;
    }

    public Type getType() {
        return type;
    }

    public void setType(final Type type) {
        this.type = type;
    }

    public QoS getQoS() {
        return qoS;
    }

    public void setQoS(final QoS qoS) {
        this.qoS = qoS;
    }

    public String getLocalPrefix() {
        return localPrefix;
    }

    public void setLocalPrefix(final String localPrefix) {
        this.localPrefix = localPrefix;
    }

    public String getRemotePrefix() {
        return remotePrefix;
    }

    public void setRemotePrefix(final String remotePrefix) {
        this.remotePrefix = remotePrefix;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final TopicPattern that = (TopicPattern) o;

        if (localPrefix != null ? !localPrefix.equals(that.localPrefix) : that.localPrefix != null) return false;
        if (pattern != null ? !pattern.equals(that.pattern) : that.pattern != null) return false;
        if (qoS != that.qoS) return false;
        if (remotePrefix != null ? !remotePrefix.equals(that.remotePrefix) : that.remotePrefix != null) return false;
        if (type != that.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pattern != null ? pattern.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (qoS != null ? qoS.hashCode() : 0);
        result = 31 * result + (localPrefix != null ? localPrefix.hashCode() : 0);
        result = 31 * result + (remotePrefix != null ? remotePrefix.hashCode() : 0);
        return result;
    }
}
