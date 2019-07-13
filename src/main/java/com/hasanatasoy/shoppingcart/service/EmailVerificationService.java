package com.hasanatasoy.shoppingcart.service;

import com.hasanatasoy.shoppingcart.domain.user.User;
import com.hasanatasoy.shoppingcart.domain.user.UserRepository;
import com.hasanatasoy.shoppingcart.domain.user.authinfo.UserAuthInfo;
import com.hasanatasoy.shoppingcart.domain.user.authinfo.UserAuthInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

@Service
public class EmailVerificationService {

    private String URL = "localhost:8080/verify/email/";
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
        return Base64.getEncoder().encodeToString(toBeEncoded.getBytes());
    }

    private void sendEmailWithVerificationPath(String verificationPath) {
        System.out.println(verificationPath);
    }

    public String decodeBase64(String toBeDecoded){
        byte[] decodedByteArray = Base64.getDecoder().decode(toBeDecoded.getBytes());
        return new String(decodedByteArray);
    }

    public boolean isEmailMatched(String email){
        Optional<UserAuthInfo> userAuthInfo = userAuthInfoRepository.findByEmail(email);
        return userAuthInfo.isPresent();
    }

    public void setUserAccountActive(String email){
        UserAuthInfo userAuthInfo = userAuthInfoRepository.findByEmail(email).get();
        User user = userRepository.findByUserAuthInfo(userAuthInfo).get();
        user.setAccountEnabled(true);
        userRepository.save(user);
    }
}
