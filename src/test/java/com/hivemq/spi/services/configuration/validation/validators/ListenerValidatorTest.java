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

package com.hivemq.spi.services.configuration.validation.validators;

import com.hivemq.spi.services.configuration.entity.TcpListener;
import com.hivemq.spi.services.configuration.entity.Tls;
import com.hivemq.spi.services.configuration.entity.TlsTcpListener;
import com.hivemq.spi.services.configuration.entity.WebsocketListener;
import com.hivemq.spi.services.configuration.validation.ValidationError;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ListenerValidatorTest {

    private ListenerValidator listenerValidator;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {

        listenerValidator = new ListenerValidator();
    }


    /* **************
     * TCP Listener *
     ***************/

    @Test
    public void test_tcp_listener_validation_successful() throws Exception {
        final TcpListener listener = new TcpListener(1025, "host");
        final List<ValidationError> validationErrors = listenerValidator.validate(listener, "");

        assertEquals(0, validationErrors.size());
    }

    @Test
    public void test_tcp_listener_validation_invalid_port_minus_values() throws Exception {
        final TcpListener listener = new TcpListener(-1, "host");
        final List<ValidationError> validationErrors = listenerValidator.validate(listener, "");

        assertEquals(1, validationErrors.size());
    }

    @Test
    public void test_tcp_listener_validation_invalid_port_zero() throws Exception {
        final TcpListener listener = new TcpListener(0, "host");
        final List<ValidationError> validationErrors = listenerValidator.validate(listener, "");

        assertEquals(1, validationErrors.size());
    }

    @Test
    public void test_tpc_listener_validation_invalid_port_higher_than_65535() throws Exception {
        final TcpListener listener = new TcpListener(65536, "host");
        final List<ValidationError> validationErrors = listenerValidator.validate(listener, "");

        assertEquals(1, validationErrors.size());
    }


    @Test
    public void test_check_validation_results_immutable() throws Exception {
        final List<ValidationError> results = listenerValidator.validate(new TcpListener(1025, "host"), "");
        try {
            results.add(new ValidationError("additionalError"));
            fail();
        } catch (Exception e) {
            // Expected
        }

        try {
            results.clear();
            fail();
        } catch (Exception e) {
            //Expected
        }
    }




    /* *********************
     * Websocket Listener *
     **********************/


    @Test
    public void test_validation_successful() throws Exception {
        final WebsocketListener listener = new WebsocketListener.Builder().port(1025).bindAddress("localhost").build();
        final List<ValidationError> validationErrors = listenerValidator.validate(listener, "");

        assertEquals(0, validationErrors.size());
    }


    /* ********
     *   TLS  *
     **********/

    @Test
    public void test_invalid_handshake_timeout() throws Exception {


        final Tls tls = new Tls("", "", "JKS", "", "", "", "JKS", -1,
                Tls.ClientAuthMode.NONE, new ArrayList<String>(), new ArrayList<String>());


        final List<ValidationError> validate = listenerValidator.validate(new TlsTcpListener(1883, "0.0.0.0", tls), "");

        assertEquals(1, validate.size());
    }

    @Test
    public void test_valid_handshake_timeout() throws Exception {

        final Tls tls = new Tls("", "", "JKS", "", "", "", "JKS", 10,
                Tls.ClientAuthMode.NONE, new ArrayList<String>(), new ArrayList<String>());

        final List<ValidationError> validate = listenerValidator.validate(new TlsTcpListener(1883, "0.0.0.0", tls), "");

        assertEquals(0, validate.size());
    }

    @Test
    public void test_valid_handshake_timeout_zero() throws Exception {

        final Tls tls = new Tls("", "", "JKS", "", "", "", "JKS", 0,
                Tls.ClientAuthMode.NONE, new ArrayList<String>(), new ArrayList<String>());

        final List<ValidationError> validate = listenerValidator.validate(new TlsTcpListener(1883, "0.0.0.0", tls), "");

        assertEquals(0, validate.size());
    }

    @Test
    public void test_invalid_keystore_type() throws Exception {

        final Tls tls = new Tls("", "", "NOTASTORETYPE", "", "", "", "JKS", 0,
                Tls.ClientAuthMode.NONE, new ArrayList<String>(), new ArrayList<String>());

        final List<ValidationError> validate = listenerValidator.validate(new TlsTcpListener(1883, "0.0.0.0", tls), "");

        assertEquals(1, validate.size());
    }

    @Test
    public void test_valid_keystore_type_jks() throws Exception {

        final Tls tls = new Tls("", "", "JKS", "", "", "", "JKS", 0,
                Tls.ClientAuthMode.NONE, new ArrayList<String>(), new ArrayList<String>());

        final List<ValidationError> validate = listenerValidator.validate(new TlsTcpListener(1883, "0.0.0.0", tls), "");

        assertEquals(0, validate.size());
    }

    @Test
    public void test_valid_keystore_type_pkcs12() throws Exception {
        final Tls tls = new Tls("", "", "PKCS12", "", "", "", "JKS", 0,
                Tls.ClientAuthMode.NONE, new ArrayList<String>(), new ArrayList<String>());

        final List<ValidationError> validate = listenerValidator.validate(new TlsTcpListener(1883, "0.0.0.0", tls), "");

        assertEquals(0, validate.size());
    }

    @Test
    public void test_invalid_truststore_type() throws Exception {

        final Tls tls = new Tls("", "", "JKS", "", "", "", "NOTASTORETYPE", 0,
                Tls.ClientAuthMode.NONE, new ArrayList<String>(), new ArrayList<String>());

        final List<ValidationError> validate = listenerValidator.validate(new TlsTcpListener(1883, "0.0.0.0", tls), "");

        assertEquals(1, validate.size());
    }

    @Test
    public void test_valid_truststore_type_jks() throws Exception {

        final Tls tls = new Tls("", "", "JKS", "", "", "", "JKS", 0,
                Tls.ClientAuthMode.NONE, new ArrayList<String>(), new ArrayList<String>());

        final List<ValidationError> validate = listenerValidator.validate(new TlsTcpListener(1883, "0.0.0.0", tls), "");

        assertEquals(0, validate.size());
    }

    @Test
    public void test_valid_truststore_type_pkcs12() throws Exception {
        final Tls tls = new Tls("", "", "JKS", "", "", "", "PKCS12", 0,
                Tls.ClientAuthMode.NONE, new ArrayList<String>(), new ArrayList<String>());

        final List<ValidationError> validate = listenerValidator.validate(new TlsTcpListener(1883, "0.0.0.0", tls), "");

        assertEquals(0, validate.size());
    }

    @Test
    public void test_valid_keystore_path() throws Exception {

        final File tempFile = temporaryFolder.newFile("tempfile");

        final Tls tls = new Tls(tempFile.getAbsolutePath(), "", "JKS", "", "", "", "JKS", 0,
                Tls.ClientAuthMode.NONE, new ArrayList<String>(), new ArrayList<String>());

        final List<ValidationError> validate = listenerValidator.validate(new TlsTcpListener(1883, "0.0.0.0", tls), "");

        assertEquals(0, validate.size());
    }

    @Test
    public void test_keystore_path_not_exists() throws Exception {

        final Tls tls = new Tls(RandomStringUtils.randomAlphabetic(32), "", "JKS", "", "", "", "JKS", 0,
                Tls.ClientAuthMode.NONE, new ArrayList<String>(), new ArrayList<String>());

        final List<ValidationError> validate = listenerValidator.validate(new TlsTcpListener(1883, "0.0.0.0", tls), "");

        assertEquals(1, validate.size());
    }

    @Test
    public void test_keystore_path_not_readable() throws Exception {

        final File tempFile = temporaryFolder.newFile("tempfile");
        assertTrue(tempFile.setReadable(false));

        final Tls tls = new Tls(tempFile.getAbsolutePath(), "", "JKS", "", "", "", "JKS", 0,
                Tls.ClientAuthMode.NONE, new ArrayList<String>(), new ArrayList<String>());

        final List<ValidationError> validate = listenerValidator.validate(new TlsTcpListener(1883, "0.0.0.0", tls), "");

        assertEquals(1, validate.size());
    }

    @Test
    public void test_valid_truststore_path() throws Exception {

        final File tempFile = temporaryFolder.newFile("tempfile");

        final Tls tls = new Tls("", "", "JKS", "", tempFile.getAbsolutePath(), "", "JKS", 0,
                Tls.ClientAuthMode.NONE, new ArrayList<String>(), new ArrayList<String>());

        final List<ValidationError> validate = listenerValidator.validate(new TlsTcpListener(1883, "0.0.0.0", tls), "");

        assertEquals(0, validate.size());
    }

    @Test
    public void test_truststore_path_not_exists() throws Exception {

        final Tls tls = new Tls("", "", "JKS", "", RandomStringUtils.randomAlphabetic(32), "", "JKS", 0,
                Tls.ClientAuthMode.NONE, new ArrayList<String>(), new ArrayList<String>());

        final List<ValidationError> validate = listenerValidator.validate(new TlsTcpListener(1883, "0.0.0.0", tls), "");

        assertEquals(1, validate.size());
    }

    @Test
    public void test_truststore_path_not_readable() throws Exception {

        final File tempFile = temporaryFolder.newFile("tempfile");
        assertTrue(tempFile.setReadable(false));

        final Tls tls = new Tls("", "", "JKS", "", tempFile.getAbsolutePath(), "", "JKS", 0,
                Tls.ClientAuthMode.NONE, new ArrayList<String>(), new ArrayList<String>());

        final List<ValidationError> validate = listenerValidator.validate(new TlsTcpListener(1883, "0.0.0.0", tls), "");

        assertEquals(1, validate.size());
    }

}