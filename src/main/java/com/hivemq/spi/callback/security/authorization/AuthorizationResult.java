package com.hivemq.spi.callback.security.authorization;

import com.hivemq.spi.annotations.NotNull;
import com.hivemq.spi.topic.MqttTopicPermission;

import java.util.List;

/**
 * The Result of one OnAuthorizationCallback call
 *
 * @author Christoph Schäbel
 */
public interface AuthorizationResult {

    List<MqttTopicPermission> getMqttTopicPermissions();

    @NotNull
    AuthorizationBehaviour getDefaultBehaviour();
}
