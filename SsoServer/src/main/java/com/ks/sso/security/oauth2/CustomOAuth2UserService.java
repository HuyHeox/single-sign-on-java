package com.ks.sso.security.oauth2;

import java.util.logging.Logger;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ks.sso.exception.OAuth2AuthenticationProcessingException;
import com.ks.sso.exception.ServerException;
import com.ks.sso.security.UserPrincipal;
import com.ks.sso.security.oauth2.user.OAuth2UserInfo;
import com.ks.sso.security.oauth2.user.OAuth2UserInfoFactory;
import com.ks.sso.service.SsoService;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    public static final Logger log = Logger.getLogger("hungnt");

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) throws ServerException {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }
        String personalEmail = oAuth2UserInfo.getEmail();
        String email = SsoService.getEmailByPersonalEmail(personalEmail);
        if (email == null) {
            throw new ServerException("you are not in the system");
        }
        return UserPrincipal.create(email, oAuth2User.getAttributes());
    }
}

