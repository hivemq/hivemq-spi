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

package com.hivemq.spi.callback.exception;

import com.hivemq.spi.message.ReturnCode;
import com.hivemq.spi.callback.security.OnAuthenticationCallback;

/**
 * Indicates that the authentication of a certain client failed.
 * As a consequence the client gets disconnected from the broker.
 * The return code, which is sent back to the client, signals what
 * the cause of the failed authentication is. For a complete list of
 * return codes see {@link ReturnCode}.
 * <p/>
 * This exception should be used in an implementation of
 * {@link OnAuthenticationCallback}
 *
 * @author Christian Goetz
 * @since 1.4
 */
public class AuthenticationException extends Exception {

    private ReturnCode returnCode;

    public AuthenticationException(final ReturnCode returnCode) {
        super();
        this.returnCode = returnCode;
    }

    public AuthenticationException(final String message, final ReturnCode returnCode) {
        super(message);
        this.returnCode = returnCode;
    }

    public ReturnCode getReturnCode() {
        return returnCode;
    }
}
