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
 * A validator which validates the maximum client length parameter
 *
 * @author Dominik Obermaier
 * @since 3.0
 */
public class MaxClientIdValidator implements Validator<Integer> {

    @Override
    public List<ValidationError> validate(final Integer clientIdLength, final String name) {

        final ImmutableList.Builder<ValidationError> validationErrors = ImmutableList.builder();
        if (clientIdLength < 1 || clientIdLength > 65535) {
            validationErrors.add(new ValidationError("%d is an invalid client identifier length. A valid client identifer length must have a value between 1 and 65535.", clientIdLength));
        }
        return validationErrors.build();
    }
}
