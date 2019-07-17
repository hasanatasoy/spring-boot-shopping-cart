package com.hasanatasoy.shoppingcart.controller;

import com.hasanatasoy.shoppingcart.authentication.Crypt;
import com.hasanatasoy.shoppingcart.base.messages.Response;
import com.hasanatasoy.shoppingcart.domain.user.authinfo.UserAuth;
import com.hasanatasoy.shoppingcart.enums.user.UserResponse;
import com.hasanatasoy.shoppingcart.service.EmailService;
import com.hasanatasoy.shoppingcart.service.user.UserAuthService;
import com.hasanatasoy.shoppingcart.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/verify/email")
public class EmailVerificationController {

    @Autowired
    private UserAuthService userAuthService;

    @RequestMapping(value = "/{token}", method = RequestMethod.GET)
    public Response<UserResponse> verification(@PathVariable String token){
        String email = Crypt.decodeBase64(token);
        userAuthService.validateDoesExist(email);
        userAuthService.validateAlreadyActive(email);
        userAuthService.setUserAccountActiveWith(email);
        return Response.<UserResponse>builder().message("Email Verification is successfully").result(UserResponse.SUCCESS).build();
    }

}
