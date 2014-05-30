
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

package com.dcsquare.hivemq.spi.topic;


import com.dcsquare.hivemq.spi.message.QoS;
import com.dcsquare.hivemq.spi.topic.exception.InvalidTopicException;

/**
 * A Permission which represents the concept of a topic which can be restricted in the following parts:
 * <p/>
 * <ul>
 * <li>Topic (name)</li>
 * <li>Quality of Service </li>
 * <li>Activity (Publish/Subscribe)</li>
 * </ul>
 *
 * @author Christian Goetz
 * @author Dominik Obermaier
 * @since 1.4
 */
public class MqttTopicPermission {

    private final String topic;
    private final ALLOWED_QOS qos;
    private final ALLOWED_ACTIVITY activity;
    private TopicMatcher topicMatcher = new PermissionTopicMatcher();

    /**
     * Represents the allowed Quality of Service levels for a MqttTopicPermission
     */
    public enum ALLOWED_QOS {
        /**
         * Only QoS 0 is allowed
         */
        ZERO,
        /**
         * Only QoS 1 is allowed
         */
        ONE,
        /**
         * Only QoS 2 is allowed
         */
        TWO,
        /**
         * Only QoS 0 and 1 are allowed
         */
        ZERO_ONE,
        /**
         * Only QoS 0 and 2 are allowed
         */
        ZERO_TWO,
        /**
         * Only QoS 1 and 2 are allowed
         */
        ONE_TWO,
        /**
         * All QoS levels are allowed
         */
        ALL;

        private ALLOWED_QOS() {
        }


        public static ALLOWED_QOS from(final QoS from) {

            switch (from) {
                case AT_MOST_ONCE:
                    return ALLOWED_QOS.ZERO;
                case AT_LEAST_ONCE:
                    return ALLOWED_QOS.ONE;
                case EXACTLY_ONCE:
                    return ALLOWED_QOS.TWO;
                default:
                    //Should never happen
                    throw new RuntimeException("Invalid Qos");

            }
        }
    }

    /**
     * Represents the allowed activity on a topic
     */
    public enum ALLOWED_ACTIVITY {

        /**
         * Only publishing on this topic is allowed
         */
        PUBLISH,
        /**
         * Only subscribing on this topic is allowed
         */
        SUBSCRIBE,
        /**
         * Publishing and subscribing is allowed on this topic
         */
        ALL
    }

    /**
     * Creates a topic permission where publishing and subscribing on all QoS level is allowed on a given topic
     *
     * @param topic the topic
     */
    public MqttTopicPermission(final String topic) {
        this(topic, ALLOWED_QOS.ALL, ALLOWED_ACTIVITY.ALL);
    }

    /**
     * Creates a topic where a given activity is allowed on all QoS levels on a given topic
     *
     * @param topic    the topic
     * @param activity the activity
     */
    public MqttTopicPermission(final String topic, final ALLOWED_ACTIVITY activity) {

        this(topic, ALLOWED_QOS.ALL, activity);
    }

    /**
     * Creates a topic where publishing and subscribing on a given QoS is allowed for a given topic
     *
     * @param topic the topic
     * @param qos   the QoS level
     */
    public MqttTopicPermission(final String topic, final ALLOWED_QOS qos) {
        this(topic, qos, ALLOWED_ACTIVITY.ALL);
    }

    /**
     * Creates a topic where a given activity is allowed on a given QoS for a given topic
     *
     * @param topic    the topic
     * @param qos      the QoS
     * @param activity the activity
     */
    public MqttTopicPermission(final String topic, final ALLOWED_QOS qos, final ALLOWED_ACTIVITY activity) {
        this.topic = topic;
        this.qos = qos;
        this.activity = activity;
    }

    /**
     * Checks the MqttTopicPermission implies a given MqttTopicPermission
     *
     * @param other the MqttTopicPermission which should be checked if it is implied by the current MqttTopicPermission
     * @return <code>true</code> if the given MqttTopicPermission is implied
     */
    public boolean implies(final MqttTopicPermission other) {

        if (other == null) {
            return false;
        }

        final boolean activityImplies = getActivityImplicity(other);

        if (!activityImplies) {
            return false;
        }

        final boolean qosImplies = getQosImplicity(other);

        if (!qosImplies) {
            return false;
        }

        return topicImplicity(other);
    }

    /**
     * Checks if the topic implies a given MqttTopicPermissions topic
     *
     * @param other the other MqttTopicPermission
     * @return <code>true</code> if the given MqttTopicPermissions topic is implied by the current one
     */
    private boolean topicImplicity(final MqttTopicPermission other) {

        try {
            return topicMatcher.matches(this.getTopic(), other.getTopic());
        } catch (InvalidTopicException e) {
            return false;
        }
    }

    /**
     * Checks if the MqttTopicPermission implies a given QoS
     *
     * @param other the other MqttTopicPermission
     * @return <code>true</code> if the QoS level implies the given QoS
     */
    private boolean getQosImplicity(final MqttTopicPermission other) {

        if (this.getQos() == ALLOWED_QOS.ALL) {
            return true;
        }

        if (other.getQos() == ALLOWED_QOS.ALL && this.getQos() != ALLOWED_QOS.ALL) {
            return false;
        } else if (this.getQos() == ALLOWED_QOS.ZERO_ONE) {
            return (other.getQos() == ALLOWED_QOS.ZERO) || (other.getQos() == ALLOWED_QOS.ONE) || (other.getQos() == ALLOWED_QOS.ZERO_ONE);
        } else if (this.getQos() == ALLOWED_QOS.ONE_TWO) {
            return (other.getQos() == ALLOWED_QOS.ONE) || (other.getQos() == ALLOWED_QOS.TWO) || (other.getQos() == ALLOWED_QOS.ONE_TWO);
        } else if (this.getQos() == ALLOWED_QOS.ZERO_TWO) {
            return (other.getQos() == ALLOWED_QOS.ZERO) || (other.getQos() == ALLOWED_QOS.TWO) || (other.getQos() == ALLOWED_QOS.ZERO_TWO);
        }
        return this.getQos() == other.getQos();
    }

    /**
     * Checks if an activity implies another activity
     *
     * @param other the other permission with an activity
     * @return <code>true</code> if the permission activity imply the other permission activity
     */
    private boolean getActivityImplicity(final MqttTopicPermission other) {
        if (this.getActivity() == ALLOWED_ACTIVITY.ALL) {
            return true;
        }

        if ((other.getActivity() == ALLOWED_ACTIVITY.SUBSCRIBE) && (this.activity == ALLOWED_ACTIVITY.PUBLISH)) {
            return false;
        } else if ((other.getActivity() == ALLOWED_ACTIVITY.PUBLISH) && (this.activity == ALLOWED_ACTIVITY.SUBSCRIBE)) {
            return false;
        } else if (other.getActivity() == ALLOWED_ACTIVITY.ALL && this.getActivity() != ALLOWED_ACTIVITY.ALL) {
            return false;
        }
        return true;
    }


    public String getTopic() {
        return topic;
    }

    public ALLOWED_QOS getQos() {
        return qos;
    }

    public ALLOWED_ACTIVITY getActivity() {
        return activity;
    }

}

