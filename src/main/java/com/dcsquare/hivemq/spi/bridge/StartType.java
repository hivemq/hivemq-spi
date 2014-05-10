package com.dcsquare.hivemq.spi.bridge;

/**
 * The start type of a bridge.
 *
 * @author Dominik Obermaier
 * @since 2.0
 */
public enum StartType {

    /**
     * The bridge connection will be started automatically when the broker starts
     * and it will try with best effort to reconnect if the connection drops or if
     * the connection failed.
     * <p/>
     * This is the default connection type
     */
    AUTOMATIC,
    /**
     * This bridge will not start automatically on broker startup. Lazy bridges queue messages
     * until a threshold is reached and then they connect to the remote broker. After a idle
     * timeout it will automatically disconnect again if no messages are delivered in the meantime
     * <p/>
     * This type is very useful if you only want to connect a bridge if needed for if you want to do
     * "batching". If you want to use the lazy mode for batching, consider setting the idle timeout to
     * a very low value.
     * <p/>
     * Please make sure that the idle timeout is shorter than the keepAlive value, otherwise
     * a bridge connection is never considered idle
     */
    LAZY,
    /**
     * This bridge will not start and stop automatically. You must use a plugin which utilizes
     * the {@link com.dcsquare.hivemq.spi.services.BridgeManagerService} to start and stop
     * this type of bridge.
     * <p/>
     * This bridge won't reconnect automatically if it disconnected unexpectedly, so make sure
     * you implemented some kind of heartbeat when using this bridge type.
     */
    MANUAL,
    /**
     * This bridge will start automatically on broker startup. If the connection drops,
     * this bridge type won't try to reconnect.
     * <p/>
     * It is possible to restart this type of bridge manually with the
     * {@link com.dcsquare.hivemq.spi.services.BridgeManagerService} in HiveMQ plugins
     */
    ONCE
}