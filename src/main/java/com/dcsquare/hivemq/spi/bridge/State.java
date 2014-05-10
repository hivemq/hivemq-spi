package com.dcsquare.hivemq.spi.bridge;

/**
 * The start type of a bridge.
 *
 * @author Dominik Obermaier
 * @since 2.0
 */
public enum State {

    STOPPED, STARTING, RUNNING, FAILURE, STOPPING;
}