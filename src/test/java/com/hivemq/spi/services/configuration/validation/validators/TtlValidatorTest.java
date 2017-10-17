package com.hivemq.spi.services.configuration.validation.validators;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Lukas Brandl
 */
public class TtlValidatorTest {

    private TtlValidator ttlValidator;

    @Before
    public void setUp() throws Exception {
        ttlValidator = new TtlValidator();
    }

    @Test
    public void test_valid_client_identifiers() throws Exception {
        assertEquals(true, ttlValidator.validate(100, "").isEmpty());
    }

    @Test
    public void test_ttl_disabled() throws Exception {
        assertEquals(true, ttlValidator.validate(-1, "").isEmpty());
    }

    @Test
    public void test_zero_ttl() throws Exception {
        assertEquals(1, ttlValidator.validate(0, "").size());
    }

    @Test
    public void test_negative_ttl() throws Exception {
        assertEquals(1, ttlValidator.validate(-2, "").size());
    }
}