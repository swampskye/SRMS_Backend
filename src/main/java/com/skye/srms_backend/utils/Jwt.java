package com.skye.srms_backend.utils;

import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.UUID;


import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Slf4j
@Component
public class Jwt {

    private static final long JWT_EXPIRE = 30 * 60 * 1000;

    private static final String JWT_KEY = "skye";


    public String createToken(Object data){
        long currentTime = System.currentTimeMillis();
        long expireTime = currentTime + JWT_EXPIRE;

        JwtBuilder builder = Jwts.builder()
                .setId(UUID.randomUUID()+"")
                .setSubject(JSON.toJSONString(data))
                .setIssuer("system_skye")
                .setIssuedAt(new Date(currentTime))
                .signWith(SignatureAlgorithm.HS256, encodeSecret(JWT_KEY))
                .setExpiration(new Date(expireTime));
        log.debug("in jwt ---------------builder.compact()"+builder.compact());
        return builder.compact();
    }

    public Claims parseToken(String token){
        Claims body = Jwts.parser()
                .setSigningKey(encodeSecret(JWT_KEY))
                .parseClaimsJws(token)
                .getBody();
        return body;
    }


    public <T> T parseToken(String token, Class<T> tClass){
        log.debug("in jwt parseToken --------------- token:"+token);
        Claims body = Jwts.parser()
                .setSigningKey(encodeSecret(JWT_KEY))
                .parseClaimsJws(token)
                .getBody();
        return JSON.parseObject(body.getSubject(), tClass);
    }


    private SecretKey encodeSecret(String key){
        byte[] encode = Base64.getEncoder().encode(key.getBytes());
        SecretKeySpec aes = new SecretKeySpec(encode, 0, encode.length, "AES");
        return aes;
    }



}
