package com.jason07289.exception;

public class CategoryException extends RuntimeException {
    public CategoryException(String msg, Throwable t) {
        super(msg, t);
    }

    public CategoryException(String msg) {
        super(msg);
    }

    public CategoryException() {
        super();
    }
}