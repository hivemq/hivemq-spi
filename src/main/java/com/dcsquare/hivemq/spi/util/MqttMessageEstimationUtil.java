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

package com.dcsquare.hivemq.spi.util;

import com.dcsquare.hivemq.spi.message.*;

import java.util.List;

/**
 * This util can be used to determine the size of each MQTT command message.
 *
 * @author Christian Goetz
 * @since 1.4
 */
public class MqttMessageEstimationUtil {

    /**
     * Length of the fixed header in bytes
     */
    private static final int FIXED_HEADER_BYTES = 2;
    /**
     * Length of the message id in bytes
     */
    private static final int MESSAGE_ID_BYTES = 2;

    /**
     * Estimates the size of a {@link CONNECT} message
     *
     * @param connect message, which should be estimated
     * @return message size in bytes
     */
    public static int estimateConnectSize(final CONNECT connect) {
        int size = 0;
        size += FIXED_HEADER_BYTES;

        size += 12;

        if (connect != null) {
            size += estimateUTF8StringSize(connect.getClientIdentifier());
            if (connect.isWill()) {
                size += estimateUTF8StringSize(connect.getWillTopic());
                size += estimateUTF8StringSize(connect.getWillMessage());
            }
            if (connect.isUsernameRequired()) {
                size += estimateUTF8StringSize(connect.getUsername());
            }
            if (connect.isPasswordRequired()) {
                size += estimateUTF8StringSize(connect.getPassword());
            }
        } else {
            return 0;
        }

        return size;

    }

    /**
     * Estimates the size of a {@link CONNACK} message
     * <p/>
     * {@link CONNACK} object is not needed, because the message
     * has the same size all the time.
     *
     * @return message size in bytes
     */
    public static int estimateConnackSize() {
        int size = 0;
        size += FIXED_HEADER_BYTES;
        // Variable Header: ConnectReturnCode & Topic Name Compression Response
        size += 2;
        return size;

    }

    /**
     * /**
     * Estimates the size of a {@link PUBLISH} message
     *
     * @param publish message, which should be estimated
     * @return message size in bytes
     */
    public static int estimatePublishSize(final PUBLISH publish) {
        int size = 0;
        size += FIXED_HEADER_BYTES;
        if (publish != null) {

            if (publish.getQoS() == QoS.AT_LEAST_ONCE || publish.getQoS() == QoS.EXACTLY_ONCE) {
                size += MESSAGE_ID_BYTES;
            }
            if (publish.getTopic() != null) {
                size += estimateUTF8StringSize(publish.getTopic());
            }
            if (publish.getPayload() != null) {
                size += publish.getPayload().length;
            }
        } else {
            return 0;
        }
        return size;
    }

    /**
     * Estimates the size of a {@link PUBACK} message
     * <p/>
     * {@link PUBACK} object is not needed, because the message
     * has the same size all the time.
     *
     * @return message size in bytes
     */
    public static int estimatePubackSize() {
        int size = 0;
        size += FIXED_HEADER_BYTES;
        size += MESSAGE_ID_BYTES;

        return size;
    }

    /**
     * Estimates the size of a {@link PUBREC} message
     * <p/>
     * {@link PUBREC} object is not needed, because the message
     * has the same size all the time.
     *
     * @return message size in bytes
     */
    public static int estimatePubrecSize() {
        int size = 0;
        size += FIXED_HEADER_BYTES;
        size += MESSAGE_ID_BYTES;

        return size;
    }

    /**
     * Estimates the size of a {@link PUBREL} message
     * <p/>
     * {@link PUBREL} object is not needed, because the message
     * has the same size all the time.
     *
     * @return message size in bytes
     */
    public static int estimatePubrelSize() {
        int size = 0;
        size += FIXED_HEADER_BYTES;
        size += MESSAGE_ID_BYTES;

        return size;
    }

    /**
     * Estimates the size of a {@link PUBCOMP} message
     * <p/>
     * {@link PUBCOMP} object is not needed, because the message
     * has the same size all the time.
     *
     * @return message size in bytes
     */
    public static int estimatePubcompSize() {
        int size = 0;
        size += FIXED_HEADER_BYTES;
        size += MESSAGE_ID_BYTES;

        return size;
    }

    /**
     * Estimates the size of a {@link SUBSCRIBE} message
     *
     * @param subscribe message, which should be estimated
     * @return message size in bytes
     */
    public static int estimateSubscribeSize(final SUBSCRIBE subscribe) {
        int size = 0;
        size += FIXED_HEADER_BYTES;
        size += MESSAGE_ID_BYTES;
        if (subscribe != null) {
            final List<Topic> topics = subscribe.getTopics();
            for (final Topic topic : topics) {
                size += estimateUTF8StringSize(topic.getTopic());
                //QoS uses 1 Byte
                size += 1;
            }
        } else {
            return 0;
        }

        return size;
    }

    /**
     * Estimates the size of a {@link SUBACK} message
     *
     * @param suback message, which should be estimated
     * @return message size in bytes
     */
    public static int estimateSubackSize(final SUBACK suback) {
        int size = 0;
        size += FIXED_HEADER_BYTES;
        size += MESSAGE_ID_BYTES;
        if (suback != null) {
            size += suback.getGrantedQos().size();
        } else {
            return 0;
        }
        return size;
    }

    /**
     * Estimates the size of a {@link UNSUBSCRIBE} message
     *
     * @param unsubscribe message, which should be estimated
     * @return message size in bytes
     */
    public static int estimateUnsubscribeSize(final UNSUBSCRIBE unsubscribe) {
        int size = 0;
        size += FIXED_HEADER_BYTES;
        size += MESSAGE_ID_BYTES;

        final List<String> topics = unsubscribe.getTopics();
        for (final String topic : topics) {
            size += estimateUTF8StringSize(topic);
        }

        return size;
    }

    /**
     * Estimates the size of a {@link UNSUBACK} message
     * <p/>
     * {@link UNSUBACK} object is not needed, because the message
     * has the same size all the time.
     *
     * @return message size in bytes
     */
    public static int estimateUnsubackSize() {
        int size = 0;
        size += FIXED_HEADER_BYTES;
        size += MESSAGE_ID_BYTES;

        return size;
    }

    /**
     * Estimates the size of a {@link PINGREQ} message
     * <p/>
     * {@link PINGREQ} object is not needed, because the message
     * has the same size all the time.
     *
     * @return message size in bytes
     */
    public static int estimatePingReqSize() {
        int size = 0;
        size += FIXED_HEADER_BYTES;

        return size;
    }

    /**
     * Estimates the size of a {@link PINGRESP} message
     * <p/>
     * {@link PINGRESP} object is not needed, because the message
     * has the same size all the time.
     *
     * @return message size in bytes
     */
    public static int estimatePingRespSize() {
        int size = 0;
        size += FIXED_HEADER_BYTES;

        return size;
    }

    /**
     * Estimates the size of a {@link DISCONNECT} message
     * <p/>
     * {@link DISCONNECT} object is not needed, because the message
     * has the same size all the time.
     *
     * @return message size in bytes
     */
    public static int estimateDisconnectSize() {
        int size = 0;
        size += FIXED_HEADER_BYTES;

        return size;
    }

    /**
     * Estimates the size of a string used in a MQTT message
     *
     * @return string size in bytes
     */
    private static int estimateUTF8StringSize(final String s) {
        if (s != null) {
            int size = 0;
            size += s.getBytes().length;
            //definition of length in mqtt
            size += 2;
            return size;
        } else {
            return 0;
        }
    }
}
