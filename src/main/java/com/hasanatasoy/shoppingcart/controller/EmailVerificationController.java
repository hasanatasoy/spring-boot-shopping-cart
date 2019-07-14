package com.hasanatasoy.shoppingcart.controller;

import com.hasanatasoy.shoppingcart.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/verify/email")
public class EmailVerificationController {

    @Autowired
    private EmailService emailVerificationService;

    @RequestMapping(value = "/{token}", method = RequestMethod.GET)
    public String verification(@PathVariable String token){
        String email = emailVerificationService.decodeBase64(token);
        boolean isMatched = emailVerificationService.isEmailMatched(email);
        if(isMatched){
            emailVerificationService.setUserAccountActive(email);
            return "HOME PAGE";
        }
        return "UNAUTHORIZED";
    }

}
