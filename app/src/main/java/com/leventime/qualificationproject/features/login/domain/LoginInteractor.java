package com.leventime.qualificationproject.features.login.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.leventime.qualificationproject.base.core.domain.BaseInteractor;

import io.reactivex.Completable;

/**
 * Provide login data
 *
 * @author kv
 */
public interface LoginInteractor extends BaseInteractor{

    /**
     * Set email
     *
     * @param aEmail email
     */
    void setEmail(@Nullable String aEmail);

    /**
     * Set password
     *
     * @param aPassword password
     */
    void setPassword(@Nullable String aPassword);

    /**
     * Get login data
     *
     * @return login data
     */
    @NonNull
    LoginDomain getLoginData();

    /**
     * Login
     *
     * @return success
     */
    @NonNull
    Completable login();
}
