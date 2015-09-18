
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

package com.hivemq.spi.topic;


import com.hivemq.spi.message.QoS;
import com.hivemq.spi.topic.exception.InvalidTopicException;

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
 * @author Christoph Schäbel
 * @since 1.4
 */
public class MqttTopicPermission {

    private final String topic;
    private final QOS qos;
    private final ACTIVITY activity;
    private final TYPE type;
    private final RETAIN publishRetain;
    private TopicMatcher topicMatcher = new PermissionTopicMatcher();

    public enum TYPE {
        /**
         * If the permission allows the activity
         */
        ALLOW,
        /**
         * If the permission denies the activity
         */
        DENY;
    }

    /**
     * Represents the allowed/denied Quality of Service levels for a MqttTopicPermission
     */
    public enum QOS {
        /**
         * Only QoS 0 is allowed/denied
         */
        ZERO,
        /**
         * Only QoS 1 is allowed/denied
         */
        ONE,
        /**
         * Only QoS 2 is allowed/denied
         */
        TWO,
        /**
         * Only QoS 0 and 1 are allowed/denied
         */
        ZERO_ONE,
        /**
         * Only QoS 0 and 2 are allowed/denied
         */
        ZERO_TWO,
        /**
         * Only QoS 1 and 2 are allowed/denied
         */
        ONE_TWO,
        /**
         * All QoS levels are allowed/denied
         */
        ALL;

        private QOS() {
        }


        public static QOS from(final QoS from) {

            switch (from) {
                case AT_MOST_ONCE:
                    return QOS.ZERO;
                case AT_LEAST_ONCE:
                    return QOS.ONE;
                case EXACTLY_ONCE:
                    return QOS.TWO;
                default:
                    //Should never happen
                    throw new RuntimeException("Invalid Qos");

            }
        }
    }

    /**
     * Represents the allowed/denied activity on a topic
     */
    public enum ACTIVITY {

        /**
         * Only publishing on this topic is allowed/denied
         */
        PUBLISH,
        /**
         * Only subscribing on this topic is allowed/denied
         */
        SUBSCRIBE,
        /**
         * Publishing and subscribing is allowed/denied on this topic
         */
        ALL
    }

    public enum RETAIN {

        /**
         * Only publishing retained is allowed/denied
         */
        RETAINED,
        /**
         * Only publishing not retained is allowed/denied
         */
        NOT_RETAINED,
        /**
         * Both, retained and not retained are allowed/denied
         */
        ALL
    }

    /**
     * Creates a topic permission where publishing and subscribing on all QoS level is allowed on a given topic
     *
     * @param topic the topic
     * @param type  the type of this permission (allow / deny)
     */
    public MqttTopicPermission(final String topic, final TYPE type) {
        this(topic, type, QOS.ALL, ACTIVITY.ALL);
    }

    /**
     * Creates a topic where a given activity is allowed on all QoS levels on a given topic
     *
     * @param topic    the topic
     * @param type     the type of this permission (allow / deny)
     * @param activity the activity
     */
    public MqttTopicPermission(final String topic, final TYPE type, final ACTIVITY activity) {

        this(topic, type, QOS.ALL, activity);
    }

    /**
     * Creates a topic where publishing and subscribing on a given QoS is allowed for a given topic
     *
     * @param topic the topic
     * @param type  the type of this permission (allow / deny)
     * @param qos   the QoS level
     */
    public MqttTopicPermission(final String topic, final TYPE type, final QOS qos) {
        this(topic, type, qos, ACTIVITY.ALL);
    }

    /**
     * Creates a topic where a given activity is allowed on a given QoS for a given topic
     *
     * @param topic    the topic
     * @param type     the type of this permission (allow / deny)
     * @param qos      the QoS
     * @param activity the activity
     */
    public MqttTopicPermission(final String topic, final TYPE type, final QOS qos, final ACTIVITY activity) {
        this(topic, type, qos, activity, RETAIN.ALL);
    }

    /**
     * Creates a topic where a given activity is allowed on a given QoS for a given topic
     *
     * @param topic           the topic
     * @param type            the type of this permission (allow / deny)
     * @param qos             the QoS
     * @param activity        the activity
     * @param publishRetain if the client is allowed/denied to publish retained messages to this topic
     */
    public MqttTopicPermission(final String topic, final TYPE type, final QOS qos, final ACTIVITY activity, final RETAIN publishRetain) {
        this.topic = topic;
        this.type = type;
        this.qos = qos;
        this.activity = activity;
        this.publishRetain = publishRetain;
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

        return implies(other.getTopic(), other.getQos(), other.getActivity());
    }


    /**
     * Checks the MqttTopicPermission implies a given topic, qos and activity combination
     *
     * @param topic    the topic to check
     * @param qoS      the QoS to check
     * @param activity the activity to check
     * @return <code>true</code> if the given topic, qos and activity combination is implied
     */
    public boolean implies(final String topic, final QoS qoS, final ACTIVITY activity, final boolean retained) {

        return implies(topic, qoS, activity, retained ? RETAIN.RETAINED : RETAIN.NOT_RETAINED);
    }

    /**
     * Checks the MqttTopicPermission implies a given topic, qos and activity combination
     *
     * @param topic    the topic to check
     * @param qoS      the QoS to check
     * @param activity the activity to check
     * @return <code>true</code> if the given topic, qos and activity combination is implied
     */
    public boolean implies(final String topic, final QoS qoS, final ACTIVITY activity, final RETAIN RETAIN) {

        if (RETAIN == null) {
            return false;
        }

        if (this.publishRetain != RETAIN.ALL && this.publishRetain != RETAIN) {
            return false;
        }

        return implies(topic, qoS, activity);
    }

    /**
     * Checks the MqttTopicPermission implies a given topic, qos and activity combination
     *
     * @param topic    the topic to check
     * @param qoS      the QoS to check
     * @param activity the activity to check
     * @return <code>true</code> if the given topic, qos and activity combination is implied
     */
    public boolean implies(final String topic, final QoS qoS, final ACTIVITY activity) {

        if (qoS == null) {
            return false;
        }

        return implies(topic, QOS.from(qoS), activity);
    }

    /**
     * Checks the MqttTopicPermission implies a given topic, qos and activity combination
     *
     * @param topic    the topic to check
     * @param qoS      the QoS to check
     * @param activity the activity to check
     * @return <code>true</code> if the given topic, qos and activity combination is implied
     */
    public boolean implies(final String topic, final QOS qoS, final ACTIVITY activity) {

        if (topic == null || qoS == null || activity == null) {
            return false;
        }

        final boolean activityImplies = getActivityImplicity(activity);

        if (!activityImplies) {
            return false;
        }

        final boolean qosImplies = getQosImplicity(qoS);

        if (!qosImplies) {
            return false;
        }

        return topicImplicity(topic);

    }

    /**
     * Checks if the topic implies a given MqttTopicPermissions topic
     *
     * @param topic the topic to check
     * @return <code>true</code> if the given MqttTopicPermissions topic is implied by the current one
     */
    private boolean topicImplicity(final String topic) {

        try {
            return topicMatcher.matches(this.getTopic(), topic);
        } catch (InvalidTopicException e) {
            return false;
        }
    }

    /**
     * Checks if the MqttTopicPermission implies a given QoS
     *
     * @param qos the activity to check
     * @return <code>true</code> if the QoS level implies the given QoS
     */
    private boolean getQosImplicity(final QOS qos) {
        if (this.getQos() == QOS.ALL) {
            return true;
        }

        if (qos == QOS.ALL) {
            return false;
        } else if (this.getQos() == QOS.ZERO_ONE) {
            return (qos == QOS.ZERO) || (qos == QOS.ONE) || (qos == QOS.ZERO_ONE);
        } else if (this.getQos() == QOS.ONE_TWO) {
            return (qos == QOS.ONE) || (qos == QOS.TWO) || (qos == QOS.ONE_TWO);
        } else if (this.getQos() == QOS.ZERO_TWO) {
            return (qos == QOS.ZERO) || (qos == QOS.TWO) || (qos == QOS.ZERO_TWO);
        }
        return this.getQos() == qos;
    }

    /**
     * Checks if an activity implies another activity
     *
     * @param activity the activity to check
     * @return <code>true</code> if the permission activity imply the other permission activity
     */
    private boolean getActivityImplicity(final ACTIVITY activity) {
        if (this.activity == ACTIVITY.ALL) {
            return true;
        }

        if ((activity == ACTIVITY.SUBSCRIBE) && (this.activity == ACTIVITY.PUBLISH)) {
            return false;
        } else if ((activity == ACTIVITY.PUBLISH) && (this.activity == ACTIVITY.SUBSCRIBE)) {
            return false;
        } else if (activity == ACTIVITY.ALL && this.getActivity() != ACTIVITY.ALL) {
            return false;
        }
        return true;
    }

    public String getTopic() {
        return topic;
    }

    public TYPE getType() {
        return type;
    }

    public QOS getQos() {
        return qos;
    }

    public ACTIVITY getActivity() {
        return activity;
    }

    public RETAIN getPublishRetain() {
        return publishRetain;
    }
}

