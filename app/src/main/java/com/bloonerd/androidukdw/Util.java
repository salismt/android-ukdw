package com.bloonerd.androidukdw;

import java.util.regex.Pattern;

public class Util {

    private static final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

    public static boolean isValidEmail(String email) {
        final Pattern emailPattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        return emailPattern.matcher(email).find();
    }

}
