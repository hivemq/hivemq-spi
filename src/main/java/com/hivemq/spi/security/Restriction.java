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

package com.hivemq.spi.security;

import java.io.Serializable;

/**
 * A restriction is essentially a permission with a numerical value.
 *
 * @author Dominik Obermaier
 * @since 1.4
 */
public class Restriction implements Serializable {

    private RestrictionType type;

    private Long value;

    public Restriction(final RestrictionType type, final Long value) {
        this.type = type;
        this.value = value;
    }

    /**
     * @return the numerical value for this restriction
     */
    public Long getValue() {
        return value;
    }

    /**
     * @return the type of this restriction
     */
    public RestrictionType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Restriction that = (Restriction) o;

        if (type != that.type) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Restriction{" +
                "type=" + type +
                ", value=" + value +
                '}';
    }
}
