package com.hasanatasoy.shoppingcart.service;

import com.hasanatasoy.shoppingcart.authentication.JwtProvider;
import com.hasanatasoy.shoppingcart.domain.user.User;
import com.hasanatasoy.shoppingcart.domain.user.UserRepository;
import com.hasanatasoy.shoppingcart.domain.user.authinfo.UserAuthInfo;
import com.hasanatasoy.shoppingcart.domain.user.authinfo.UserAuthInfoRepository;
import com.hasanatasoy.shoppingcart.domain.user.info.UserInfo;
import com.hasanatasoy.shoppingcart.domain.user.info.UserInfoRepository;
import com.hasanatasoy.shoppingcart.dto.login.LoginDTO;
import com.hasanatasoy.shoppingcart.dto.register.RegisterDTO;
import com.hasanatasoy.shoppingcart.enums.user.UserLoginResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAuthInfoRepository userAuthInfoRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;

    public boolean isEmailAlreadyTaken(String email) {

        return userAuthInfoRepository.findByEmail(email).isPresent();
    }

    public void createNewUser(RegisterDTO registerDTO) {
        String encodedPassword = passwordEncoder.encode(registerDTO.getPassword());
        UserAuthInfo userAuthInfo = new UserAuthInfo(registerDTO.getEmail(), encodedPassword);
        userAuthInfoRepository.save(userAuthInfo);
        UserInfo userInfo = new UserInfo(registerDTO.getUserGender());
        userInfoRepository.save(userInfo);
        User user = new User(userAuthInfo, userInfo);
        userRepository.save(user);
    }


    public UserLoginResult getResultIsEmailAndPasswordCorrect(LoginDTO loginDTO) {
        String encodedPassword = passwordEncoder.encode(loginDTO.getPassword());
        loginDTO.setPassword(encodedPassword);
        Optional<UserAuthInfo> userAuthInfo = userAuthInfoRepository.findByEmail(loginDTO.getEmail());
        if(userAuthInfo.isPresent()){
            boolean isEmailAndPasswordCorrect = userAuthInfo.get().getEmail().equals(loginDTO.getEmail())
                                                && userAuthInfo.get().getPassword().equals(loginDTO.getPassword());
            boolean isEmailCorrect = userAuthInfo.get().getEmail().equals(loginDTO.getEmail());
            boolean isPasswordCorrect = userAuthInfo.get().getPassword().equals(loginDTO.getPassword());
            if(isEmailAndPasswordCorrect)
                return UserLoginResult.CORRECT;
            else if(isEmailCorrect)
                return UserLoginResult.UNCORRECTPASSWORD;
            else if(isPasswordCorrect)
                return UserLoginResult.UNCORRECTEMAIL;
            else
                return UserLoginResult.UNCORRECTBOTH;
        }
        else
            return UserLoginResult.NOTFOUNDEMAIL;
    }

    public String createJsonWebToken(LoginDTO loginDTO) {
        return jwtProvider.generateJsonWebToken(loginDTO);
    }
}
