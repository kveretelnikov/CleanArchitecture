package com.leventime.qualificationproject.features.login.presentation;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.base.core.presentation.BasePresenter;
import com.leventime.qualificationproject.features.login.presentation.states.LoginBaseState;

/**
 *
 * Manage login view
 *
 * @author kv
 */
public interface LoginPresenter extends BasePresenter<LoginView>, LoginView.Actions{

    /**
     * Get state type
     *
     * @return state type
     */
    @NonNull
    LoginBaseState.LoginStateType getStateType();
}
