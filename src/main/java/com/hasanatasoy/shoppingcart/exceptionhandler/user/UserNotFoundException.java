package com.hasanatasoy.shoppingcart.exceptionhandler.user;


public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Long id) {
        super("User Not Found with id : "+id);
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
