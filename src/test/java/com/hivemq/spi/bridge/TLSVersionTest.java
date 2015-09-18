/*
 * Copyright 2014 dc-square GmbH
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

package com.hivemq.spi.bridge;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Dominik Obermaier
 */
public class TLSVersionTest {

    @Test
    public void test_fromString() throws Exception {
        final TLSVersion tlsVersion1 = TLSVersion.fromString("TLSv1.1");
        assertEquals(TLSVersion.TLSv1_1, tlsVersion1);

        final TLSVersion tlsVersion2 = TLSVersion.fromString("TLS");
        assertEquals(TLSVersion.TLS, tlsVersion2);

        final TLSVersion tlsVersion3 = TLSVersion.fromString("SSLv3");
        assertEquals(TLSVersion.SSLv3, tlsVersion3);

        final TLSVersion tlsVersion4 = TLSVersion.fromString("TLSv1");
        assertEquals(TLSVersion.TLSv1, tlsVersion4);

        final TLSVersion tlsVersion5 = TLSVersion.fromString("TLSv1.2");
        assertEquals(TLSVersion.TLSv1_2, tlsVersion5);
    }
}
