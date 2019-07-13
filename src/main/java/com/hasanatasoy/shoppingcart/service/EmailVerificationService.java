package com.hasanatasoy.shoppingcart.service;

import com.hasanatasoy.shoppingcart.domain.user.User;
import com.hasanatasoy.shoppingcart.domain.user.UserRepository;
import com.hasanatasoy.shoppingcart.domain.user.authinfo.UserAuthInfo;
import com.hasanatasoy.shoppingcart.domain.user.authinfo.UserAuthInfoRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

public class EmailVerificationService {

    private String URL = "localhost:8080/client/verify/email/";
    private String encodedEmail;

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserAuthInfoRepository userAuthInfoRepository;

    public void sendEmailVerification(String email) {
        encodedEmail = encodeBase64(email);
        sendEmailWithVerificationPath(URL+encodedEmail);
    }

    private String encodeBase64(String toBeEncoded) {
        String encoded = Base64.getEncoder().encodeToString(toBeEncoded.getBytes());
        return Base64.getEncoder().encodeToString(encoded.getBytes());
    }

    private void sendEmailWithVerificationPath(String verificationPath) {
        System.out.println(verificationPath);
    }

    public String decodeBase64(String toBeDecoded){
        String decoded = Arrays.toString(Base64.getDecoder().decode(toBeDecoded));
        return Arrays.toString(Base64.getDecoder().decode(decoded));
    }

    public boolean isEmailMatched(String email){
        Optional<UserAuthInfo> userAuthInfo = userAuthInfoRepository.findByEmail(email);
        return userAuthInfo.map(authInfo -> authInfo.getEmail().equals(email)).orElse(false);
    }

    public void setUserAccountActive(String email){
        UserAuthInfo userAuthInfo = userAuthInfoRepository.findByEmail(email).get();
        User user = userRepository.findByUserAuthInfo(userAuthInfo).get();
        user.setAccountEnabled(true);
    }
}
