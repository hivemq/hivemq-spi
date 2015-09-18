package com.hivemq.spi.services.rest.servlet;

import com.hivemq.spi.annotations.NotNull;
import org.junit.Test;

import javax.servlet.*;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * @author Dominik Obermaier
 */
public class ServletFilterTest {

    @Test
    public void test_create_servlet_filter_with_instance() throws Exception {
        final ServletFilter servletFilter = new ServletFilter(dummyFilter());

        assertEquals(true, servletFilter.getFilter().isPresent());
        assertEquals(false, servletFilter.getFilterClass().isPresent());
        assertEquals(1, servletFilter.getDispatcherTypes().size());
        assertEquals(DispatcherType.REQUEST, servletFilter.getDispatcherTypes().get(0));
    }

    @Test
    public void test_create_servlet_filter_with_class() throws Exception {
        final ServletFilter servletFilter = new ServletFilter(Filter.class);

        assertEquals(false, servletFilter.getFilter().isPresent());
        assertEquals(true, servletFilter.getFilterClass().isPresent());
        assertEquals(1, servletFilter.getDispatcherTypes().size());
        assertEquals(DispatcherType.REQUEST, servletFilter.getDispatcherTypes().get(0));
    }

    @Test
    public void test_create_servlet_filter_with_dispatcher_types() throws Exception {
        final ServletFilter servletFilter = new ServletFilter(dummyFilter(), DispatcherType.INCLUDE, DispatcherType.REQUEST);

        assertEquals(true, servletFilter.getFilter().isPresent());
        assertEquals(false, servletFilter.getFilterClass().isPresent());

        assertEquals(2, servletFilter.getDispatcherTypes().size());
        assertEquals(true, servletFilter.getDispatcherTypes().contains(DispatcherType.INCLUDE));
        assertEquals(true, servletFilter.getDispatcherTypes().contains(DispatcherType.REQUEST));
    }

    @NotNull
    private Filter dummyFilter() {
        return new Filter() {
            @Override
            public void init(final FilterConfig filterConfig) throws ServletException {

            }

            @Override
            public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {

            }

            @Override
            public void destroy() {

            }
        };
    }

}