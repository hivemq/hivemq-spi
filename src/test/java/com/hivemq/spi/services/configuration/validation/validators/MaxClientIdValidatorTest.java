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

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MaxClientIdValidatorTest {

    private MaxClientIdValidator maxClientIdValidator;

    @Before
    public void setUp() throws Exception {
        maxClientIdValidator = new MaxClientIdValidator();
    }

    @Test
    public void test_valid_client_identifiers() throws Exception {

        for (int i = 1; i <= 65535; i++) {
            assertEquals(true, maxClientIdValidator.validate(i, "").isEmpty());
        }
    }

    @Test
    public void test_zero_client_identifier() throws Exception {
        assertEquals(1, maxClientIdValidator.validate(0, "").size());
    }

    @Test
    public void test_negative_client_identifier() throws Exception {
        assertEquals(1, maxClientIdValidator.validate(-1, "").size());
    }

    @Test
    public void test_client_identifier_higher_65535() throws Exception {
        assertEquals(1, maxClientIdValidator.validate(65536, "").size());
    }
}