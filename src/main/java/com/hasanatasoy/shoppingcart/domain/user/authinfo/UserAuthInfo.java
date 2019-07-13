package com.hasanatasoy.shoppingcart.domain.user.authinfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserAuthInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;

    public UserAuthInfo(String email, String password){
        this.email = email;
        this.password = password;
    }

}
