package com.hasanatasoy.shoppingcart.service;

import com.hasanatasoy.shoppingcart.authentication.Crypt;
import com.hasanatasoy.shoppingcart.domain.user.authinfo.UserAuth;
import com.hasanatasoy.shoppingcart.exceptionhandler.base.exception.BadRequestExcepiton;
import com.hasanatasoy.shoppingcart.exceptionhandler.base.exception.ForbiddenException;
import com.hasanatasoy.shoppingcart.service.user.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private String URL = "localhost:8080";

    @Autowired
    UserAuthService userAuthService;

    public void sendEmailVerificationTo(String email) {
        String encodedEmail = Crypt.encodeBase64(email);
        sendEmailWith(URL+"/verify/email/"+encodedEmail);
    }

    private void sendEmailWith(String verificationPath) {
        System.out.println(verificationPath);
    }

    public void sendResetPasswordUrlWith(String secureCode) {
        System.out.println(URL+"/authentication/resetpass/"+secureCode);
    }
}
