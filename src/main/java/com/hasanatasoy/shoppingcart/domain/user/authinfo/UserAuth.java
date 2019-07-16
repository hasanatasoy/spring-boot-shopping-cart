package com.hasanatasoy.shoppingcart.domain.user.authinfo;

import com.hasanatasoy.shoppingcart.base.domain.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_auth")
@SequenceGenerator(name = "userauthgenerator", sequenceName = "user_auth_seq")
@Getter
@Setter
@NoArgsConstructor
public class UserAuth extends BaseModel {

    private boolean accountEnabled = false;
    private String email;
    private String password;

    public UserAuth(String email, String password){
        this.email = email;
        this.password = password;
    }

}
