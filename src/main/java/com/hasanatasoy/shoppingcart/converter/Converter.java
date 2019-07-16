package com.hasanatasoy.shoppingcart.converter;

public interface Converter<T, R> {
    R convert(T t);
}
