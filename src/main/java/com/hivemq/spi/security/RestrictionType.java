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

package com.hivemq.spi.security;

/**
 * The different types of restrictions which are available.
 *
 * @author Dominik Obermaier
 * @since 1.4
 */

public enum RestrictionType {
    /**
     * The maximum size of a message the user can send.
     */
    MAX_PUBLISH_MESSAGE_SIZE,
    /**
     * The throttling for sending messages out to the client. This is broker -> client
     */
    MAX_OUTGOING_BYTES_SEC,
    /**
     * The throttling for receiving messages from the client. This is client -> broker
     */
    MAX_INCOMING_BYTES
}
