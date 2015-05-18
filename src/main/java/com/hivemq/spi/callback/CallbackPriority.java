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

package com.hivemq.spi.callback;

/**
 * Useful constants for common priorities
 *
 * @author Christian Goetz
 * @since 1.4
 */
public class CallbackPriority {
    public static int CRITICAL = 1;
    public static int VERY_HIGH = 10;
    public static int HIGH = 50;
    public static int MEDIUM = 100;
    public static int LOW = 500;
    public static int VERY_LOW = 1000;
}
