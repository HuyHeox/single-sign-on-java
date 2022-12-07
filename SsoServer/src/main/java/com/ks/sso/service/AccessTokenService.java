package com.ks.sso.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ks.sso.service.DTO.AccessToken;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AccessTokenService {

    private long expirationTimeAccessToken = 86400000;
    private long expirationTimeRefreshToken = 86400000;


    private String secret = "clientsecret";


    public String decodeCode(String code) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build(); //Reusable verifier instance
            DecodedJWT decodedJWT = verifier.verify(code);
            Date expiredDate = decodedJWT.getExpiresAt();
            String coderesult = decodedJWT.getClaim("code").asString();

            return coderesult;
        } catch (JWTVerificationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
            throw new RuntimeException();
        }
    }

    public String CreateAccessToken() {

        try {
            Date expiredDate = new Date(new Date().getTime() + expirationTimeAccessToken);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withExpiresAt(expiredDate)
                    .withClaim("username", "admin")
                    .withClaim("role", "admin")
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            return null;
        }
    }

    public String CreateRefreshToken() {
        try {
            Date expiredDate = new Date(new Date().getTime() + expirationTimeAccessToken);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withExpiresAt(expiredDate)
                    .withClaim("clientId", "clientId")
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            return null;
        }
    }

    public AccessToken generate() {
        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken(CreateAccessToken());
        accessToken.setRefreshToken(CreateRefreshToken());
        return accessToken;
    }

    public String createAccessTokenFromRefreshToken(String refreshToken, String clientId) {
        return CreateAccessToken();
    }
}
