package com.hasanatasoy.shoppingcart.exceptionhandler;

import com.hasanatasoy.shoppingcart.exceptionhandler.base.exception.BadRequestExcepiton;
import com.hasanatasoy.shoppingcart.exceptionhandler.base.exception.ForbiddenException;
import com.hasanatasoy.shoppingcart.exceptionhandler.user.UserNotFoundException;
import com.hasanatasoy.shoppingcart.exceptionhandler.user.userauth.UserAuthNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExcepitonHandler {

    @ExceptionHandler({UserNotFoundException.class, UserAuthNotFoundException.class})
    public ResponseEntity<ErrorResponse> notFoundException(Exception exception){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestExcepiton.class)
    public ResponseEntity<ErrorResponse> badRequestException(Exception exception){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse> forbiddenException(Exception exception){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN.value(), exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }
}
