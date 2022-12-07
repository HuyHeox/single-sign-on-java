package com.ks.sso.controller;

import com.ks.sso.DTO.AccessToken;
import com.ks.sso.DTO.NotificationRequestDto;
import com.ks.sso.DTO.SsoResponse;
import com.ks.sso.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {
    @Autowired
    JwtService jwtService;


    String token;
    String code;

    @GetMapping("/sso/login")
    public void login(HttpServletResponse response, HttpSession session) {
        this.code = session.getId();
        Cookie ckToken = new Cookie("code", session.getId());
        ckToken.setMaxAge(3600);
        response.addCookie(ckToken);
        String code=jwtService.CreateCodeToken(session.getId());
        System.out.println(session.getId());
        String url ="https://ssoserver-dot-koolsoft-sanbox.appspot.com/ssoserver/sso?redirectUrl=https://ssoclient-dot-koolsoft-sanbox.appspot.com&clientId=client1&encodedSessionId="+code;
//         String url ="http://localhost:8080/ssoserver/sso?redirectUrl=http://localhost:8081&clientId=client1&encodedSessionId="+code;
        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        response.setHeader("Location", url);
    }

    @GetMapping("/callback")
    public SsoResponse getObject(@RequestParam("token") String token, HttpServletRequest request,HttpServletResponse response, HttpSession session){
        System.out.println(token);
        System.out.println("_--------------------------------------");
        this.token=token;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        System.out.println(session.getId());
        String urlTemplate = UriComponentsBuilder.fromHttpUrl("https://ssoserver-dot-koolsoft-sanbox.appspot.com/auth/userinfo")
//        String urlTemplate = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/auth/userinfo")
                .queryParam("token", token)
                .queryParam("clientId", "{clientId}")
                .queryParam("sessionId", this.code)
                .toUriString();

        Map<String, String> params = new HashMap<>();
        params.put("token",  this.token);
        params.put("clientId","client1");
        params.put("sessionId",this.code);


//        HttpHeaders header = new HttpHeaders();
//        header.add("Cookie", this.code );
        HttpEntity<SsoResponse> responseApi = restTemplate.exchange(
                urlTemplate,
                HttpMethod.GET,
                entity,
                SsoResponse.class,
                params
        );



        SsoResponse result = responseApi.getBody();
//        Cookie ckToken = new Cookie("accessToken", result.getAccessToken());
//        ckToken.setMaxAge(3600);
//        response.addCookie(ckToken);
//        System.out.println("----------------------------");
        return result;
    }
    
    @PostMapping("/topic")
    public String sendPnsToTopic( @ModelAttribute(value = "notificationRequestDto") NotificationRequestDto notificationRequestDto) throws  IOException {
        return jwtService.sendPnsToTopic(notificationRequestDto);
    }







}
