package com.hivemq.spi.util;

import com.hivemq.spi.services.configuration.entity.TcpListener;
import com.hivemq.spi.services.configuration.entity.TlsTcpListener;
import com.hivemq.spi.services.configuration.entity.TlsWebsocketListener;
import com.hivemq.spi.services.configuration.entity.WebsocketListener;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Dominik Obermaier
 */
public class ListenersTest {


    @Mock
    TcpListener tcpListener;

    @Mock
    TlsTcpListener tlsTcpListener;

    @Mock
    TlsWebsocketListener tlsWebsocketListener;

    @Mock
    WebsocketListener websocketListener;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_is_websocket_listener() throws Exception {

        assertTrue(Listeners.isWebsocket(tlsWebsocketListener));
        assertTrue(Listeners.isWebsocket(websocketListener));
        assertFalse(Listeners.isWebsocket(tcpListener));
        assertFalse(Listeners.isWebsocket(tlsTcpListener));
    }

    @Test
    public void test_is_secure_listener() throws Exception {

        assertTrue(Listeners.isSecure(tlsWebsocketListener));
        assertFalse(Listeners.isSecure(websocketListener));
        assertFalse(Listeners.isSecure(tcpListener));
        assertTrue(Listeners.isSecure(tlsTcpListener));

    }
}