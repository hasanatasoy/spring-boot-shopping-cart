package com.hasanatasoy.shoppingcart.dto.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class LoginDTO {

    @NotNull
    @Email
    @Size(min = 11, max = 50)
    private String email;
    @NotNull
    @Size(min = 6, max = 25)
    private String password;
}
