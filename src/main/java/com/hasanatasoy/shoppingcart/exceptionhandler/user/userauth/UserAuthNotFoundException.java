package com.hasanatasoy.shoppingcart.exceptionhandler.user.userauth;

public class UserAuthNotFoundException extends RuntimeException {

    public UserAuthNotFoundException(String email){
        super("User Not Found with email: "+email);
    }
    public UserAuthNotFoundException(Long id){ super("User Not Found with id: "+id);}
}
