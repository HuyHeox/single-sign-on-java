package com.ks.sso.exception;


import com.ks.sso.exception.common.BusinessException;
import com.ks.sso.exception.common.ServiceError;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(String userName) {
        super(ServiceError.USER_NOT_FOUND_EXCEPTION, null, buildSingleParamMaps("userName", userName));
    }
}
