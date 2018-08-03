package com.leventime.qualificationproject.features.login.presentation.states;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.base.core.presentation.states.BaseOwner;
import com.leventime.qualificationproject.features.login.presentation.LoginValidationErrors;
import com.leventime.qualificationproject.features.login.presentation.LoginView;

/**
 * Describe login owner
 *
 * @author kv
 */
public interface LoginOwner extends BaseOwner<LoginView, LoginBaseState>{

    /**
     * Get login validation errors
     *
     * @return login validation errors
     */
    @NonNull
    LoginValidationErrors getLoginValidationErrors();

    /**
     * Login
     */
    void login();
}
