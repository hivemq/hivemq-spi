package com.dcsquare.hivemq.spi.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * This annotation indicates that an API is experimental an can be subject
 * to change in later versions. If you are relying on an experimental API,
 * it is possible that you have to modify your code after an upgrade.
 *
 * @author Dominik Obermaier
 * @since 2.0
 */
@Documented
@Retention(SOURCE)
@Target({METHOD, ANNOTATION_TYPE, TYPE, PACKAGE})
public @interface Experimental {
}
