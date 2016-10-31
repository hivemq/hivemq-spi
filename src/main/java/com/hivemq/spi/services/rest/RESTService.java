package com.hivemq.spi.services.rest;

import com.hivemq.spi.annotations.NotNull;
import com.hivemq.spi.annotations.ReadOnly;
import com.hivemq.spi.annotations.ThreadSafe;
import com.hivemq.spi.services.rest.listener.HttpListener;
import com.hivemq.spi.services.rest.listener.Listener;
import com.hivemq.spi.services.rest.servlet.ServletFilter;

import javax.servlet.Filter;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.core.Application;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import java.util.Collection;

/**
 * This HiveMQ REST service allows to expose Java Servlets (3.0), Filters and JAX-RS (2.0) resources.
 * <p/>
 * The REST service has a listener concept so different resources can be bound to different
 * transports (TCP / TLS) and different ports on different interfaces.
 * <p/>
 * The REST Service supports first-class dependency injection and even allows to use constructor injection.
 * <p/>
 * It's possible to annotate JAX-RS resources with {@link javax.inject.Singleton} to bind a resource to a singleton
 * context instead of a per-request context.
 * <p>
 * The REST Service implementation is guaranteed to be thread safe.
 *
 * @author Dominik Obermaier
 * @since 3.0
 */
@ThreadSafe
public interface RESTService {

    /**
     * Adds a new listener to the REST Service programmatically.
     * <p/>
     * Note: Typically the listeners are added via the configuration file. If adding
     * the configuration via the config file does not suit your needs, the programmatic API
     * offers a convenient way to add an arbitrary number of listeners at runtime.
     * <p/>
     * Note: If you add listeners at runtime, existing servlets or JAX-RS resource on other listeners won't be added
     * to this new listener automatically.
     *
     * @param listener the {@link Listener} implementation
     * @throws NullPointerException     if the passed listener is <code>null</code>
     * @throws IllegalArgumentException if the passed listener is not a valid listener type (like {@link HttpListener}
     */
    void addListener(@NotNull Listener listener);

    /**
     * Returns an immutable view of all listeners.
     * <p/>
     * In order to get more information you need to downcast to a specific listener type. You can do this e.g. by
     * code such as
     * <pre>
     * if (listener instanceof HttpListener) {
     *      HttpListener httpListener = (HttpListener) listener;
     * }
     * </pre>
     *
     * @return an immutable view of all listeners
     */
    @ReadOnly
    Collection<Listener> getListeners();

    /**
     * Adds a servlet instance to a specific path and adds the servlet to all available listeners.
     *
     * @param servlet the servlet to add
     * @param path    the path to bind the servlet to
     * @throws NullPointerException if the servlet or the path is null
     */
    void addServlet(@NotNull HttpServlet servlet, @NotNull String path);

    /**
     * Adds a servlet instance to a specific path and adds the servlet to all specified listeners. If the
     * collection of listeners is empty, the servlet will be added to all available listeners
     *
     * @param servlet             the servlet to add
     * @param path                the path to bind the servlet to
     * @param listenerIdentifiers a collection with identifiers of listeners
     * @throws NullPointerException if the servlet, the path or the listener identifiers collection is null
     */
    void addServlet(@NotNull HttpServlet servlet, @NotNull String path, @NotNull Collection<String> listenerIdentifiers);


    /**
     * Adds a servlet to a specific path and adds the servlet to all available listeners.
     * <p/>
     * The given servlet class will be instantiated by HiveMQ and the servlet can use dependency injection.
     * <p/>
     * The servlet will be instantiated once and not per request, so it's essentially a singleton.
     *
     * @param servlet the servlet to add
     * @param path    the path to bind the servlet to
     * @return The instantiated servlet
     * @throws NullPointerException if the servlet or the path is null
     */
    <T extends HttpServlet> T addServlet(@NotNull Class<T> servlet, @NotNull String path);

    /**
     * Adds a servlet to a specific path and adds the servlet to all specified listeners. If the
     * collection of listeners is empty, the servlet will be added to all available listeners
     * <p/>
     * The given servlet class will be instantiated by HiveMQ and the servlet can use dependency injection.
     * <p/>
     * The servlet will be instantiated once and not per request, so it's essentially a singleton.
     *
     * @param servlet             the servlet to add
     * @param path                the path to bind the servlet to
     * @param listenerIdentifiers a collection with identifiers of listeners
     * @return The instantiated servlet
     * @throws NullPointerException if the servlet or the path is null
     */
    <T extends HttpServlet> T addServlet(@NotNull Class<T> servlet, @NotNull String path, @NotNull Collection<String> listenerIdentifiers);


    /**
     * Adds a specific {@link ServletFilter} with a given path to the RESTService on all available listeners.
     *
     * @param filter the {@link ServletFilter}
     * @param path   the Path
     * @param <T>    the {@link Filter} which is contained in the {@link ServletFilter}
     * @return the concrete {@link Filter} instance
     * @throws NullPointerException if the {@link ServletFilter} or path is null
     */
    <T extends Filter> T addFilter(@NotNull ServletFilter<T> filter, @NotNull String path);

    /**
     * Adds a specific {@link ServletFilter} with a given path to the RESTService on specific listeners.
     *
     * @param filter    the {@link ServletFilter}
     * @param path      the Path
     * @param listeners a collection of listeners
     * @param <T>       the {@link Filter} which is contained in the {@link ServletFilter}
     * @return the concrete {@link Filter} instance
     * @throws NullPointerException if the {@link ServletFilter}, path or listener collection is null
     */
    <T extends Filter> T addFilter(@NotNull ServletFilter<T> filter, @NotNull String path, @NotNull Collection<String> listeners);


    /**
     * Adds a servlet instance to a specific path and adds the servlet to all available listeners.
     * <p/>
     * Additionally a servlet filter is added directly to the servlet path mapping. This is a convenient
     * method if you need a specific filter only for one servlet
     *
     * @param servlet the servlet to add
     * @param path    the path to bind the servlet to
     * @param filters an arbitrary amount of filters for this specific servlet
     * @throws NullPointerException if the servlet, the path or the filter array is null
     */
    @SuppressWarnings("unchecked")
    void addServletWithFilters(@NotNull HttpServlet servlet, @NotNull String path, @NotNull ServletFilter<? extends Filter>... filters);

    /**
     * Adds a servlet instance to a specific path and adds the servlet to all specific listeners.
     * <p/>
     * Additionally a servlet filter is added directly to the servlet path mapping. This is a convenient
     * method if you need a specific filter only for one servlet and only on specific listeners
     *
     * @param servlet             the servlet to add
     * @param path                the path to bind the servlet to
     * @param listenerIdentifiers a collection of listener identifierd
     * @param filters             an arbitrary amount of filters for this specific servlet
     * @throws NullPointerException if the servlet, the path, the listener collection or the filter array is null
     */
    @SuppressWarnings("unchecked")
    void addServletWithFilters(@NotNull HttpServlet servlet, @NotNull String path, @NotNull Collection<String> listenerIdentifiers, @NotNull ServletFilter<? extends Filter>... filters);

    /**
     * Adds a servlet to a specific path and adds the servlet to all available listeners. If the
     * collection of listeners is empty, the servlet will be added to all available listeners
     * <p/>
     * The given servlet class will be instantiated by HiveMQ and the servlet can use dependency injection.
     * <p/>
     * Additionally a servlet filter is added directly to the servlet path mapping. This is a convenient
     * method if you need a specific filter only for one servlet
     *
     * @param servlet the servlet to add
     * @param path    the path to bind the servlet to
     * @param filters an arbitrary amount of filters for this specific servlet
     * @return the instantiated servlet
     * @throws NullPointerException if the servlet, the path or the filter array is null
     */
    @SuppressWarnings("unchecked")
    <T extends HttpServlet> T addServletWithFilters(@NotNull Class<T> servlet, @NotNull String path, @NotNull ServletFilter<? extends Filter>... filters);

    /**
     * Adds a servlet to a specific path and adds the servlet to all specified listeners. If the
     * collection of listeners is empty, the servlet will be added to all available listeners
     * <p/>
     * The given servlet class will be instantiated by HiveMQ and the servlet can use dependency injection.
     * <p/>
     * Additionally a servlet filter is added directly to the servlet path mapping. This is a convenient
     * method if you need a specific filter only for one servlet
     *
     * @param servlet             the servlet to add
     * @param path                the path to bind the servlet to
     * @param listenerIdentifiers the collection of listeners
     * @param filters             an arbitrary amount of filters for this specific servlet
     * @return the instantiated servlet
     * @throws NullPointerException if the servlet, the path or the filter array is null
     */
    @SuppressWarnings("unchecked")
    <T extends HttpServlet> T addServletWithFilters(@NotNull Class<T> servlet, @NotNull String path, @NotNull Collection<String> listenerIdentifiers, @NotNull ServletFilter<? extends Filter>... filters);


    /**
     * Adds a {@link Application} to the RESTService.
     * <p/>
     * Please be aware that when using this method, only all properties, classes and singletons are
     * added to the RESTService, the {@link Application} and all annotations on the {@link Application}
     * are ignored. So essentially this is a convenient method which allows you to add a lot of resources at once
     * <p/>
     * Important: {@link javax.ws.rs.ApplicationPath} annotations are ignored.
     * <p/>
     * All resources defined in the Application will be added to all available listeners.
     *
     * @param application the {@link Application}
     * @throws NullPointerException if the passed {@link Application} is null
     */
    void addJaxRsApplication(@NotNull Application application);

    /**
     * Adds a {@link Application} to the RESTService.
     * <p/>
     * Please be aware that when using this method, only all properties, classes and singletons are
     * added to the RESTService, the {@link Application} and all annotations on the {@link Application}
     * are ignored. So essentially this is a convenient method which allows you to add a lot of resources at once
     * <p/>
     * Important: {@link javax.ws.rs.ApplicationPath} annotations are ignored.
     * <p/>
     * All resources defined in the Application will be added to all specified listeners.
     *
     * @param application         the {@link Application}
     * @param listenerIdentifiers a collection of listeners
     * @throws NullPointerException if the passed {@link Application} or the collection of listeners is null
     */
    void addJaxRsApplication(@NotNull Application application, @NotNull Collection<String> listenerIdentifiers);

    /**
     * Adds all given JAX-RS resources as singleton to all available listeners.
     * Since you have to instantiate the singleton objects on your own,
     * these singletons can't use dependency injection by HiveMQ and you have to pass your dependencies on your own
     * to the objects.
     * <p/>
     * If you want to have singletons which use dependency injection, consider using another method of the RESTService
     * which accepts classes instead of objects. You can annotate these methods with {@link javax.inject.Singleton}
     *
     * @param singletons an arbitrary number of singleton resources
     * @throws NullPointerException if the singleton array is null
     */
    void addJaxRsSingletons(@NotNull Object... singletons);

    /**
     * Adds all given JAX-RS resources as singleton to all specified listeners.
     * Since you have to instantiate the singleton objects on your own,
     * these singletons can't use dependency injection by HiveMQ and you have to pass your dependencies on your own
     * to the objects.
     * <p/>
     * If you want to have singletons which use dependency injection, consider using another method of the RESTService
     * which accepts classes instead of objects. You can annotate these methods with {@link javax.inject.Singleton}
     *
     * @param singletons          an collection of singleton resources
     * @param listenerIdentifiers a collection of listeners
     * @throws NullPointerException if the singleton array is null
     */
    void addJaxRsSingletons(@NotNull Collection<Object> singletons, @NotNull Collection<String> listenerIdentifiers);

    /**
     * Adds an arbitrary number of JAX-RS resources to all available listeners.
     * These resources can use dependency injection and HiveMQ will instantiate the resources for you at runtime
     *
     * @param resources a arbitrary number of JAX-RS resource classes
     * @throws NullPointerException if the resources array is null
     */
    void addJaxRsResources(@NotNull Class<?>... resources);

    /**
     * Adds an arbitrary number of JAX-RS resources to all specified listeners.
     * These resources can use dependency injection and HiveMQ will instantiate the resources for you at runtime
     *
     * @param resources           a collection of JAX-RS resource classes
     * @param listenerIdentifiers a collection of listeners
     * @throws NullPointerException if the resources array or the listener collection is null
     */
    void addJaxRsResources(@NotNull Collection<Class<?>> resources, @NotNull Collection<String> listenerIdentifiers);

    /**
     * Adds an {@link ExceptionMapper} to all available listeners.
     * <p>
     * Since you have to instantiate the {@link ExceptionMapper} objects on your own,
     * it can't use dependency injection by HiveMQ and you have to pass your dependencies on your own
     * to the objects.
     * <p/>
     * If you want to have {@link ExceptionMapper}s which use dependency injection,
     * consider using another method of the RESTService which accepts classes instead of objects.
     *
     * @param exceptionMapper the JAX-RS {@link ExceptionMapper} instance that should be added to all listeners
     * @throws NullPointerException if the ExceptionMapper is null
     * @since 3.2
     */
    void addExceptionMapper(@NotNull final ExceptionMapper<? extends Throwable> exceptionMapper);

    /**
     * Adds an {@link ExceptionMapper} to all specified listeners.
     * <p>
     * Since you have to instantiate the {@link ExceptionMapper} objects on your own,
     * it can't use dependency injection by HiveMQ and you have to pass your dependencies on your own
     * to the objects.
     * <p/>
     * If you want to have {@link ExceptionMapper}s which use dependency injection,
     * consider using another method of the RESTService which accepts classes instead of objects.
     *
     * @param exceptionMapper    the JAX-RS {@link ExceptionMapper} instance that should be added to all specified listeners
     * @param listenerIdentifier a collection of listeners
     * @throws NullPointerException if the ExceptionMapper or the listener collection is null
     * @since 3.2
     */
    void addExceptionMapper(@NotNull final ExceptionMapper<? extends Throwable> exceptionMapper, @NotNull final Collection<String> listenerIdentifier);

    /**
     * Adds an {@link ExceptionMapper} to all available listeners.
     * <p>
     * The ExceptionMapper can use dependency injection and HiveMQ will instantiate it for you at runtime
     *
     * @param exceptionMapper the JAX-RS {@link ExceptionMapper} class that should be added to all listeners
     * @throws NullPointerException if the ExceptionMapper is null
     * @since 3.2
     */
    void addExceptionMapper(@NotNull final Class<? extends ExceptionMapper<? extends Throwable>> exceptionMapper);

    /**
     * Adds an {@link ExceptionMapper} to all specified listeners.
     * <p>
     * The ExceptionMapper can use dependency injection and HiveMQ will instantiate it for you at runtime
     *
     * @param exceptionMapper    the JAX-RS {@link ExceptionMapper} class that should be added to all specified listeners
     * @param listenerIdentifier a collection of listeners
     * @throws NullPointerException if the ExceptionMapper or the listener collection is null
     * @since 3.2
     */
    void addExceptionMapper(@NotNull final Class<? extends ExceptionMapper<? extends Throwable>> exceptionMapper, @NotNull final Collection<String> listenerIdentifier);

    /**
     * Adds an {@link ContextResolver} to all available listeners.
     * <p>
     * The ContextResolver can use dependency injection and HiveMQ will instantiate it for you at runtime
     *
     * @param contextResolver the JAX-RS {@link ContextResolver} class that should be added to all listeners
     * @throws NullPointerException if the ContextResolver is null
     * @since 3.2
     */
    void addContextResolver(@NotNull final Class<? extends ContextResolver> contextResolver);

    /**
     * Adds an {@link ContextResolver} to all specified listeners.
     * <p>
     * The ContextResolver can use dependency injection and HiveMQ will instantiate it for you at runtime
     *
     * @param contextResolver    the JAX-RS {@link ContextResolver} class that should be added to all specified listeners
     * @param listenerIdentifier the collection of listeners
     * @throws NullPointerException if the ContextResolver or the listener collection is null
     * @since 3.2
     */
    void addContextResolver(@NotNull final Class<? extends ContextResolver> contextResolver, @NotNull final Collection<String> listenerIdentifier);

    /**
     * Adds a {@link MessageBodyWriter} to all listeners.
     * <p>
     * The MessageBodyWriter can use dependency injection and HiveMQ will instantiate it for you at runtime
     *
     * @param messageBodyWriter the JAX-RS {@link MessageBodyWriter} class that should be added to all listeners
     * @throws NullPointerException if the MessageBodyWriter is null
     * @since 3.2
     */
    void addMessageBodyWriter(@NotNull final Class<? extends MessageBodyWriter> messageBodyWriter);

    /**
     * Adds a {@link MessageBodyWriter} to all specified listeners.
     * <p>
     * The MessageBodyWriter can use dependency injection and HiveMQ will instantiate it for you at runtime
     *
     * @param messageBodyWriter  the JAX-RS {@link MessageBodyWriter} class that should be added to all specified listeners
     * @param listenerIdentifier the collection of listeners
     * @throws NullPointerException if the MessageBodyWriter or the listener collection is null
     * @since 3.2
     */
    void addMessageBodyWriter(@NotNull final Class<? extends MessageBodyWriter> messageBodyWriter, @NotNull final Collection<String> listenerIdentifier);

    /**
     * Adds a {@link MessageBodyReader} to all listeners.
     * <p>
     * The MessageBodyReader can use dependency injection and HiveMQ will instantiate it for you at runtime
     *
     * @param messageBodyReader the JAX-RS {@link MessageBodyReader} class that should be added to all listeners
     * @throws NullPointerException if the MessageBodyReader is null
     * @since 3.2
     */
    void addMessageBodyReader(@NotNull final Class<? extends MessageBodyReader> messageBodyReader);

    /**
     * Adds a {@link MessageBodyReader} to all specified listeners.
     * <p>
     * The MessageBodyReader can use dependency injection and HiveMQ will instantiate it for you at runtime
     *
     * @param messageBodyReader  the JAX-RS {@link MessageBodyReader} class that should be added to all specified listeners
     * @param listenerIdentifier the collection of listeners
     * @throws NullPointerException if the MessageBodyReader or the listener collection is null
     * @since 3.2
     */
    void addMessageBodyReader(@NotNull final Class<? extends MessageBodyReader> messageBodyReader, @NotNull final Collection<String> listenerIdentifier);
}
