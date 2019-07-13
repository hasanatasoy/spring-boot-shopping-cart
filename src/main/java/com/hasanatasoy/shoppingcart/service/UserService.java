package com.hasanatasoy.shoppingcart.service;

import com.hasanatasoy.shoppingcart.authentication.JwtProvider;
import com.hasanatasoy.shoppingcart.domain.user.User;
import com.hasanatasoy.shoppingcart.domain.user.UserRepository;
import com.hasanatasoy.shoppingcart.domain.user.authinfo.UserAuthInfo;
import com.hasanatasoy.shoppingcart.domain.user.authinfo.UserAuthInfoRepository;
import com.hasanatasoy.shoppingcart.domain.user.info.UserInfo;
import com.hasanatasoy.shoppingcart.domain.user.info.UserInfoRepository;
import com.hasanatasoy.shoppingcart.domain.user.role.RoleName;
import com.hasanatasoy.shoppingcart.domain.user.role.UserRole;
import com.hasanatasoy.shoppingcart.domain.user.role.UserRoleRepository;
import com.hasanatasoy.shoppingcart.dto.login.LoginDTO;
import com.hasanatasoy.shoppingcart.dto.register.RegisterDTO;
import com.hasanatasoy.shoppingcart.enums.user.UserLoginResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAuthInfoRepository userAuthInfoRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
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
        UserRole userRole = userRoleRepository.findByRoleName(RoleName.Client);
        User user = new User(userAuthInfo, userInfo, userRole);
        userRepository.save(user);
    }


    public UserLoginResult getResultIsEmailAndPasswordCorrect(LoginDTO loginDTO) {

        Optional<UserAuthInfo> userAuthInfo = userAuthInfoRepository.findByEmail(loginDTO.getEmail());
        User user = userRepository.findByUserAuthInfo(userAuthInfo.get()).get();
        if(!user.isAccountEnabled())
            return UserLoginResult.INACTIVEACCOUNT;
        if(userAuthInfo.isPresent()){
            boolean isEmailAndPasswordCorrect = userAuthInfo.get().getEmail().equals(loginDTO.getEmail())
                                                && passwordEncoder.matches(loginDTO.getPassword(), userAuthInfo.get().getPassword());
            boolean isEmailCorrect = userAuthInfo.get().getEmail().equals(loginDTO.getEmail());
            boolean isPasswordCorrect = passwordEncoder.matches(loginDTO.getPassword(), userAuthInfo.get().getPassword());
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
