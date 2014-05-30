package com.dcsquare.hivemq.spi.bridge;

/**
 * The state of a bridge.
 * <p/>
 * The state machine is as follows:
 * <p/>
 * <code>STOPPED -> STARTING -> RUNNING -> STOPPING -> STOPPED</code>
 * <p/>
 * If something bad happens, the bridge can get in state <code>FAILURE</code>.
 *
 * @author Dominik Obermaier
 * @since 2.0
 */
public enum State {

    /**
     * The bridge is stopped at the moment
     */
    STOPPED,
    /**
     * The bridge is starting and will be available soon
     */
    STARTING,
    /**
     * The bridge is up and running
     */
    RUNNING,
    /**
     * The bridge is in failure state. This is typically a fatal problem
     * and restarting could possibly be impossible.
     */
    FAILURE,
    /**
     * The bridge is stopping at the moment and will be shutdown soon
     */
    STOPPING
}