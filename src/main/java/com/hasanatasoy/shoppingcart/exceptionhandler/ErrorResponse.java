package com.hasanatasoy.shoppingcart.exceptionhandler;

import com.hasanatasoy.shoppingcart.base.util.DateTimeFormatter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

    private int statusCode;
    private final boolean isSuccess = false;
    private String message;
    private String errorTime;

    public ErrorResponse(int statusCode, String message){
        this.statusCode = statusCode;
        this.message = message;
        errorTime = DateTimeFormatter.generateDateAs("yyyy/mm/dd hh:mm:ss");
    }
}
