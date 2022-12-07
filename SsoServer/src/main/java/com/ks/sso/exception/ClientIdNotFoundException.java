package com.ks.sso.exception;

import com.ks.sso.exception.common.BusinessException;
import com.ks.sso.exception.common.ServiceError;

public class ClientIdNotFoundException extends BusinessException {
    public ClientIdNotFoundException(String clientId) {
        super(ServiceError.CLIENTID_NOT_FOUND_EXCEPTION, null, buildSingleParamMaps("clientId", clientId));
    }
}