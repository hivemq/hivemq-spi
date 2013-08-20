/*
 * Copyright 2013 dc-square GmbH
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
import com.google.common.base.Charsets;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Christian Goetz
 */
public class MqttMessageEstimationUtilTest {


    @Test
    public void testEstimateNullConnectMessage() throws Exception {

        final CONNECT connect = null;

        final int size = MqttMessageEstimationUtil.estimateConnectSize(connect);

        assertEquals(0, size);
    }

    @Test
    public void testEstimateEmptyConnectMessage() throws Exception {

        final CONNECT connect = new CONNECT();

        final int size = MqttMessageEstimationUtil.estimateConnectSize(connect);

        assertEquals(14, size);
    }

    @Test
    public void testEstimateAlmostEmptyConnectMessage() throws Exception {

        final CONNECT connect = new CONNECT();
        connect.setClientIdentifier("");

        final int size = MqttMessageEstimationUtil.estimateConnectSize(connect);

        assertEquals(16, size);
    }

    @Test
    public void testEstimateConnectMessage() throws Exception {

        final CONNECT connect = new CONNECT();
        connect.setKeepAliveTimer(60);
        connect.setCleanSession(true);
        connect.setClientIdentifier("client-test");
        final int size = MqttMessageEstimationUtil.estimateConnectSize(connect);
        assertEquals(27, size);
    }

    @Test
    public void testEstimateConnectMessageWithUsername() throws Exception {

        final CONNECT connect = new CONNECT();
        connect.setKeepAliveTimer(60);
        connect.setCleanSession(true);
        connect.setClientIdentifier("client-test");
        connect.setUsernameRequired(true);
        connect.setUsername("testuser");

        final int size = MqttMessageEstimationUtil.estimateConnectSize(connect);

        assertEquals(37, size);
    }

    @Test
    public void testEstimateConnectMessageWithPassword() throws Exception {

        final CONNECT connect = new CONNECT();
        connect.setKeepAliveTimer(60);
        connect.setCleanSession(true);
        connect.setClientIdentifier("client-test");
        connect.setPasswordRequired(true);
        connect.setPassword("testpassword");

        final int size = MqttMessageEstimationUtil.estimateConnectSize(connect);

        assertEquals(41, size);
    }

    @Test
    public void testEstimateConnectMessageWithCredentials() throws Exception {

        final CONNECT connect = new CONNECT();
        connect.setKeepAliveTimer(60);
        connect.setCleanSession(true);
        connect.setClientIdentifier("0815:testclient");
        connect.setUsernameRequired(true);
        connect.setPasswordRequired(true);
        connect.setUsername("0815:bla");
        connect.setPassword("goetz");

        final int size = MqttMessageEstimationUtil.estimateConnectSize(connect);

        assertEquals(48, size);
    }

    @Test
    public void testEstimateConnectMessageWithWill() throws Exception {

        final CONNECT connect = new CONNECT();
        connect.setKeepAliveTimer(60);
        connect.setCleanSession(true);
        connect.setClientIdentifier("0815:testclient");
        connect.setWill(true);
        connect.setWillMessage("Hi everybody, watch me, I'm dead");
        connect.setWillQos(QoS.AT_LEAST_ONCE);
        connect.setWillTopic("my/custom/willtopic");

        final int size = MqttMessageEstimationUtil.estimateConnectSize(connect);

        assertEquals(86, size);
    }

    @Test
    public void testEstimateConnackSize() throws Exception {
        final int size = MqttMessageEstimationUtil.estimateConnackSize();
        assertEquals(4, size);
    }

    @Test
    public void testEstimateNullPublish() throws Exception {

        final PUBLISH publish = new PUBLISH();

        final int size = MqttMessageEstimationUtil.estimatePublishSize(publish);

        assertEquals(2, size);

    }


    @Test
    public void testEstimateEmptyPublish() throws Exception {

        final PUBLISH publish = null;

        final int size = MqttMessageEstimationUtil.estimatePublishSize(publish);

        assertEquals(0, size);

    }

    @Test
    public void testEstimatePublishSizeQoS0() throws Exception {

        final PUBLISH publish = new PUBLISH();
        publish.setMessageId(1);
        publish.setQoS(QoS.AT_MOST_ONCE);
        publish.setRetain(false);
        publish.setTopic("tha test publish topic");
        publish.setPayload("tha test payload message".getBytes(Charsets.UTF_8));

        final int size = MqttMessageEstimationUtil.estimatePublishSize(publish);

        assertEquals(50, size);

    }

    @Test
    public void testEstimatePublishSizeQoS1() throws Exception {

        final PUBLISH publish = new PUBLISH();
        publish.setMessageId(1);
        publish.setQoS(QoS.AT_LEAST_ONCE);
        publish.setRetain(false);
        publish.setTopic("tha test publish topic");
        publish.setPayload("tha test payload message".getBytes(Charsets.UTF_8));

        final int size = MqttMessageEstimationUtil.estimatePublishSize(publish);

        assertEquals(52, size);

    }

    @Test
    public void testEstimatePublishSizeQoS2() throws Exception {
        final PUBLISH publish = new PUBLISH();
        publish.setMessageId(1);
        publish.setQoS(QoS.EXACTLY_ONCE);
        publish.setRetain(false);
        publish.setTopic("tha test publish topic");
        publish.setPayload("tha test payload message".getBytes(Charsets.UTF_8));

        final int size = MqttMessageEstimationUtil.estimatePublishSize(publish);

        assertEquals(52, size);
    }

    @Test
    public void testEstimatePubackSize() throws Exception {
        final int size = MqttMessageEstimationUtil.estimatePubackSize();
        assertEquals(4, size);
    }

    @Test
    public void testEstimatePubrecSize() throws Exception {
        final int size = MqttMessageEstimationUtil.estimatePubrecSize();
        assertEquals(4, size);
    }

    @Test
    public void testEstimatePubrelSize() throws Exception {
        final int size = MqttMessageEstimationUtil.estimatePubrelSize();
        assertEquals(4, size);
    }

    @Test
    public void testEstimatePubcompSize() throws Exception {

        final int size = MqttMessageEstimationUtil.estimatePubcompSize();

        assertEquals(4, size);
    }

    @Test
    public void testEstimateSubscribeSizeOfEmptyMessage() throws Exception {
        final SUBSCRIBE subscribe = new SUBSCRIBE();

        final int size = MqttMessageEstimationUtil.estimateSubscribeSize(subscribe);

        assertEquals(4, size);
    }

    @Test
    public void testEstimateSubscribeSizeOfNullMessage() throws Exception {
        final SUBSCRIBE subscribe = null;

        final int size = MqttMessageEstimationUtil.estimateSubscribeSize(subscribe);

        assertEquals(0, size);
    }


    @Test
    public void testEstimateSubscribeSize() throws Exception {
        final SUBSCRIBE subscribe = new SUBSCRIBE();
        subscribe.setMessageId(1);
        final List<Topic> topics = new ArrayList<Topic>();
        topics.add(new Topic("my/test/subscribe/topic/#", QoS.EXACTLY_ONCE));
        subscribe.setTopics(topics);
        subscribe.setQoS(QoS.valueOf(1));

        final int size = MqttMessageEstimationUtil.estimateSubscribeSize(subscribe);

        assertEquals(32, size);
    }

    @Test
    public void testEstimateSubscribe2TopcisSize() throws Exception {
        final SUBSCRIBE subscribe = new SUBSCRIBE();
        subscribe.setMessageId(1);
        final List<Topic> topics = new ArrayList<Topic>();
        topics.add(new Topic("my/test/subscribe/topic/1/#", QoS.EXACTLY_ONCE));
        topics.add(new Topic("my/test/subscribe/topic/2/#", QoS.EXACTLY_ONCE));
        subscribe.setTopics(topics);
        subscribe.setQoS(QoS.valueOf(1));

        final int size = MqttMessageEstimationUtil.estimateSubscribeSize(subscribe);

        assertEquals(64, size);
    }

    @Test
    public void testEstimateSubackNull() throws Exception {
        final SUBACK suback = null;

        final int size = MqttMessageEstimationUtil.estimateSubackSize(suback);

        assertEquals(0, size);
    }

    @Test
    public void testEstimateSubackEmpty() throws Exception {
        final SUBACK suback = new SUBACK();

        final int size = MqttMessageEstimationUtil.estimateSubackSize(suback);

        assertEquals(4, size);
    }

    @Test
    public void testEstimateSuback1TopicSize() throws Exception {
        final SUBACK suback = new SUBACK();
        final ArrayList<Byte> list = new ArrayList<Byte>();
        list.add((byte) 1);
        suback.setGrantedQos(list);

        final int size = MqttMessageEstimationUtil.estimateSubackSize(suback);

        assertEquals(5, size);
    }

    @Test
    public void testEstimateSuback2TopicsSize() throws Exception {
        final SUBACK suback = new SUBACK();
        final ArrayList<Byte> list = new ArrayList<Byte>();
        list.add((byte) 1);
        list.add((byte) 2);
        suback.setGrantedQos(list);

        final int size = MqttMessageEstimationUtil.estimateSubackSize(suback);

        assertEquals(6, size);
    }

    @Test
    public void testEstimateUnsubscribeSizeEmpty() throws Exception {
        final UNSUBSCRIBE unsubscribe = new UNSUBSCRIBE();
        final int size = MqttMessageEstimationUtil.estimateUnsubscribeSize(unsubscribe);
        assertEquals(4, size);
    }

    @Test
    public void testEstimateUnsubscribeSize() throws Exception {
        final UNSUBSCRIBE unsubscribe = new UNSUBSCRIBE();
        final List<String> topic = new ArrayList<String>();
        topic.add("topic");
        topic.add("test");
        unsubscribe.setTopics(topic);
        final int size = MqttMessageEstimationUtil.estimateUnsubscribeSize(unsubscribe);
        assertEquals(17, size);
    }

    @Test
    public void testEstimateUnsubackSize() throws Exception {
        final int size = MqttMessageEstimationUtil.estimateUnsubackSize();
        assertEquals(4, size);
    }

    @Test
    public void testEstimatePingReqSize() throws Exception {
        final int size = MqttMessageEstimationUtil.estimatePingReqSize();
        assertEquals(2, size);
    }

    @Test
    public void testEstimatePingRespSize() throws Exception {
        final int size = MqttMessageEstimationUtil.estimatePingRespSize();
        assertEquals(2, size);
    }

    @Test
    public void testEstimateDisconnectSize() throws Exception {
        final int size = MqttMessageEstimationUtil.estimateDisconnectSize();
        assertEquals(2, size);
    }
}

