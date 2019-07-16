package com.hasanatasoy.shoppingcart.domain.user.role;

import com.hasanatasoy.shoppingcart.base.domain.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
@SequenceGenerator(name = "userrolegenerator", sequenceName = "user_role_seq")
@Getter
@Setter
@NoArgsConstructor
public class UserRole extends BaseModel {

    private RoleName roleName;

    public UserRole(RoleName roleName){
        this.roleName = roleName;
    }
}
