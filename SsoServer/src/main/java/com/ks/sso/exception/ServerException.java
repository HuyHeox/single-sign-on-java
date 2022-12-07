package com.ks.sso.exception;

import java.io.Serializable;

public class ServerException extends Exception implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int exceptionType;

    public ServerException() {
        super();
    }

    public ServerException(String message) {
        super(message);
    }

    public ServerException(int exceptionType, String message) {
        this(message);
        this.exceptionType = exceptionType;
    }

    public ServerException(int exceptionType) {
        this.exceptionType = exceptionType;
    }

    public int getExceptionType() {
        return this.exceptionType;
    }
}
