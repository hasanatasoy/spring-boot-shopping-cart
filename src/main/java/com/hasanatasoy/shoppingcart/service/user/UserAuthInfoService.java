package com.hasanatasoy.shoppingcart.service.user;

import com.hasanatasoy.shoppingcart.domain.user.authinfo.UserAuthInfo;
import com.hasanatasoy.shoppingcart.domain.user.authinfo.UserAuthInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthInfoService {

    @Autowired
    UserAuthInfoRepository userAuthInfoRepository;

    public Optional<UserAuthInfo> findBy(String email){
        return userAuthInfoRepository.findByEmail(email);
    }

    public Optional<UserAuthInfo> findBy(Long id){
        return userAuthInfoRepository.findById(id);
    }

    public void save(UserAuthInfo userAuthInfo) {
        userAuthInfoRepository.save(userAuthInfo);
    }
}
