package com.hasanatasoy.shoppingcart.base.messages.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RequestResetPassword {

    @NotNull
    @Size(min = 6, max = 26)
    private String password;
    @NotNull
    @Email
    @Size(min = 11, max = 50)
    private String email;
}
