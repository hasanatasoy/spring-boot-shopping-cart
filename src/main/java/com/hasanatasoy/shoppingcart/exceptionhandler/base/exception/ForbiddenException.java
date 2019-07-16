package com.hasanatasoy.shoppingcart.exceptionhandler.base.exception;

public class ForbiddenException extends RuntimeException {

    public ForbiddenException(String message) {
        super(message);
    }
}
