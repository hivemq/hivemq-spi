package com.hivemq.spi.services.configuration.validation.validators;

import com.google.common.collect.ImmutableList;
import com.hivemq.spi.services.configuration.validation.ValidationError;
import com.hivemq.spi.services.configuration.validation.Validator;

import java.util.List;

/**
 * @author Lukas Brandl
 */
public class TtlValidator implements Validator<Integer> {

    @Override
    public List<ValidationError> validate(final Integer clientSessionTtl, final String name) {

        final ImmutableList.Builder<ValidationError> validationErrors = ImmutableList.builder();
        if (clientSessionTtl < 1 && clientSessionTtl != -1) {
            validationErrors.add(new ValidationError("%d is an invalid time to live. A valid ttl must be -1 or more than 1.", clientSessionTtl));
        }
        return validationErrors.build();
    }
}
