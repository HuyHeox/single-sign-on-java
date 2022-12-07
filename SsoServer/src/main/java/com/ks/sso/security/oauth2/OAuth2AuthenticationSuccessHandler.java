package com.ks.sso.security.oauth2;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.ks.sso.config.Constant;
import com.ks.sso.service.TokenProvider;
import com.ks.sso.util.CookieUtil;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    public static final Logger log = Logger.getLogger("hungnt");

    @Autowired
    TokenProvider tokenProvider;

    @Autowired
    CookieUtil cookieUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
    	String email = "";
    	String[] array = authentication.getPrincipal().toString().split(",");
    	for (String string : array) {
			if(string.contains("preferred_username=")) {
				email = string.trim().replace("preferred_username=", "").toLowerCase();
			}
		}
//    	log.warning("authentication.getName() "+ authentication.getName() +" email "+ email);
        String loginToken = tokenProvider.createToken(!email.isEmpty()? email: authentication.getName());
        cookieUtil.SaveInCookie(response, Constant.loggedInToken,loginToken,3600*24*7);
        response.sendRedirect("/sso/login");
    }

}
