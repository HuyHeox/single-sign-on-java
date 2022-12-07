package com.ks.sso.model;

import lombok.Data;

@Data
public class Client {
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    private String clientId;
    private String clientSecret;
    private String redirectUrl;

    public Client(String clientid, String clientsecret, String redirectUrl) {
        this.clientId = clientid;
        this.clientSecret = clientsecret;
        this.redirectUrl = redirectUrl;
    }
}
