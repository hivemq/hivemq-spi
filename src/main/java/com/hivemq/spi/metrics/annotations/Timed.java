package com.hivemq.spi.metrics.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Methods annotated with this annotation are added to the HiveMQ {@link com.codahale.metrics.MetricRegistry}
 * automatically as Timers. The invocation times of the methods annotated with this Annotation are captured automatically.
 *
 * @author Christoph Schäbel
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface Timed {

    /**
     * @return The name of this metric. When left emtpy the canonical name of the class and method will be used.
     */
    String name() default "";

}
