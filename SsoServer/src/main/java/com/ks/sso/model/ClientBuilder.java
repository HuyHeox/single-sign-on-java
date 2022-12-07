package com.ks.sso.model;

public class ClientBuilder {
    private String clientId;
    private String clientSecret;

    private String redirectUrl;

    public ClientBuilder setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public ClientBuilder setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
        return this;
    }

    public ClientBuilder setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
        return this;
    }

    public Client build() {
        return new Client(clientId, clientSecret, redirectUrl);
    }


}
