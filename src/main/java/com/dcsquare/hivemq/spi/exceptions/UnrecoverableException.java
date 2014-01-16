package com.dcsquare.hivemq.spi.exceptions;

/**
 * @author Dominik Obermaier
 */
public class UnrecoverableException extends RuntimeException {

    private final boolean showException;


    public UnrecoverableException() {
        this(true);
    }

    public UnrecoverableException(boolean showException) {

        this.showException = showException;
    }

    public boolean isShowException() {
        return showException;
    }
}
