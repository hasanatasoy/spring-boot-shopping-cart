package com.hasanatasoy.shoppingcart.controller;

import com.hasanatasoy.shoppingcart.authentication.Crypt;
import com.hasanatasoy.shoppingcart.authentication.JwtProvider;
import com.hasanatasoy.shoppingcart.base.domain.Response;
import com.hasanatasoy.shoppingcart.domain.user.authinfo.UserAuthInfo;
import com.hasanatasoy.shoppingcart.dto.login.LoginDTO;
import com.hasanatasoy.shoppingcart.dto.register.RegisterDTO;
import com.hasanatasoy.shoppingcart.enums.user.UserResponse;
import com.hasanatasoy.shoppingcart.service.EmailService;
import com.hasanatasoy.shoppingcart.service.user.UserAuthInfoService;
import com.hasanatasoy.shoppingcart.service.user.UserService;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/authentication")
public class AuthenticationController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserAuthInfoService userAuthInfoService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private JwtProvider jsonWebTokenProvider;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Response<?> register(@Valid @RequestBody RegisterDTO registerDTO, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            List<String> errors = new ArrayList<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errors.add(error.getField());
            });
            return new Response<>(false, HttpStatus.BAD_REQUEST.value(), "Invalid: "+errors.toString(), UserResponse.VALIDERROR);
        }
        if(userAuthInfoService.findBy(registerDTO.getEmail()).isPresent())
            return new Response<>(false, HttpStatus.BAD_REQUEST.value(), "Email already taken", UserResponse.EXISTEMAIL);
        userService.createNewUser(registerDTO);
        emailService.sendEmailVerificationTo(registerDTO.getEmail());
        return new Response<>(true,HttpStatus.OK.value(), "Sign up is successfully. Please Verification your email ", UserResponse.SUCCESS);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response<?> login(@Valid @RequestBody LoginDTO loginDTO, BindingResult bindingResult){

        UserResponse userResponse = userService.getResultIsEmailAndPasswordCorrect(loginDTO);
        if(userResponse.equals(UserResponse.CORRECT)){
            String JsonWebToken = jsonWebTokenProvider.generateJsonWebToken(loginDTO);
            return new Response<>(true, HttpStatus.OK.value(), "Login is successfully.", JsonWebToken);
        }
        else if(userResponse.equals(UserResponse.UNCORRECTBOTH))
            return new Response<>(false, HttpStatus.FORBIDDEN.value(), "Email and Password is not correct", UserResponse.UNCORRECTBOTH);
        else if(userResponse.equals(UserResponse.UNCORRECTPASSWORD))
            return new Response<>(false, HttpStatus.FORBIDDEN.value(), "Password is not correct", UserResponse.UNCORRECTPASSWORD);
        else if(userResponse.equals(UserResponse.UNCORRECTEMAIL))
            return new Response<>(false, HttpStatus.FORBIDDEN.value(), "Email is not correct", UserResponse.UNCORRECTEMAIL);
        else if(userResponse.equals(UserResponse.NOTFOUND))
            return new Response<>(false, HttpStatus.NOT_FOUND.value(), "There is no account for this email", UserResponse.NOTFOUND);
        else
            return new Response<>(false, HttpStatus.FORBIDDEN.value(), "Please check your email for activation", UserResponse.INACTIVEACCOUNT);
    }

    @RequestMapping(value = "/forgatpass", method = RequestMethod.POST)
    public Response<?> forgatPassword(@RequestBody String email){
        System.out.println(email);
        boolean valid = EmailValidator.getInstance().isValid(email);
        if(!valid)
            return new Response<>(false, HttpStatus.BAD_REQUEST.value(), "Email is invalid type", UserResponse.VALIDERROR);
        Optional<UserAuthInfo> userAuthInfo = userAuthInfoService.findBy(email);
        if(userAuthInfo.isPresent()){
            String secureCode = Crypt.hash(email);
            emailService.sendResetPasswordPageWith(email, userAuthInfo.get().getId(), secureCode);
        }
        else
            return new Response<>(false, HttpStatus.NOT_FOUND.value(), "Email is not found", UserResponse.NOTFOUND);
        return new Response<>(true, HttpStatus.OK.value(), "Your new password successfully sent ", UserResponse.SUCCESS);
    }

    @RequestMapping(value = "/resetpass/{authId}/{secureCode}/{newpass}", method = RequestMethod.GET)
    public Response<?> resetPassword(@PathVariable("authId") Long id, @PathVariable("secureCode") String secureCode, @PathVariable("newpass") String newPassword){
        Optional<UserAuthInfo> userAuthInfo = userAuthInfoService.findBy(id);
        if(userAuthInfo.isPresent()){
            String email = userAuthInfo.get().getEmail();
            String hashingEmail = Crypt.hash(email);
            if(hashingEmail.equals(secureCode)){
                String passwordHash = userService.encode(newPassword);
                // this part must be something passwordService Crypt or something like that not userService
                // maybe hash process should be in setPassword() think later
                userAuthInfo.get().setPassword(passwordHash);
                userAuthInfoService.save(userAuthInfo.get());
                return new Response<>(true, HttpStatus.OK.value(), "Password is successfully changed", UserResponse.SUCCESS);
            }
            else
                return new Response<>(false, HttpStatus.FORBIDDEN.value(), "You dont have permission", UserResponse.UNAUTHORIZATION);
        }
        else
            return new Response<>(false, HttpStatus.NOT_FOUND.value(), "Not found any user", UserResponse.NOTFOUND);
    }


}
