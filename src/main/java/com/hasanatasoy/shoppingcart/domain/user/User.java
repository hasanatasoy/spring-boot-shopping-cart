package com.hasanatasoy.shoppingcart.domain.user;

import com.hasanatasoy.shoppingcart.base.domain.BaseModel;
import com.hasanatasoy.shoppingcart.domain.user.authinfo.UserAuth;
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
@SequenceGenerator(name = "usergenerator", sequenceName = "user_seq")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseModel {

    private String profilePhotoUrl;
    @ElementCollection
    @CollectionTable(name = "UserRoles", joinColumns = @JoinColumn(name = "users_id"))
    private List<UserRole> userRoles = new ArrayList<>();
    @OneToOne(fetch = FetchType.LAZY)
    private UserInfo userInfo;
    @OneToOne(fetch = FetchType.LAZY)
    private UserAuth userAuth;

    public User(UserAuth userAuth, UserInfo userInfo, UserRole userRole){
        this.userAuth = userAuth;
        this.userInfo = userInfo;
        userRoles.add(userRole);
    }


}
