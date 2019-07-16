package com.hasanatasoy.shoppingcart.dto.user.userauth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserAuthDTO {

    private Long id;
    private String email;
    private String password;
}
