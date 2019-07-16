package com.hasanatasoy.shoppingcart.controller;

import com.hasanatasoy.shoppingcart.authentication.Crypt;
import com.hasanatasoy.shoppingcart.base.messages.Response;
import com.hasanatasoy.shoppingcart.enums.user.UserResponse;
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
    public Response<UserResponse> verification(@PathVariable String token){
        String email = Crypt.decodeBase64(token);
        emailVerificationService.validateEmail(email);
        emailVerificationService.validateAlreadyActive(email);
        emailVerificationService.setUserAccountActiveWith(email);
        return Response.<UserResponse>builder().message("Email Verification is successfully").result(UserResponse.SUCCESS).build();
    }

}
