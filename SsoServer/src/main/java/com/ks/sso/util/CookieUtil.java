package com.ks.sso.util;

import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class CookieUtil {
    public void SaveInCookie(HttpServletResponse response, String name, String value, int duration) {
        Cookie ck = new Cookie(name, value);
        ck.setMaxAge(duration);
        ck.setPath("/");
        response.addCookie(ck);
    }

    public String GetCookieWithName(String name, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase(name)) {
                    return cookie.getValue();
                }
            }
        return null;
    }


    public void DeleteCookieWithName(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equalsIgnoreCase(name)) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
    }


}
