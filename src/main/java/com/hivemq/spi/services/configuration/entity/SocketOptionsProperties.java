/*
 * Copyright 2017 dc-square GmbH
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

package com.hivemq.spi.services.configuration.entity;

import com.google.common.base.Optional;
import com.hivemq.spi.annotations.NotNull;
import com.hivemq.spi.annotations.Nullable;

/**
 * Properties for socket send and receive buffer sizes.
 */
public class SocketOptionsProperties {

    private final Optional<Integer> sendBufferSize;
    private final Optional<Integer> receiveBufferSize;

    /**
     * Creates a {@link SocketOptionsProperties} object.
     *
     * @param sendBufferSize    the send buffer size for every socket this listener creates
     * @param receiveBufferSize the receive buffer size for every socket this listener creates
     */
    private SocketOptionsProperties(@Nullable final Integer sendBufferSize, @Nullable final Integer receiveBufferSize) {
        this.sendBufferSize = Optional.fromNullable(sendBufferSize);
        this.receiveBufferSize = Optional.fromNullable(receiveBufferSize);
    }

    /**
     * Get the send buffer size.
     *
     * @return Optional of the send buffer size
     */
    @NotNull
    public Optional<Integer> getSendBufferSize() {
        return sendBufferSize;
    }

    /**
     * Get the receive buffer size.
     *
     * @return Optional of the receive buffer size
     */
    @NotNull
    public Optional<Integer> getReceiveBufferSize() {
        return receiveBufferSize;
    }

    /**
     * A builder for the SocketOptionsProperties object.
     */
    public static class Builder {

        private Integer sendBufferSize = null;
        private Integer receiveBufferSize = null;

        /**
         * Create a builder.
         */
        public Builder() {
        }

        /**
         * Set the send buffer size.
         * <p>
         * On Unix-like systems this will set the SO_SNDBUF socket option.
         * <p>
         * The OS will do its own validation of this option, the used value can differ significantly.
         *
         * @param sendBufferSize the send buffer size
         * @return the builder
         */
        public Builder sendBufferSize(final int sendBufferSize) {
            this.sendBufferSize = sendBufferSize;
            return this;
        }

        /**
         * Set the receive buffer size.
         * <p>
         * On Unix-like systems this will set the SO_RCVBUF socket option.
         * <p>
         * The OS will do its own validation of this option, the used value can differ significantly.
         *
         * @param receiveBufferSize the receive buffer size
         * @return the builder
         */
        public Builder receiveBufferSize(final int receiveBufferSize) {
            this.receiveBufferSize = receiveBufferSize;
            return this;
        }

        /**
         * Build the properties object.
         *
         * @return a {@link SocketOptionsProperties} object.
         */
        public SocketOptionsProperties build() {
            return new SocketOptionsProperties(this.sendBufferSize, this.receiveBufferSize);
        }
    }
}
