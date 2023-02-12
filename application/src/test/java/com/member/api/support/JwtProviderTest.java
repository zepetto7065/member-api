package com.member.api.support;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

class JwtProviderTest {

    private String secret = "secret";
    private long tokenValidityInMilliseconds = 86400;

    @Test
    void getTokenTest(){
        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);

        String compact = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secret)
                .setExpiration(validity)
                .compact();

        System.out.println(compact);
    }

}