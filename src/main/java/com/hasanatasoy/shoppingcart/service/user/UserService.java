package com.hasanatasoy.shoppingcart.service.user;

import com.hasanatasoy.shoppingcart.authentication.Crypt;
import com.hasanatasoy.shoppingcart.domain.user.User;
import com.hasanatasoy.shoppingcart.domain.user.UserGender;
import com.hasanatasoy.shoppingcart.domain.user.UserRepository;
import com.hasanatasoy.shoppingcart.domain.user.authinfo.UserAuth;
import com.hasanatasoy.shoppingcart.domain.user.info.UserInfo;
import com.hasanatasoy.shoppingcart.domain.user.role.RoleName;
import com.hasanatasoy.shoppingcart.domain.user.role.UserRole;
import com.hasanatasoy.shoppingcart.domain.user.role.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserRoleRepository userRoleRepository;

    public void createNewUser(String email, String password, UserGender userGender) {
        String encodedPassword = Crypt.encode(password);
        userAuthService.validateDoesNotExist(email);
        UserAuth userAuth = new UserAuth(email, encodedPassword);
        userAuthService.save(userAuth);
        UserInfo userInfo = new UserInfo(userGender);
        userInfoService.save(userInfo);
        UserRole userRole = userRoleRepository.findByRoleName(RoleName.Client);
        User user = new User(userAuth, userInfo, userRole);
        userRepository.save(user);
    }

}
