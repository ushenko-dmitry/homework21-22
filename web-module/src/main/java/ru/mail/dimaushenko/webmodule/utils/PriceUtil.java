package ru.mail.dimaushenko.webmodule.utils;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PriceUtil {

    private static final String PRICE_REGEX = "^\\d{1,}([.]\\d{1,2}){0,1}$";

    public static boolean isValid(String price) {
        Pattern pattern = Pattern.compile(PRICE_REGEX);
        Matcher matcher = pattern.matcher(price);
        return matcher.find();
    }

    public static BigDecimal getPrice(String price) {
        return new BigDecimal(price);
    }

}
