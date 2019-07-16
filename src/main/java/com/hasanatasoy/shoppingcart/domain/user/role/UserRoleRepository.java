package com.hasanatasoy.shoppingcart.domain.user.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    UserRole findByRoleName(RoleName roleName);
}
