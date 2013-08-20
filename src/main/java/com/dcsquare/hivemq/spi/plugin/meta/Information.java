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

package com.dcsquare.hivemq.spi.plugin.meta;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * A annotation which can be used on {@link com.dcsquare.hivemq.spi.HiveMQPluginModule}s
 * to set metadata about the project and the author.
 *
 * @author Dominik Obermaier
 * @since 1.4
 */
@Target({ElementType.TYPE})
@Retention(RUNTIME)
@Documented
public @interface Information {

    /**
     * The name of the plugin
     */
    String name();

    /**
     * The author(s) of the plugin.
     */
    String author() default "no author defined";

    /**
     * The version of the plugin
     */
    String version();

    /**
     * Additional description about the plugin
     */
    String description() default "";
}
