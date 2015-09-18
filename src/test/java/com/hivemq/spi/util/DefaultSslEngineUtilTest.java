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

package com.hivemq.spi.util;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @author Christoph Schäbel
 * @author Dominik Obermaier
 */
public class DefaultSslEngineUtilTest {

    @Test
    public void test_supported_protocols_available() throws Exception {
        final List<String> supportedProtocols = new DefaultSslEngineUtil().getSupportedProtocols();
        assertTrue(supportedProtocols.size() > 0);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_supported_protocols_immutable() throws Exception {
        final List<String> supportedProtocols = new DefaultSslEngineUtil().getSupportedProtocols();

        supportedProtocols.add("another");
    }


    @Test
    public void test_supported_cipher_suites_available() throws Exception {
        final List<String> supportedCipherSuites = new DefaultSslEngineUtil().getSupportedCipherSuites();
        assertTrue(supportedCipherSuites.size() > 0);
    }


    @Test(expected = UnsupportedOperationException.class)
    public void test_supported_cipher_suites_immutable() throws Exception {
        final List<String> supportedCipherSuites = new DefaultSslEngineUtil().getSupportedCipherSuites();

        supportedCipherSuites.add("another");
    }


    @Test
    public void test_enabled_protocols_available() throws Exception {
        final List<String> enabledProtocols = new DefaultSslEngineUtil().getEnabledProtocols();
        assertTrue(enabledProtocols.size() > 0);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_enabled_protocols_immutable() throws Exception {
        final List<String> enabledProtocols = new DefaultSslEngineUtil().getEnabledProtocols();

        enabledProtocols.add("another");
    }


    @Test
    public void test_enabled_cipher_suites_available() throws Exception {
        final List<String> enabledCipherSuites = new DefaultSslEngineUtil().getEnabledCipherSuites();
        assertTrue(enabledCipherSuites.size() > 0);
    }


    @Test(expected = UnsupportedOperationException.class)
    public void test_enabled_cipher_suites_immutable() throws Exception {
        final List<String> enabledCipherSuites = new DefaultSslEngineUtil().getEnabledCipherSuites();

        enabledCipherSuites.add("another");
    }

}