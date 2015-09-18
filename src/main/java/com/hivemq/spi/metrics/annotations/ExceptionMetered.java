package com.hivemq.spi.metrics.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Methods annotated with this annotation are added to the HiveMQ {@link com.codahale.metrics.MetricRegistry}
 * automatically as Meters. The exceptions thrown by the annotated method are counted
 *
 * @author Christoph Schäbel
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface ExceptionMetered {

    /**
     * @return The name of this metric. When left emtpy the canonical name of the class and method will be used.
     */
    String name() default "";

    /**
     * @return The type of exceptions that the meter will catch and count.
     */
    Class<? extends Throwable> cause() default Exception.class;
}
