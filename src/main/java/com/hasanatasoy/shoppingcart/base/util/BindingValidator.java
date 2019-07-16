package com.hasanatasoy.shoppingcart.base.util;

import com.hasanatasoy.shoppingcart.exceptionhandler.base.exception.BadRequestExcepiton;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@Service
public class BindingValidator {

        public static void validate(BindingResult bindingResult){

            if(bindingResult.hasErrors()){
                List<String> errors = new ArrayList<>();
                bindingResult.getFieldErrors().forEach(error -> {
                    errors.add(error.getField());
                });
                throw new BadRequestExcepiton(errors.toString());
            }
        }
}
