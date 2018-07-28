package com.leventime.qualificationproject.base.core.presentation;

import android.support.annotation.Nullable;

import com.google.common.base.Strings;
import com.leventime.qualificationproject.features.login.presentation.LoginValidator;
import com.leventime.qualificationproject.util.Patterns;

/**
 * Manage validation in app
 *
 * @author kv
 */
public class Validator implements LoginValidator{

    @Override
    public boolean validateLoginEmail(@Nullable final String aEmail){
        return aEmail != null && Patterns.EMAIL_PATTERN_VERIFY.matcher(aEmail).matches();
    }

    @Override
    public boolean validateLoginPassword(@Nullable final String aPassword){
        return !Strings.isNullOrEmpty(aPassword);
    }
}
