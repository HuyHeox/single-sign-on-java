package com.ks.sso.config;

public class CacheTokenInfo {
    private String redirectUrl;
    private String sessionId;

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public CacheTokenInfo(String redirectUrl, String sessionId) {
        super();
        this.redirectUrl = redirectUrl;
        this.sessionId = sessionId;
    }


}
