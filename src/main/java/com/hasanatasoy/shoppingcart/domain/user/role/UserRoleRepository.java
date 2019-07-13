package com.hasanatasoy.shoppingcart.domain.user.role;

import org.springframework.data.repository.CrudRepository;


public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

    UserRole findByRoleName(RoleName roleName);
}
