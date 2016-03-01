package com.hivemq.spi.metrics.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Methods annotated with this annotation are added to the HiveMQ {@link com.codahale.metrics.MetricRegistry}
 * automatically as Counters. The invocation of the annotated methods are counted
 *
 * @author Christoph Schäbel
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface Counted {

    /**
     * @return The name of this metric. When left empty, the canonical name of the class and method will be used.
     */
    String name() default "";


    /**
     * @return If {@code false} the counter is decremented when the annotated
     * method returns, counting current invocations of the annotated method.
     * If {@code true} (default) the counter increases monotonically, counting total
     * invocations of the annotated method.
     */
    boolean monotonic() default true;
}
