package com.hivemq.spi.callback.webui;

import com.hivemq.spi.annotations.NotNull;
import com.hivemq.spi.callback.Callback;
import com.hivemq.spi.callback.SynchronousCallback;
import com.hivemq.spi.callback.registry.CallbackRegistry;

/**
 * This callback is called, when a web ui user is trying to log in.
 *
 * Use the {@link CallbackRegistry#addCallback(Callback)} method to add a WebUIAuthenticationCallback to HiveMQ.
 *
 * If multiple callbacks are registered, only the one with the highest priority is used.
 *
 * If no callback is registered, the credentials are validated against HiveMQ's config.xml
 *
 * @author Florian Limpöck
 *
 * @since 3.4.2
 */
public interface WebUIAuthenticationCallback extends SynchronousCallback {

    /**
     * Checks the credentials for a WebUI login.
     *
     * return {@link AuthenticationState#SUCCESS} when the authentication with the credentials was successful,
     * {@link AuthenticationState#FAILED} otherwise.
     *
     * @param username the username to check
     * @param password the password to check
     *
    **/
    @NotNull AuthenticationState checkCredentials(final @NotNull String username, final @NotNull String password);

    enum AuthenticationState{

        SUCCESS(0), FAILED(1);

        final int code;

        AuthenticationState(final int code) {
            this.code = code;
        }

        public AuthenticationState fromCode(final int code){
            for (AuthenticationState value : values()) {
                if(value.code == code){
                    return value;
                }
            }
            throw new IllegalArgumentException("There is no AuthenticationState available for code: " + code);
        }
    }

}
