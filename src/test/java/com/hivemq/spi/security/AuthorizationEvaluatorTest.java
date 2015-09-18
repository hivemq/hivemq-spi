package com.hivemq.spi.security;

import com.google.common.collect.Lists;
import com.hivemq.spi.callback.security.authorization.AuthorizationBehaviour;
import com.hivemq.spi.callback.security.authorization.AuthorizationResult;
import com.hivemq.spi.message.QoS;
import com.hivemq.spi.topic.MqttTopicPermission;
import org.junit.Test;

import java.util.List;

import static com.hivemq.spi.callback.security.authorization.AuthorizationBehaviour.*;
import static com.hivemq.spi.topic.MqttTopicPermission.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Christoph Schäbel
 */
public class AuthorizationEvaluatorTest {

    @Test
    public void test_publish_empty_list() throws Exception {

        final AuthorizationResult authResult = new AuthorizationResult() {
            @Override
            public List<MqttTopicPermission> getMqttTopicPermissions() {
                return Lists.newArrayList();
            }

            @Override
            public AuthorizationBehaviour getDefaultBehaviour() {
                return NEXT;
            }
        };

        assertEquals(NEXT, AuthorizationEvaluator.checkPublish("test/1/2/3", QoS.AT_LEAST_ONCE, false, authResult));
    }

    @Test
    public void test_publish_null_list() throws Exception {

        final AuthorizationResult authResult = new AuthorizationResult() {
            @Override
            public List<MqttTopicPermission> getMqttTopicPermissions() {
                return null;
            }

            @Override
            public AuthorizationBehaviour getDefaultBehaviour() {
                return NEXT;
            }
        };

        assertEquals(NEXT, AuthorizationEvaluator.checkPublish("test/1/2/3", QoS.AT_LEAST_ONCE, false, authResult));
    }

    @Test
    public void test_publish_accept() throws Exception {

        final AuthorizationResult authResult = new AuthorizationResult() {
            @Override
            public List<MqttTopicPermission> getMqttTopicPermissions() {
                return Lists.newArrayList(
                        new MqttTopicPermission("test/1/#", TYPE.ALLOW)
                );
            }

            @Override
            public AuthorizationBehaviour getDefaultBehaviour() {
                return DENY;
            }
        };

        assertEquals(ACCEPT, AuthorizationEvaluator.checkPublish("test/1/2/3", QoS.AT_LEAST_ONCE, false, authResult));
    }

    @Test
    public void test_publish_default() throws Exception {

        final AuthorizationResult authResult = new AuthorizationResult() {
            @Override
            public List<MqttTopicPermission> getMqttTopicPermissions() {
                return Lists.newArrayList(
                        new MqttTopicPermission("test/2/#", TYPE.ALLOW),
                        new MqttTopicPermission("test/3/#", TYPE.DENY)
                );
            }

            @Override
            public AuthorizationBehaviour getDefaultBehaviour() {
                return NEXT;
            }
        };

        assertEquals(NEXT, AuthorizationEvaluator.checkPublish("test/1/2/3", QoS.AT_LEAST_ONCE, false, authResult));
    }

    @Test
    public void test_publish_match_multi() throws Exception {

        final AuthorizationResult authResult = new AuthorizationResult() {
            @Override
            public List<MqttTopicPermission> getMqttTopicPermissions() {
                return Lists.newArrayList(
                        new MqttTopicPermission("test/2/#", TYPE.DENY),
                        new MqttTopicPermission("test/3/#", TYPE.DENY),
                        new MqttTopicPermission("test/1/#", TYPE.ALLOW),
                        new MqttTopicPermission("test/3/#", TYPE.DENY)
                );
            }

            @Override
            public AuthorizationBehaviour getDefaultBehaviour() {
                return DENY;
            }
        };

        assertEquals(ACCEPT, AuthorizationEvaluator.checkPublish("test/1/2/3", QoS.AT_LEAST_ONCE, false, authResult));
    }

    @Test
    public void test_publish_deny() throws Exception {

        final AuthorizationResult authResult = new AuthorizationResult() {
            @Override
            public List<MqttTopicPermission> getMqttTopicPermissions() {
                return Lists.newArrayList(
                        new MqttTopicPermission("test/1/#", TYPE.DENY)
                );
            }

            @Override
            public AuthorizationBehaviour getDefaultBehaviour() {
                return ACCEPT;
            }
        };

        assertEquals(DENY, AuthorizationEvaluator.checkPublish("test/1/2/3", QoS.AT_LEAST_ONCE, false, authResult));
    }


    @Test
    public void test_subscribe_empty_list() throws Exception {

        final AuthorizationResult authResult = new AuthorizationResult() {
            @Override
            public List<MqttTopicPermission> getMqttTopicPermissions() {
                return Lists.newArrayList();
            }

            @Override
            public AuthorizationBehaviour getDefaultBehaviour() {
                return NEXT;
            }
        };

        assertEquals(NEXT, AuthorizationEvaluator.checkSubscription("test/1/2/3", QoS.AT_LEAST_ONCE, authResult));
    }

    @Test
    public void test_subscribe_null_list() throws Exception {

        final AuthorizationResult authResult = new AuthorizationResult() {
            @Override
            public List<MqttTopicPermission> getMqttTopicPermissions() {
                return null;
            }

            @Override
            public AuthorizationBehaviour getDefaultBehaviour() {
                return NEXT;
            }
        };

        assertEquals(NEXT, AuthorizationEvaluator.checkSubscription("test/1/2/3", QoS.AT_LEAST_ONCE, authResult));
    }

    @Test
    public void test_subscribe_accept() throws Exception {

        final AuthorizationResult authResult = new AuthorizationResult() {
            @Override
            public List<MqttTopicPermission> getMqttTopicPermissions() {
                return Lists.newArrayList(
                        new MqttTopicPermission("test/1/#", TYPE.ALLOW)
                );
            }

            @Override
            public AuthorizationBehaviour getDefaultBehaviour() {
                return DENY;
            }
        };

        assertEquals(ACCEPT, AuthorizationEvaluator.checkSubscription("test/1/2/3", QoS.AT_LEAST_ONCE, authResult));
    }

    @Test
    public void test_subscribe_default() throws Exception {

        final AuthorizationResult authResult = new AuthorizationResult() {
            @Override
            public List<MqttTopicPermission> getMqttTopicPermissions() {
                return Lists.newArrayList(
                        new MqttTopicPermission("test/2/#", TYPE.ALLOW),
                        new MqttTopicPermission("test/3/#", TYPE.DENY)
                );
            }

            @Override
            public AuthorizationBehaviour getDefaultBehaviour() {
                return NEXT;
            }
        };

        assertEquals(NEXT, AuthorizationEvaluator.checkSubscription("test/1/2/3", QoS.AT_LEAST_ONCE, authResult));
    }

    @Test
    public void test_subscribe_match_multi() throws Exception {

        final AuthorizationResult authResult = new AuthorizationResult() {
            @Override
            public List<MqttTopicPermission> getMqttTopicPermissions() {
                return Lists.newArrayList(
                        new MqttTopicPermission("test/2/#", TYPE.DENY),
                        new MqttTopicPermission("test/3/#", TYPE.DENY),
                        new MqttTopicPermission("test/1/#", TYPE.ALLOW),
                        new MqttTopicPermission("test/3/#", TYPE.DENY)
                );
            }

            @Override
            public AuthorizationBehaviour getDefaultBehaviour() {
                return DENY;
            }
        };

        assertEquals(ACCEPT, AuthorizationEvaluator.checkSubscription("test/1/2/3", QoS.AT_LEAST_ONCE, authResult));
    }

    @Test
    public void test_subscribe_deny() throws Exception {

        final AuthorizationResult authResult = new AuthorizationResult() {
            @Override
            public List<MqttTopicPermission> getMqttTopicPermissions() {
                return Lists.newArrayList(
                        new MqttTopicPermission("test/1/#", TYPE.DENY)
                );
            }

            @Override
            public AuthorizationBehaviour getDefaultBehaviour() {
                return ACCEPT;
            }
        };

        assertEquals(DENY, AuthorizationEvaluator.checkSubscription("test/1/2/3", QoS.AT_LEAST_ONCE, authResult));
    }


}