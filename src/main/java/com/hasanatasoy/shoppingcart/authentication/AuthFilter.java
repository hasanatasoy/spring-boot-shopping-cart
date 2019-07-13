package com.hasanatasoy.shoppingcart.authentication;

import com.hasanatasoy.shoppingcart.dto.login.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Configuration
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("Authentication");
        if(token != null && jwtProvider.validateJsonWebToken(token)){
            if(jwtProvider.isExpirationPassed(token)){
                LoginDTO loginDTO = jwtProvider.getLoginDtoFrom(token);
            }
        }
        filterChain.doFilter(request,response);
    }
}
