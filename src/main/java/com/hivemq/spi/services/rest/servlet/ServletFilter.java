package com.hivemq.spi.services.rest.servlet;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.hivemq.spi.annotations.Immutable;
import com.hivemq.spi.annotations.NotNull;
import com.hivemq.spi.annotations.Nullable;
import com.hivemq.spi.annotations.ReadOnly;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A wrapper for encapsulating a Servlet filter with their
 * corresponding {@link DispatcherType}s.
 * <p/>
 * This class can either be used to wrap a concrete instance of a filter
 * or a class which is instantiated by HiveMQ in order to provide
 * dependency injection to that given filter.
 *
 * @author Dominik Obermaier
 */
@Immutable
public class ServletFilter<T extends Filter> {

    private final Optional<T> filter;

    private final List<DispatcherType> dispatcherTypes;

    private final Optional<Class<T>> filterClass;

    /**
     * Creates a new wrapper for a concrete Servlet Filter object and its corresponding
     * dispatcher types.
     * <p/>
     * If no dispatcher type is given, {@link DispatcherType#REQUEST} is used as default dispatcher type
     *
     * @param filter          a concrete filter instance
     * @param dispatcherTypes all supported dispatcher types by that filter
     * @throws NullPointerException if the filter or the dispatcher types array is null
     */
    public ServletFilter(@NotNull final T filter,
                         @NotNull final DispatcherType... dispatcherTypes) {

        this(checkNotNull(filter, "Filter must not be null"), null, checkNotNull(dispatcherTypes, "Dispatcher Types must not be null"));
    }

    /**
     * Creates a new wrapper for a Servlet Filter class and its corresponding
     * dispatcher types. The Filter class will be instantiated and dependency injection can be used
     * <p/>
     * If no dispatcher type is given, {@link DispatcherType#REQUEST} is used as default dispatcher type
     *
     * @param filterClass     a filter class which should be instantiated with dependency injection
     * @param dispatcherTypes all supported dispatcher types by that filter
     * @throws NullPointerException if the filter or the dispatcher types array is null
     */
    public ServletFilter(@NotNull final Class<T> filterClass,
                         @NotNull final DispatcherType... dispatcherTypes) {

        this(null, checkNotNull(filterClass, "Filter Class must not be null"), checkNotNull(dispatcherTypes, "Dispatcher Types must not be null"));
    }


    private ServletFilter(@Nullable final T filter,
                          @Nullable final Class<T> filterClass,
                          @NotNull final DispatcherType... dispatcherTypes) {
        if (dispatcherTypes.length > 0) {

            this.dispatcherTypes = ImmutableList.copyOf(dispatcherTypes);
        } else {
            //Per Servlet Spec, Request is the default dispatcher type
            this.dispatcherTypes = ImmutableList.of(DispatcherType.REQUEST);
        }
        this.filter = Optional.fromNullable(filter);
        this.filterClass = Optional.fromNullable(filterClass);

    }

    public Optional<T> getFilter() {
        return filter;
    }

    public Optional<Class<T>> getFilterClass() {
        return filterClass;
    }

    /**
     * Returns a immutable list of all supported Dispatcher types by that filter
     *
     * @return a immutable List of all supported Dispatcher types by that filter
     */
    @ReadOnly
    public List<DispatcherType> getDispatcherTypes() {
        return dispatcherTypes;
    }
}
