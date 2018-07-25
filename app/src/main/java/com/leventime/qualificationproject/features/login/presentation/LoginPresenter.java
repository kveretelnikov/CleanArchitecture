package com.leventime.qualificationproject.features.login.presentation;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.base.core.presentation.BasePresenter;
import com.leventime.qualificationproject.features.login.LoginContract;

/**
 * Manage login in app
 *
 * @author kv
 */
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter{

    private final LoginContract.Interactor mInteractor;

    public LoginPresenter(@NonNull LoginContract.Interactor aInteractor){
        mInteractor = aInteractor;
    }
}
