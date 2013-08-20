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

package com.dcsquare.hivemq.spi.callback.exception;

/**
 * This exception can be thrown when a PUBLISH arrives but the PUBLISH is invalid.
 * <p/>
 * It's possible to disconnect the client when passing <code>true</code> as parameter.
 *
 * @author Dominik Obermaier
 * @since 1.4
 */
public class OnPublishReceivedException extends Exception {

    private final boolean disconnectClient;

    public OnPublishReceivedException(final boolean disconnectClient) {
        super();
        this.disconnectClient = disconnectClient;
    }

    public OnPublishReceivedException(final String message, final boolean disconnectClient) {
        super(message);
        this.disconnectClient = disconnectClient;
    }

    public OnPublishReceivedException(final String message, final Throwable cause, final boolean disconnectClient) {
        super(message, cause);
        this.disconnectClient = disconnectClient;
    }

    public OnPublishReceivedException(final Throwable cause, final boolean disconnectClient) {
        super(cause);
        this.disconnectClient = disconnectClient;
    }

    /**
     * @return if the client should be disconnected
     */
    public boolean getDisconnectClient() {
        return disconnectClient;
    }
}
