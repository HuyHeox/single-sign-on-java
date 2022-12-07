package com.ks.sso.security.oauth2.user;
import com.ks.sso.model.AuthProvider;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.azure.toString())) {
            return new Office365OAuth2UserInfo(attributes);
        } else {
            return null;
        }
    }
}
