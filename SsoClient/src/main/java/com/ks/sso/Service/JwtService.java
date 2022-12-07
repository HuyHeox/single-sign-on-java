package com.ks.sso.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.ks.sso.DTO.DecodeTokenAccessClaim;
import com.ks.sso.DTO.NotificationRequestDto;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class JwtService {


    private long expirationTimeAccessToken = 86400000;
    public String CreateCodeToken(String code){

        try {
            Date expiredDate = new Date(new Date().getTime() + expirationTimeAccessToken);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withExpiresAt(expiredDate)
                    .withClaim("code", code)
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            return null;
        }
    }

    private String secret = "clientsecret";
    public DecodeTokenAccessClaim decodeToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build(); //Reusable verifier instance
            DecodedJWT decodedJWT = verifier.verify(token);
            Date expiredDate = decodedJWT.getExpiresAt();
            String username = decodedJWT.getClaim("username").asString();
            String role = decodedJWT.getClaim("role").asString();

            return new DecodeTokenAccessClaim(username,role, expiredDate);
        } catch (JWTVerificationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
            throw new RuntimeException();
        }
    }

    public Boolean validateLoginToken(DecodeTokenAccessClaim jwtTokenClaim){
        if (jwtTokenClaim==null) return false;
        String username = jwtTokenClaim.getUsername();

        if (username==null || username.isEmpty()){
            return false;
        }
        if (jwtTokenClaim.getExpiredDate().before(new Date())){
            return false;
        }
        return true;
    }

    public String sendPnsToTopic(NotificationRequestDto notificationRequestDto) throws IOException {
        Notification notification = Notification
                .builder()
                .setTitle(notificationRequestDto.getTitle())
                .setBody( notificationRequestDto.getBody())
                .build();
        Message message = Message.builder()
                .setTopic(notificationRequestDto.getTarget())
                .setNotification(notification)
                .putData("content", notificationRequestDto.getTitle())
                .putData("body", notificationRequestDto.getBody())
                .build();

        String response = null;
        try {
             response = FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
//            log.error("Fail to send firebase notification", e);
        }

        return response;
//        String authKey = "AAAAUiLnO6s:APA91bH5nwPQX-wjIU6W9a-E4NtmDR9qZx1y5pdPar1zEv224Yqvd88A689hsObaK6KuogegNHdp5EDLXIPo4eFdkIESjThNsZzH0pkZabHCItbTkEBYFwkf5DYdjp5agH3cZKIuRFQG";   // You FCM AUTH key
//        String FMCurl = "https://fcm.googleapis.com/fcm/send";
//
//        URL url = new URL(FMCurl);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//        conn.setUseCaches(false);
//        conn.setDoInput(true);
//        conn.setDoOutput(true);
//
//        conn.setRequestMethod("POST");
//        conn.setRequestProperty("Authorization","key="+authKey);
//        conn.setRequestProperty("Content-Type","application/json");
//
//        JSONObject json = new JSONObject();
//        json.put("to","/topics/school");
//        JSONObject data = new JSONObject();
//        data.put("message",notificationRequestDto.getTitle());
//        json.put("data", data);
//
//        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
//        wr.write(json.toString());
//        wr.flush();
//        conn.getInputStream();
//        return new ResponseDto();
    }
}
