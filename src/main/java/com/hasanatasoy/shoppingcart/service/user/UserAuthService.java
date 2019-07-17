package com.hasanatasoy.shoppingcart.service.user;

import com.hasanatasoy.shoppingcart.authentication.Crypt;
import com.hasanatasoy.shoppingcart.domain.user.authinfo.UserAuth;
import com.hasanatasoy.shoppingcart.domain.user.authinfo.UserAuthRepository;
import com.hasanatasoy.shoppingcart.exceptionhandler.base.exception.BadRequestExcepiton;
import com.hasanatasoy.shoppingcart.exceptionhandler.base.exception.ForbiddenException;
import com.hasanatasoy.shoppingcart.exceptionhandler.user.userauth.UserAuthNotFoundException;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthService {

    @Autowired
    UserAuthRepository userAuthRepository;

    public UserAuth findBy(String email){
        Optional<UserAuth> userAuth = userAuthRepository.findByEmail(email);
        boolean emailDoesNotExist = userAuth.isPresent();
        if(emailDoesNotExist)
            throw new UserAuthNotFoundException(email);
        return userAuth.get();
    }

    public void save(UserAuth userAuth) {
        userAuthRepository.save(userAuth);
    }

    public void validateDoesNotExist(String email) {
        boolean emailDoesExist = userAuthRepository.findByEmail(email).isPresent();
        if(emailDoesExist)
            throw new BadRequestExcepiton("Email already taken");
    }

    public void validateDoesExist(String email){
        findBy(email);
    }

    public void validateUserAccountActive(String email){
        UserAuth userAuth = findBy(email);
        boolean isAccountNotEnabled = !userAuth.isAccountEnabled();
        if(isAccountNotEnabled)
            throw new ForbiddenException("Account not active");
    }

    public void validateEmailAndPasswordCorrect(String email, String password) {
        UserAuth userAuth = findBy(email);
        boolean isPasswordNotCorrect = !Crypt.isMatched(password, userAuth.getPassword());
        if(isPasswordNotCorrect)
            throw new BadRequestExcepiton("Password is not correct");
    }

    public void validateIs(String email){
        boolean isNotValid = !EmailValidator.getInstance().isValid(email);
        if(isNotValid)
            throw new BadRequestExcepiton("Email is invalid type");
    }

    public void validateSecureCode(String email, String secureCode) {
        boolean isNotMatched = Crypt.isMatched(email, secureCode);
        if(isNotMatched)
            throw new ForbiddenException("You don't have permission");
    }

    public void validateAlreadyActive(String email) {
        UserAuth userAuth = findBy(email);
        if(userAuth.isAccountEnabled())
            throw new BadRequestExcepiton("User account already active");
    }

    public void setUserAccountActiveWith(String email){
        UserAuth userAuth = findBy(email);
        userAuth.setAccountEnabled(true);
        save(userAuth);
    }

    public void setUserPassword(String email, String password){
        UserAuth userAuth = findBy(email);
        String encodedPassword = Crypt.encode(password);
        userAuth.setPassword(encodedPassword);
        userAuthRepository.save(userAuth);
    }
}
