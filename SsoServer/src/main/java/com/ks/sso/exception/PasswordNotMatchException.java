package com.ks.sso.exception;

import com.ks.sso.exception.common.BusinessException;
import com.ks.sso.exception.common.ServiceError;

public class PasswordNotMatchException extends BusinessException {
    public PasswordNotMatchException() {
        super(ServiceError.PASSWORD_NOT_MATCH, null, null);
    }
}
