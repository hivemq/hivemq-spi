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

import com.google.common.collect.ImmutableList;
import com.hivemq.spi.services.configuration.validation.ValidationError;
import com.hivemq.spi.services.configuration.validation.Validator;

import java.util.List;

/**
 * A validator which validates that a parameter is higher or equal to 0
 *
 * @author Dominik Obermaier
 * @since 3.0
 */
public class ZeroablePositiveNumber implements Validator<Number> {

    @Override
    public List<ValidationError> validate(final Number number, final String name) {

        final ImmutableList.Builder<ValidationError> validationErrors = ImmutableList.builder();
        if (number.intValue() < 0) {
            validationErrors.add(new ValidationError("Validation failed for %s. The number must be 0 or higher but was %d", name, number));
        }
        return validationErrors.build();
    }
}
