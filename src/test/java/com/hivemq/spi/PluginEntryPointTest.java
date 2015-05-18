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

package com.hivemq.spi;

import com.hivemq.spi.callback.Callback;
import com.hivemq.spi.callback.registry.CallbackRegistry;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.HashSet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * @author Dominik Obermaier
 */
public class PluginEntryPointTest {

    @Mock
    CallbackRegistry registry;


    private Injector injector;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);

        final HashSet<Callback> callbackValues = new HashSet<Callback>();
        callbackValues.add(new Callback() {
        });
        when(registry.getAllCallbacks()).thenReturn(callbackValues);
        injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(CallbackRegistry.class).toInstance(registry);

                bind(String.class).toInstance("testString");
            }
        });

    }

    @Test
    public void test_setter_injection_working() throws Exception {


        final TestHiveMQPluginEntryPoint instance = injector.getInstance(TestHiveMQPluginEntryPoint.class);

        final CallbackRegistry callbackRegistry = instance.getCallbackRegistry();
        assertNotNull(callbackRegistry);

        assertEquals(1, callbackRegistry.getAllCallbacks().size());
    }


    @Test
    public void test_constructor_injection_working() throws Exception {
        final TestHiveMQPluginEntryPointWithConstructorInjection instance = injector.getInstance(TestHiveMQPluginEntryPointWithConstructorInjection.class);

        assertNotNull(instance.getParamter());

        assertEquals("testString", instance.getParamter());

        assertSame(instance.getCallbackRegistry2(), instance.getCallbackRegistry());
    }

    public static class TestHiveMQPluginEntryPoint extends PluginEntryPoint {

    }


    public static class TestHiveMQPluginEntryPointWithConstructorInjection extends PluginEntryPoint {

        private final String paramter;
        private final CallbackRegistry callbackRegistry2;

        @Inject
        public TestHiveMQPluginEntryPointWithConstructorInjection(String paramter, final CallbackRegistry callbackRegistry2) {
            this.paramter = paramter;
            this.callbackRegistry2 = callbackRegistry2;
        }

        public String getParamter() {
            return paramter;
        }

        public CallbackRegistry getCallbackRegistry2() {
            return callbackRegistry2;
        }
    }

    public static class TestHiveMQPluginEntryPointWithPostConstruct {

        private String setAfterPostconstruct;

        @PostConstruct
        public void postConstruct() {
            setAfterPostconstruct = "set!";
        }

        public String getSetAfterPostconstruct() {
            return setAfterPostconstruct;
        }
    }

}
