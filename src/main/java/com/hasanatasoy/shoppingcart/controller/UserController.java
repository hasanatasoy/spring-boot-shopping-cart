package com.hasanatasoy.shoppingcart.controller;

import com.hasanatasoy.shoppingcart.base.domain.Response;
import com.hasanatasoy.shoppingcart.dto.login.LoginDTO;
import com.hasanatasoy.shoppingcart.dto.register.RegisterDTO;
import com.hasanatasoy.shoppingcart.enums.user.UserLoginResult;
import com.hasanatasoy.shoppingcart.enums.user.UserRegisterResult;
import com.hasanatasoy.shoppingcart.enums.user.UserValid;
import com.hasanatasoy.shoppingcart.service.EmailVerificationService;
import com.hasanatasoy.shoppingcart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailVerificationService emailVerificationService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Response<?> register(@Valid @RequestBody RegisterDTO registerDTO, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            List<String> errors = new ArrayList<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errors.add(error.getField());
            });
            return new Response<>(false, HttpStatus.BAD_REQUEST.value(), "Valid not correct : "+errors.toString(), UserValid.ERROR);
        }
        if(userService.isEmailAlreadyTaken(registerDTO.getEmail()))
            return new Response<>(false, HttpStatus.BAD_REQUEST.value(), "Email already taken", UserRegisterResult.EXISTEMAIL);
        userService.createNewUser(registerDTO);
        emailVerificationService.sendEmailVerification(registerDTO.getEmail());
        return new Response<>(true,HttpStatus.OK.value(), "Sign up is successfully. Please Verification your email ", UserRegisterResult.SUCCESS);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response<?> login(@Valid @RequestBody LoginDTO loginDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new Response<>(false, HttpStatus.BAD_REQUEST.value(), bindingResult.toString(), UserValid.ERROR);
        UserLoginResult userLoginResult = userService.getResultIsEmailAndPasswordCorrect(loginDTO);
        if(userLoginResult.equals(UserLoginResult.CORRECT)){
            System.out.println("4");
            String JsonWebToken = userService.createJsonWebToken(loginDTO);
            return new Response<>(true, HttpStatus.OK.value(), "Login is successfully.", JsonWebToken);
        }
        else if(userLoginResult.equals(UserLoginResult.UNCORRECTBOTH))
            return new Response<>(false, HttpStatus.FORBIDDEN.value(), "Email and Password is not correct", UserLoginResult.UNCORRECTBOTH);
        else if(userLoginResult.equals(UserLoginResult.UNCORRECTPASSWORD))
            return new Response<>(false, HttpStatus.FORBIDDEN.value(), "Password is not correct", UserLoginResult.UNCORRECTPASSWORD);
        else if(userLoginResult.equals(UserLoginResult.UNCORRECTEMAIL))
            return new Response<>(false, HttpStatus.FORBIDDEN.value(), "Email is not correct", UserLoginResult.UNCORRECTEMAIL);
        else if(userLoginResult.equals(UserLoginResult.NOTFOUNDEMAIL))
            return new Response<>(false, HttpStatus.NOT_FOUND.value(), "There is no account for this email", UserLoginResult.NOTFOUNDEMAIL);
        else
            return new Response<>(false, HttpStatus.FORBIDDEN.value(), "Please check your email for activation", UserLoginResult.INACTIVEACCOUNT);
    }
}
