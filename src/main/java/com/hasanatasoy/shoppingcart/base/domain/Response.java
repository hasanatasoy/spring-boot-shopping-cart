package com.hasanatasoy.shoppingcart.base.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Response<T> {

    private boolean isSuccess;
    private int statusCode;
    private String message;
    private T result;
}
