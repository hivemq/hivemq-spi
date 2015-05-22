package com.hivemq.spi.security;

import com.hivemq.spi.callback.security.authorization.AuthorizationBehaviour;
import com.hivemq.spi.callback.security.authorization.AuthorizationResult;
import com.hivemq.spi.message.QoS;
import com.hivemq.spi.topic.MqttTopicPermission;

import java.util.List;

import static com.hivemq.spi.callback.security.authorization.AuthorizationBehaviour.ACCEPT;
import static com.hivemq.spi.callback.security.authorization.AuthorizationBehaviour.DENY;
import static com.hivemq.spi.topic.MqttTopicPermission.ACTIVITY;
import static com.hivemq.spi.topic.MqttTopicPermission.TYPE;

/**
 * Evaluator for the results of a OnAuthorizationCallback against a PUBLISH or a subscription.
 * It is used by HiveMQ to check, whether a publish or subscribe are permitted.
 *
 * @author Christoph Schäbel
 */
public class AuthorizationEvaluator {

    public static AuthorizationBehaviour checkPublish(final String topic, final QoS qos, final boolean retained, final AuthorizationResult authorizationResult) {

        final List<MqttTopicPermission> mqttTopicPermissions = authorizationResult.getMqttTopicPermissions();

        if (mqttTopicPermissions == null || mqttTopicPermissions.size() < 1) {
            return authorizationResult.getDefaultBehaviour();
        }

        for (MqttTopicPermission mqttTopicPermission : mqttTopicPermissions) {
            if (mqttTopicPermission.implies(topic, qos, ACTIVITY.PUBLISH, retained)) {
                return mqttTopicPermission.getType() == TYPE.ALLOW ? ACCEPT : DENY;
            }
        }

        return authorizationResult.getDefaultBehaviour();
    }

    public static AuthorizationBehaviour checkSubscription(final String topic, final QoS qoS, final AuthorizationResult authorizationResult) {

        final List<MqttTopicPermission> mqttTopicPermissions = authorizationResult.getMqttTopicPermissions();

        if (mqttTopicPermissions == null || mqttTopicPermissions.size() < 1) {
            return authorizationResult.getDefaultBehaviour();
        }

        for (MqttTopicPermission mqttTopicPermission : mqttTopicPermissions) {
            if (mqttTopicPermission.implies(topic, qoS, ACTIVITY.SUBSCRIBE)) {
                return mqttTopicPermission.getType() == TYPE.ALLOW ? ACCEPT : DENY;
            }
        }

        return authorizationResult.getDefaultBehaviour();
    }
}
