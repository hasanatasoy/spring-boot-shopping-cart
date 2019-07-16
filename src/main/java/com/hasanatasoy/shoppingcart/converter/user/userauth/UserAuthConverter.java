package com.hasanatasoy.shoppingcart.converter.user.userauth;

import com.hasanatasoy.shoppingcart.converter.Converter;
import com.hasanatasoy.shoppingcart.domain.user.authinfo.UserAuth;
import com.hasanatasoy.shoppingcart.dto.user.userauth.UserAuthDTO;

public class UserAuthConverter implements Converter<UserAuth, UserAuthDTO> {

    @Override
    public UserAuthDTO convert(UserAuth userAuth) {
        return new UserAuthDTO(userAuth.getId(), userAuth.getEmail(), userAuth.getPassword());
    }
}
