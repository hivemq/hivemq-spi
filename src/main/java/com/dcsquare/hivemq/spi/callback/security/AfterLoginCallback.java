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

package com.dcsquare.hivemq.spi.callback.security;

import com.dcsquare.hivemq.spi.callback.AsynchronousCallback;
import com.dcsquare.hivemq.spi.callback.exception.AuthenticationException;
import com.dcsquare.hivemq.spi.security.ClientData;

/**
 * The methods of this callback get called when a login attempt was made. Potential use cases for this callback are:
 * <p/>
 * - You want to log when someone tries to log in.
 * - Execute custom logic which should occur after a login attempt
 * <p/>
 * No authentication and authorization logic should be implemented here,
 * please use {@link com.dcsquare.hivemq.spi.callback.security.OnAuthenticationCallback} or {@link com.dcsquare.hivemq.spi.callback.security.OnAuthorizationCallback} for that purpose.
 *
 * @author Dominik Obermaier
 * @author Christian Goetz
 * @since 1.4
 */
public interface AfterLoginCallback extends AsynchronousCallback {

    /**
     * Called after a client was granted authentication
     *
     * @param clientData the information about the client
     */
    void afterSuccessfulLogin(final ClientData clientData);

    /**
     * Called after a client was denied authentication
     * The client will be disconnected after calling this method.
     *
     * @param exception  the exception which has details why the client had an unsuccessful login attempt.
     * @param clientData information about the client
     */
    void afterFailedLogin(AuthenticationException exception, final ClientData clientData);
}
