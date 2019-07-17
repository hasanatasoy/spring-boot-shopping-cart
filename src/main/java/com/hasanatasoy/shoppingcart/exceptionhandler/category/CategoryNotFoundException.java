package com.hasanatasoy.shoppingcart.exceptionhandler.category;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String categoryName) {
        super("Category not found with called: "+categoryName);
    }
}
