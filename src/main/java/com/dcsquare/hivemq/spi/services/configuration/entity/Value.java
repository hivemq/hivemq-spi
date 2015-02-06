/*
 * Copyright 2015 dc-square GmbH
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

package com.dcsquare.hivemq.spi.services.configuration.entity;

import com.dcsquare.hivemq.spi.annotations.Immutable;
import com.google.common.base.Preconditions;

/**
 * A wrapper for configuration data which can either be overridable or static.
 * <p/>
 * This wrapper is only useful for single data, consider using a
 * {@link com.dcsquare.hivemq.spi.services.configuration.entity.ValueList} if
 * you are looking for a wrapper for lists.
 *
 * @author Dominik Obermaier
 */
@Immutable
public class Value<T> {

    private final T value;
    private final boolean overridable;

    /**
     * Private constructor. Use the static factory methods
     * to construct a concrete Value
     *
     * @param value       the value
     * @param overridable if the value should be overridable
     */
    private Value(final T value, final boolean overridable) {
        Preconditions.checkNotNull(value, "A Value can never be null");
        this.value = value;
        this.overridable = overridable;
    }

    /**
     * Creates a new Value object which is overridable.
     *
     * @param value the value
     * @param <T>   the type of the value
     * @return a overridable Value
     */
    public static <T> Value<T> overridableValue(final T value) {
        return new Value<>(value, true);
    }

    /**
     * Creates a new static Value object, which is a value that is not overridable
     *
     * @param value the value
     * @param <T>   the type of the value
     * @return a static, not overridable value
     */
    public static <T> Value<T> staticValue(final T value) {
        return new Value<>(value, false);
    }

    /**
     * Creates a new Value object
     *
     * @param value       the value
     * @param overridable if the value should be overridable
     * @param <T>         the type of the values
     * @return a new Value objects
     */
    public static <T> Value<T> of(final T value, final boolean overridable) {
        return new Value<>(value, overridable);
    }

    /**
     * Returns the actual value of the Value wrapper object
     *
     * @return the actual value of the Value wrapper object
     */
    public T getValue() {
        return value;
    }

    /**
     * Returns <code>true</code> if the value is overridable, <code>false</code> otherwise
     *
     * @return <code>true</code> if the value is overridable, <code>false</code> otherwise
     */
    public boolean isOverridable() {
        return overridable;
    }


    /**
     * Creates a copy of the original value. In case of primitive type wrappers,
     * this will create a deep copy.
     * <p/>
     * Returns <code>null</code> if the passed original value is also <code>null</code>
     *
     * @param original the original value to copy from
     * @param <T>      the type of the original value
     * @return a copy of the original value
     */
    public static <T> Value<T> copy(final Value<T> original) {
        if (original == null) {
            return null;
        }
        if (original.isOverridable()) {
            return Value.overridableValue(original.getValue());
        } else {
            return Value.staticValue(original.getValue());
        }
    }
}
