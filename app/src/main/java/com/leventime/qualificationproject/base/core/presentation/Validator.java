package com.leventime.qualificationproject.base.core.presentation;

import android.support.annotation.Nullable;

import com.leventime.qualificationproject.features.login.presentation.LoginValidator;
import com.leventime.qualificationproject.util.Patterns;

import kotlin.text.StringsKt;

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
        return !StringsKt.isBlank(aPassword);
    }
}
