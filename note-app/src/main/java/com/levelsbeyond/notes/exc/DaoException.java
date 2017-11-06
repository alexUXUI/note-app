package com.levelsbeyond.notes.exc;

public class DaoException extends Exception {
    private final Object originalException;

    public DaoException(Exception originalException, String msg) {
        super(msg);
        this.originalException = originalException;
    }
}
