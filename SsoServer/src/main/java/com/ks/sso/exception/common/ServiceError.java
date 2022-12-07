package com.ks.sso.exception.common;



import lombok.Getter;

@Getter
public enum ServiceError {
    PASSWORD_NOT_MATCH(401, "err.authorize.password-not-match"),
    USER_NOT_FOUND_EXCEPTION(401, "err.api.user-not-found"),
    USER_ALREADY_EXIST(400, "err.api.user-already-exist"),
    CLIENTID_AND_REDIRECTURL_NOT_MATCH(401,"err.clientid-and-redirecturl-not-match"),
	CLIENTID_NOT_FOUND_EXCEPTION(401,"err.clientid-not-found");
   ServiceError(int errCode, String messageKey) {
        this.errCode = errCode;
        this.messageKey = messageKey;
    }

    private final int errCode;
    private final String messageKey;
    
    public int getErrCode() {
    	return this.errCode;
    }
    public String getMessageKey() {
    	return this.messageKey;
    }
}
