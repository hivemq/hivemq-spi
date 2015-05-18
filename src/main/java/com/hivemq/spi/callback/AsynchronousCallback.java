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
 * A callback which gets executed asynchronously.
 * <p/>
 * Asynchronous callbacks are executed at the same time.
 * Although the execution of the callbacks is asynchronous,
 * most callbacks get synchronized after executing to receive the results.
 * <p/>
 * Don't implement this interface on your own, use a more concrete interface when you want
 * to add a callback
 *
 * @author Christian Goetz
 * @since 1.4
 */
public interface AsynchronousCallback extends Callback {
}
