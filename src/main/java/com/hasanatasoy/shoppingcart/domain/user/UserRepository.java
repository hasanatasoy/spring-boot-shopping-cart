package com.hasanatasoy.shoppingcart.domain.user;

import com.hasanatasoy.shoppingcart.domain.user.authinfo.UserAuthInfo;
import com.hasanatasoy.shoppingcart.domain.user.info.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUserInfo(UserInfo userInfo);
    Optional<User> findByUserAuthInfo(UserAuthInfo userAuthInfo);
}
