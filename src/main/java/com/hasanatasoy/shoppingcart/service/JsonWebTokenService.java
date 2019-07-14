package com.hasanatasoy.shoppingcart.service;

import com.hasanatasoy.shoppingcart.authentication.JwtProvider;
import com.hasanatasoy.shoppingcart.dto.login.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class JsonWebTokenService {

    @Autowired
    JwtProvider jwtProvider;

    public String createJsonWebToken(LoginDTO loginDTO) {

        return jwtProvider.generateJsonWebToken(loginDTO);
    }
}
