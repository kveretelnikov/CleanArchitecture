package com.leventime.qualificationproject.features.login.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.leventime.qualificationproject.base.core.domain.BaseInteractor;
import com.leventime.qualificationproject.features.login.presentation.states.LoginBaseState;

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

    /**
     * Get current state
     *
     * @return state
     */
    @NonNull
    LoginBaseState getState();

    /**
     * Set state
     *
     * @param aState state
     */
    void setState(@NonNull LoginBaseState aState);
}
