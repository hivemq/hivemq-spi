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

package com.dcsquare.hivemq.spi.bridge;

/**
 * The state of a bridge.
 * <p/>
 * The state machine is as follows:
 * <p/>
 * <code>STOPPED -> STARTING -> RUNNING -> STOPPING -> STOPPED</code>
 * <p/>
 * If something bad happens, the bridge can get in state <code>FAILURE</code>.
 *
 * @author Dominik Obermaier
 * @since 2.0
 */
public enum State {

    /**
     * The bridge is stopped at the moment
     */
    STOPPED,
    /**
     * The bridge is starting and will be available soon
     */
    STARTING,
    /**
     * The bridge is up and running
     */
    RUNNING,
    /**
     * The bridge is in failure state. This is typically a fatal problem
     * and restarting could possibly be impossible.
     */
    FAILURE,
    /**
     * The bridge is stopping at the moment and will be shutdown soon
     */
    STOPPING
}