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
 * @author Georg Held
 * @author Lukas Brandl
 * @author Florian Limpöck
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
    MAX_INCOMING_BYTES,

    /**
     * The <b>high</b> fill state of the write buffer for this client, measured in bytes. If this state is reached HiveMQ will prevent additional buffering of writes. QoS 0 messages may be dropped. If this restriction is set {@link #WRITE_BUFFER_LOW_THRESHOLD} must be set as well and the {@link Restriction} value for this must be equal or greater than the value for {@link #WRITE_BUFFER_LOW_THRESHOLD}.
     *
     * @since 3.3
     */
    WRITE_BUFFER_HIGH_THRESHOLD,

    /**
     * The <b>low</b> fill state of the write buffer for this client, measured in bytes. If the write buffer previously exceeded the {@link #WRITE_BUFFER_HIGH_THRESHOLD}, buffering of writes will resume once it drops below this. If this restriction is set {@link #WRITE_BUFFER_HIGH_THRESHOLD} must be set as well and the {@link Restriction} value for this must be greater than zero.
     *
     * @since 3.3
     */
    WRITE_BUFFER_LOW_THRESHOLD,

    /**
     * The maximum size of the offline message queue for this client. This only takes effect for clients with clean-session set to false
     *
     * @since 3.3
     */
    MAX_QUEUED_MESSAGES,

    /**
     * The strategy to apply when messages are dropped because the maximum size of the offline queue is exceeded.
     * You can use the constants from {@link QueuedMessageStrategy} as values.
     *
     * @since 3.3
     */
    DISCARD_STRATEGY,

    /**
     * The time to live of the session of this client.
     * <p/>
     * A value of 0 for the TTL means that the session is immediately invalidated after the client disconnects.
     * A value of -1 for the TTL means that session expiry is disabled.
     *
     * @since 3.4
     */
    CLIENT_SESSION_TTL,

    /**
     * The maximum size of the inflight message queue for this client.
     * This only takes effect for online clients.
     *
     * @since 3.4
     */
    INFLIGHT_QUEUE_SIZE
}
