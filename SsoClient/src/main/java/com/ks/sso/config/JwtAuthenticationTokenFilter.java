package com.ks.sso.config;

import com.ks.sso.DTO.DecodeTokenAccessClaim;
import com.ks.sso.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JwtAuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {
    private final static String TOKEN_HEADER = "authorization";

    @Autowired
    private JwtService jwtService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Cookie[] cookies = httpRequest.getCookies();
        String accessToken = null;
        if (cookies!=null)
        for (Cookie cookie : cookies) {
            if (cookie.getName().equalsIgnoreCase("accessToken")) {
                accessToken = cookie.getValue();
            }
        }
        DecodeTokenAccessClaim decodeTokenAccessClaim=null;
        if (accessToken!=null)  decodeTokenAccessClaim= jwtService.decodeToken(accessToken);
        if (jwtService.validateLoginToken(decodeTokenAccessClaim) ) {

            if (decodeTokenAccessClaim.getUsername() != null) {
                String username = decodeTokenAccessClaim.getUsername();
                String password = "123";
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    authorities.add(new SimpleGrantedAuthority(decodeTokenAccessClaim.getRole()));
                boolean enabled = true;
                boolean accountNonExpired = true;
                boolean credentialsNonExpired = true;
                boolean accountNonLocked = true;
                UserDetails userDetail = new org.springframework.security.core.userdetails.User(username , password, enabled, accountNonExpired,
                        credentialsNonExpired, accountNonLocked, authorities);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail,
                        null, userDetail.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }
}
