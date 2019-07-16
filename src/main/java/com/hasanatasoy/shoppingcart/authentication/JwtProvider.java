package com.hasanatasoy.shoppingcart.authentication;

import com.hasanatasoy.shoppingcart.base.messages.request.RequestLogin;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    @Value("$(secret-key)")
    private String secretKey;
    private int expiration = 300000;

    public String generateJsonWebToken(RequestLogin requestLogin){
        return Jwts.builder()
                .setSubject(requestLogin.getEmail()+" "+requestLogin.getPassword())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+expiration))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public RequestLogin getLoginDtoFrom(String token){
        String subject = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        String[] login = subject.split(" ");
        return new RequestLogin(login[0],login[1]);
    }

    public boolean validateJsonWebToken(String token){
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean isExpirationPassed(String token){
        Date expiration = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration();
        return expiration.getTime() < new Date().getTime();
    }
}
