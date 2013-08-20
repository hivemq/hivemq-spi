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

package com.dcsquare.hivemq.spi.security;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Christian Goetz
 */
public class RestrictionTest {

    private Restriction restriction;
    private Long expectedValue;
    private RestrictionType expectedType;

    @Before
    public void setUp() throws Exception {
        expectedValue = 123l;
        expectedType = RestrictionType.MAX_INCOMING_BYTES;
        restriction = new Restriction(expectedType, expectedValue);


    }

    @Test
    public void testGetValue() throws Exception {
        assertEquals(restriction.getValue(), expectedValue);
    }

    @Test
    public void testGetType() throws Exception {
          assertEquals(restriction.getType(),expectedType);
    }

    @Test
    public void testEquals() throws Exception {
        Restriction restriction1 = new Restriction(RestrictionType.MAX_INCOMING_BYTES,123l);
        assertTrue(restriction1.equals(restriction));
    }

    @Test
    public void testHashCode() throws Exception {
        Restriction restriction1 = new Restriction(RestrictionType.MAX_INCOMING_BYTES,123l);
        assertEquals(restriction1.hashCode(), restriction.hashCode());
    }

    @Test
    public void testToString() throws Exception {
        String expectedToString = "Restriction{type=MAX_INCOMING_BYTES, value=123}";
        assertEquals(restriction.toString(), expectedToString);
    }
}
