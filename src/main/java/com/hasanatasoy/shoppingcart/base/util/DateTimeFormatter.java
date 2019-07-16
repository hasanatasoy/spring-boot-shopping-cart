package com.hasanatasoy.shoppingcart.base.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeFormatter {

    public static String generateDateAs(String pattern){
        return new SimpleDateFormat(pattern).format(new Date());
    }
}
