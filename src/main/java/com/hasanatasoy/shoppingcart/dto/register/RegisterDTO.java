package com.hasanatasoy.shoppingcart.dto.register;

import com.hasanatasoy.shoppingcart.domain.user.UserGender;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegisterDTO {

    @NotNull
    @Email
    @Size(min = 11, max = 50)
    private String email;
    @NotNull
    @Size(min = 6, max = 25)
    private String password;
    @NotNull
    private UserGender userGender;

}
