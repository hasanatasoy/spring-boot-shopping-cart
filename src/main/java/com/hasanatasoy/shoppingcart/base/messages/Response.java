package com.hasanatasoy.shoppingcart.base.messages;

import lombok.*;

@Getter
@Setter
@Builder
public class Response<T> {

    private final int statusCode = 200;
    private final boolean isSuccess = true;
    private String message;
    private T result;
}
