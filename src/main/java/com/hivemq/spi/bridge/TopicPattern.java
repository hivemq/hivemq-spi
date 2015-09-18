/*
 * Copyright 2014 dc-square GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hivemq.spi.bridge;

import com.hivemq.spi.message.QoS;

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
