package com.hasanatasoy.shoppingcart.domain.user.authinfo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAuthInfoRepository extends CrudRepository<UserAuthInfo, Long> {

    Optional<UserAuthInfo> findByEmail(String email);
}
