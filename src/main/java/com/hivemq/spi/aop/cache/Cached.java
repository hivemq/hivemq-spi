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

package com.hivemq.spi.aop.cache;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * Caches the return value of a method for a specific time. It takes the parameters
 * of the method into account, when deciding if this method has already been cached.
 * Very useful for expensive methods
 * (like database lookups) which are called regularly.
 * <p/>
 * Only works on objects created by Guice.
 *
 * @author Christian Goetz
 * @since 1.4
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface Cached {

    /**
     * The total time how long the return value of this method should be cached
     */
    long timeToLive();

    /**
     * The {@link TimeUnit} for the timeToLive. Defaults to Milliseconds
     */
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;
}
