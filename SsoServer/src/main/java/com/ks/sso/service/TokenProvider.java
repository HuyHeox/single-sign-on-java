package com.ks.sso.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ks.sso.service.DTO.JWTTokenClaim;

@Service
public class TokenProvider {
    private String secret = "secret";
    private long expirationTime = 3600000*24*7;

    public String createToken(String email) {
        try {
            Date expiredDate = new Date(new Date().getTime() + expirationTime);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withExpiresAt(expiredDate)
                    .withClaim("email", email)
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            return null;
        }
    }

    public JWTTokenClaim decodeToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build(); //Reusable verifier instance
            DecodedJWT decodedJWT = verifier.verify(token);
            Date expiredDate = decodedJWT.getExpiresAt();
            String email = decodedJWT.getClaim("email").asString();

            return new JWTTokenClaim(email, expiredDate);
        } catch (JWTVerificationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
            throw new RuntimeException();
        }
    }


    public Boolean ValidateLoginToken(JWTTokenClaim jwtTokenClaim) {
        String username = jwtTokenClaim.getEmail();

        if (username == null || username.isEmpty()) {
            return false;
        }
        if (jwtTokenClaim.getExpiredDate().before(new Date())) {
            return false;
        }
        return true;
    }


}