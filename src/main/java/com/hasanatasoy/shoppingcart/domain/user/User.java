package com.hasanatasoy.shoppingcart.domain.user;

import com.hasanatasoy.shoppingcart.base.domain.BaseModel;
import com.hasanatasoy.shoppingcart.domain.user.authinfo.UserAuthInfo;
import com.hasanatasoy.shoppingcart.domain.user.info.UserInfo;
import com.hasanatasoy.shoppingcart.domain.user.role.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseModel {

    private boolean accountEnabled = false;
    private String profilePhotoUrl;
    @ElementCollection
    @CollectionTable(name = "UserRoles", joinColumns = @JoinColumn(name = "Users_id"))
    private List<UserRole> userRoles = new ArrayList<>();
    @OneToOne(fetch = FetchType.LAZY)
    private UserInfo userInfo;
    @OneToOne(fetch = FetchType.LAZY)
    private UserAuthInfo userAuthInfo;

    public User(UserAuthInfo userAuthInfo, UserInfo userInfo, UserRole userRole){
        this.userAuthInfo = userAuthInfo;
        this.userInfo = userInfo;
        userRoles.add(userRole);
    }


}
