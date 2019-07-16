package com.hasanatasoy.shoppingcart.controller;

import com.hasanatasoy.shoppingcart.authentication.Crypt;
import com.hasanatasoy.shoppingcart.authentication.JwtProvider;
import com.hasanatasoy.shoppingcart.base.messages.Response;
import com.hasanatasoy.shoppingcart.base.messages.request.RequestLogin;
import com.hasanatasoy.shoppingcart.base.messages.request.RequestRegister;
import com.hasanatasoy.shoppingcart.base.messages.request.RequestResetPassword;
import com.hasanatasoy.shoppingcart.base.util.BindingValidator;
import com.hasanatasoy.shoppingcart.enums.user.UserResponse;
import com.hasanatasoy.shoppingcart.service.EmailService;
import com.hasanatasoy.shoppingcart.service.user.UserAuthService;
import com.hasanatasoy.shoppingcart.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/authentication")
public class AuthenticationController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private JwtProvider jsonWebTokenProvider;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Response<UserResponse> register(@Valid @RequestBody RequestRegister requestRegister, BindingResult bindingResult){
        BindingValidator.validate(bindingResult);
        userService.createNewUser(requestRegister.getEmail(), requestRegister.getPassword(), requestRegister.getUserGender());
        emailService.sendEmailVerificationTo(requestRegister.getEmail());
        return Response.<UserResponse>builder().result(UserResponse.SUCCESS).message("Sign up is successfully. Please Verification your email.").build();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response<String> login(@Valid @RequestBody RequestLogin requestLogin, BindingResult bindingResult){
        BindingValidator.validate(bindingResult);
        userAuthService.validateUserAccountActive(requestLogin.getEmail());
        userAuthService.validateEmailAndPasswordCorrect(requestLogin.getEmail(), requestLogin.getPassword());
        String JsonWebToken = jsonWebTokenProvider.generateJsonWebToken(requestLogin);
        return Response.<String>builder().message("Login is successfully.").result(JsonWebToken).build();

    }

    @RequestMapping(value = "/forgotpass", method = RequestMethod.POST)
    public Response<UserResponse> forgatPassword(@RequestBody String email){
        userAuthService.validateIs(email);
        userAuthService.validateDoesExist(email);
        String secureCode = Crypt.generateSecureCodeWith(email);
        emailService.sendResetPasswordUrlWith(email, secureCode);
        return Response.<UserResponse>builder().result(UserResponse.SUCCESS).message("Your new password successfully sent.").build();
    }

    @RequestMapping(value = "/resetpass/{secureCode}", method = RequestMethod.POST)
    public Response<UserResponse> resetPassword(@PathVariable String secureCode, @RequestBody RequestResetPassword requestResetPassword, BindingResult bindingResult){
        BindingValidator.validate(bindingResult);
        userAuthService.validateDoesExist(requestResetPassword.getEmail());
        userAuthService.validateSecureCode(requestResetPassword.getEmail(), secureCode);
        userAuthService.setUserPassword(requestResetPassword.getEmail(), requestResetPassword.getPassword());
        return Response.<UserResponse>builder().result(UserResponse.SUCCESS).message("Your password successfully changed.").build();
    }


}
