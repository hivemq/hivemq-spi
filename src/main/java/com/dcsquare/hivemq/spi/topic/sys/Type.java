package com.dcsquare.hivemq.spi.topic.sys;

/**
 * An enumeration of all available types for HiveMQ $SYS Topics
 *
 * @author Dominik Obermaier
 * @since 2.0
 */
public enum Type {
    /**
     * A static $SYS topic is a $SYS topic which will be published on the initial $SYS subscription of a client.
     * Typically static $SYS topic values don't change over time.
     */
    STATIC,

    /**
     * A standard $SYS topic which will be published every time the $SYS topic publishing job
     * is running.
     */
    STANDARD
}
