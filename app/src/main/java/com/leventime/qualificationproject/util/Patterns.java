package com.leventime.qualificationproject.util;

import java.util.regex.Pattern;

/**
 * Contain patterns for validate fields
 *
 * @author kv
 */
public final class Patterns{

    private Patterns(){}

    /**
     * Pattern to verify that the email corresponds to the format example@example.subdomen.ru
     */
    public static final Pattern EMAIL_PATTERN_VERIFY = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


}
