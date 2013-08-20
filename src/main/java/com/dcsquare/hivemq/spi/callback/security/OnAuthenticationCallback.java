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

package com.dcsquare.hivemq.spi.callback.security;

import com.dcsquare.hivemq.spi.callback.SynchronousCallback;
import com.dcsquare.hivemq.spi.callback.exception.AuthenticationException;
import com.dcsquare.hivemq.spi.security.ClientCredentialsData;

/**
 * This callback gets called when a CONNECT message arrives and is meant to
 * perform the authentication of a client.
 * <p/>
 * When more OnAuthenticationCallbacks are added only one must return
 * a positive result to successfully authenticate the client.
 * <p/>
 * If an {@link AuthenticationException} is thrown, no additional
 * credentials checks in other plugins are made and the client is
 * disconnected immediately with an CONNACK with the given return code.
 * <p/>
 * Note: It's your responsibility to use proper caching in the <code>checkCredentials</code> method
 *
 * @author Christian Goetz
 * @since 1.4
 */
public interface OnAuthenticationCallback extends SynchronousCallback {

    /**
     * Checks the credentials after a CONNECT message arrives.
     * <p/>
     * return <code>true</code> when the authentication with the credentials was successful, <code>false</code> otherwise
     *
     * @param clientData the client credentials
     * @return <code>true</code> when the authentication was successful
     * @throws AuthenticationException when you want the client to disconnect immediately with a given return code
     */
    public Boolean checkCredentials(ClientCredentialsData clientData) throws AuthenticationException;

}
