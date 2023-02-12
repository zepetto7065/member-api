package com.member.api.support;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.token-validity-in-seconds}")
    private long tokenValidityInMilliseconds;

    private Key key;

    public String getToken(){
        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secret)
                .setExpiration(validity)
                .compact();
    }
}
