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

package com.dcsquare.hivemq.spi.services.configuration.validation.validators;

import com.dcsquare.hivemq.spi.services.configuration.validation.ValidationError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ZeroablePositiveNumberTest {

    @Test
    public void test_zero_valid() throws Exception {
        final ZeroablePositiveNumber zeroablePositiveNumber = new ZeroablePositiveNumber();
        final List<ValidationError> validationErrors = zeroablePositiveNumber.validate(0, "test");

        assertEquals(true, validationErrors.isEmpty());
    }

    @Test
    public void test_positive_number_valid() throws Exception {
        final ZeroablePositiveNumber zeroablePositiveNumber = new ZeroablePositiveNumber();
        final List<ValidationError> validationErrors = zeroablePositiveNumber.validate(1, "test");

        assertEquals(true, validationErrors.isEmpty());
    }

    @Test
    public void test_negative_number_invalid() throws Exception {
        final ZeroablePositiveNumber zeroablePositiveNumber = new ZeroablePositiveNumber();
        final List<ValidationError> validationErrors = zeroablePositiveNumber.validate(-1, "test");

        assertEquals(1, validationErrors.size());
        System.out.println(validationErrors.get(0).getMessage());
    }
}