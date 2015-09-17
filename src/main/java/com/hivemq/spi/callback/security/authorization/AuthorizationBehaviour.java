package com.hivemq.spi.callback.security.authorization;

/**
 * The Authorization behaviour which defines if
 * an authorization was granted, denied or if
 * another callback should be called
 *
 * @author Christoph Schäbel
 * @since 3.0
 */
public enum AuthorizationBehaviour {

    ACCEPT,
    DENY,
    NEXT
}
