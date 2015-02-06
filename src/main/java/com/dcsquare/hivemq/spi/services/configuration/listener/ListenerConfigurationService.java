/*
 * Copyright 2015 dc-square GmbH
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

package com.dcsquare.hivemq.spi.services.configuration.listener;

import com.dcsquare.hivemq.spi.annotations.ReadOnly;
import com.dcsquare.hivemq.spi.services.configuration.SubmoduleConfiguration;
import com.dcsquare.hivemq.spi.services.configuration.entity.*;
import com.dcsquare.hivemq.spi.services.configuration.exception.ConfigurationNotOverridableException;
import com.dcsquare.hivemq.spi.services.configuration.exception.ConfigurationValidationException;

import java.util.List;

/**
 * @author Dominik Obermaier
 */
public interface ListenerConfigurationService extends SubmoduleConfiguration {


    <T extends Listener> void addListener(final T listener) throws ConfigurationNotOverridableException, ConfigurationValidationException, IllegalArgumentException;

    @ReadOnly
    List<Listener> getListeners();

    @ReadOnly
    List<TcpListener> getTcpListeners();

    @ReadOnly
    List<TlsTcpListener> getTlsTcpListeners();

    @ReadOnly
    List<WebsocketListener> getWebsocketListeners();

    @ReadOnly
    List<TlsWebsocketListener> getTlsWebsocketListeners();

    boolean isOverridable();


    TcpListenerUpdater updateListener(TcpListener listener) throws ConfigurationNotOverridableException;

    TlsTcpListenerUpdater updateListener(TlsTcpListener listener) throws ConfigurationNotOverridableException;

    WebsocketListenerUpdater updateListener(WebsocketListener listener) throws ConfigurationNotOverridableException;

    TlsWebsocketListenerUpdater updateListener(TlsWebsocketListener listener) throws ConfigurationNotOverridableException;


}
