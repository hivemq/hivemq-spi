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
import com.dcsquare.hivemq.spi.security.ClientData;
import com.dcsquare.hivemq.spi.security.Restriction;

import java.util.Set;

/**
 * This callback gets executed after a CONNECT message arrived and
 * the client was authenticated successfully.
 * <p/>
 * This callback provides {@link Restriction}s for a given client
 * which allows e.g. throttling.
 * <p/>
 * This callback is only executed once after a login
 *
 * @author Dominik Obermaier
 * @author Chrisitan Goetz
 * @since 1.4
 */
public interface RestrictionsAfterLoginCallback extends SynchronousCallback {

    /**
     * Gets called after a successful login of a client.
     * <p/>
     * Provides {@link Restriction}s which allow e.g. throttling of a client
     *
     * @param clientData information about the client
     * @return a set of restrictions for the client
     */
    public Set<Restriction> getRestrictions(ClientData clientData);
}
