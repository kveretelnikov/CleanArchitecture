package com.leventime.qualificationproject.features.login.presentation;

import android.support.annotation.Nullable;

/**
 * Validate login fields
 *
 * @author kv
 */
public interface LoginValidator{

    /**
     * Validate login email
     *
     * @param aEmail email
     * @return true if value validated
     */
    boolean validateLoginEmail(@Nullable String aEmail);

    /**
     * Validate login password
     *
     * @param aPassword password
     * @return true if value validated
     */
    boolean validateLoginPassword(@Nullable String aPassword);
}
