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

    /**
     * A list of all topic permissions which are granted
     *
     * @return a list of topic permission
     */
    List<MqttTopicPermission> getMqttTopicPermissions();

    /**
     * The default {@link AuthorizationBehaviour} if the getMqttTopicPermissions() method returns
     * an empty list.
     *
     * @return the deafult authorization behaviour
     */
    @NotNull
    AuthorizationBehaviour getDefaultBehaviour();
}
