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

package com.hivemq.spi.topic.sys;

/**
 * An enumeration of all available types for HiveMQ $SYS Topics
 *
 * @author Dominik Obermaier
 * @since 2.0
 */
public enum Type {
    /**
     * A static $SYS topic is a $SYS topic which will be published on the initial $SYS subscription of a client.
     * Typically static $SYS topic values don't change over time.
     */
    STATIC,

    /**
     * A standard $SYS topic which will be published every time the $SYS topic publishing job
     * is running.
     */
    STANDARD
}
