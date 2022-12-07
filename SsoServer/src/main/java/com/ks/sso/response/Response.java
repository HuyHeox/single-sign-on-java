package com.ks.sso.response;

public class Response {
    protected Status status;
    protected String message;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public enum Status {
        OK,
        ERROR
    }
}
