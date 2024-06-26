package com.ks.sso.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse extends Response {
    public String getErrKey() {
        return errKey;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    private final String errKey;
    private final Map<String, Object> params;

    protected ErrorResponse(String errKey, String message, Map<String, Object> params) {
        this.status = Status.ERROR;
        this.errKey = errKey;
        this.message = message;
        this.params = params;
    }

    public static ErrorResponse of(String errKey) {
        return new ErrorResponse(errKey, null, null);
    }
    
    public static ErrorResponse of(String errKey, String details) {
        return new ErrorResponse(errKey, details, null);
    }

    public static ErrorResponse of(String errKey, String details, Map<String, Object> params) {
        return new ErrorResponse(errKey, details, params);
    }
}