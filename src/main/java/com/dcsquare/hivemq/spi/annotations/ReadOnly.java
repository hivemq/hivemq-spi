package com.dcsquare.hivemq.spi.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * This is an informal annotation which indicates that the method returns
 * a read-only type.
 * <p/>
 * If you encounter this on a method which returns a {@link java.util.Collection},
 * this collection is expected to be immutable and must not be modified.
 *
 * @author Dominik Obermaier
 * @since 2.0
 */
@Documented
@Retention(RUNTIME)
@Target({METHOD})
public @interface ReadOnly {
}
