package com.ks.sso.exception.common;

import com.ks.sso.exception.common.ServiceError;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public abstract class ServiceException extends RuntimeException {

    private final ServiceError err;
    private final Map<String, Object> params;


    protected ServiceException(ServiceError err, Throwable ex, LinkedHashMap<String, Object> params) {
        super(err.getMessageKey(), ex);
        this.params = Objects.nonNull(params) ? params : Collections.emptyMap();
        this.err = err;
    }

    public ServiceError getErr() {
        return err;
    }

    public Map<String, Object> getParams() {
        return params;
    }
}
