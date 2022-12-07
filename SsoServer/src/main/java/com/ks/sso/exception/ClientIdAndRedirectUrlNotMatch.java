package com.ks.sso.exception;

import com.ks.sso.exception.common.BusinessException;
import com.ks.sso.exception.common.ServiceError;

public class ClientIdAndRedirectUrlNotMatch extends BusinessException {
    public ClientIdAndRedirectUrlNotMatch() {
        super(ServiceError.CLIENTID_AND_REDIRECTURL_NOT_MATCH, null, null);
    }
}