package com.hasanatasoy.shoppingcart.domain.user.authinfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "UserAuthInformation")
@Getter
@Setter
public class UserAuthInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String email;
    private String password;

    public UserAuthInfo(String email, String password){
        this.email = email;
        this.password = password;
    }

}
