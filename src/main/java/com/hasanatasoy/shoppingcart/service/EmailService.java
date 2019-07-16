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

    public void validateEmail(String targetEmail){
        boolean isNotMatched = !userAuthService.findBy(targetEmail).isPresent();
        if(isNotMatched)
            throw new ForbiddenException("You don't have permission");
    }

    public void setUserAccountActiveWith(String email){
        UserAuth userAuth = userAuthService.findBy(email).get();
        userAuth.setAccountEnabled(true);
        userAuthService.save(userAuth);
    }

    public void sendResetPasswordUrlWith(String targetEmail, String secureCode) {
        UserAuth userAuth = userAuthService.findBy(targetEmail).get();
        System.out.println(URL+"/authentication/resetpass/"+userAuth.getId()+"/"+secureCode);
    }

    public void validateAlreadyActive(String email) {
        UserAuth userAuth = userAuthService.findBy(email).get();
        if(userAuth.isAccountEnabled())
            throw new BadRequestExcepiton("User account already active");
    }
}
