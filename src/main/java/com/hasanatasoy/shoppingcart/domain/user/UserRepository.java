package com.hasanatasoy.shoppingcart.domain.user;

import com.hasanatasoy.shoppingcart.domain.user.authinfo.UserAuth;
import com.hasanatasoy.shoppingcart.domain.user.info.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserInfo(UserInfo userInfo);
    Optional<User> findByUserAuth(UserAuth userAuth);
}
