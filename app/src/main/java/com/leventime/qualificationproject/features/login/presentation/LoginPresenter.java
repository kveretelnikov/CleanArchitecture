package com.leventime.qualificationproject.features.login.presentation;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.base.core.presentation.BasePresenter;
import com.leventime.qualificationproject.features.login.LoginContract;
import com.leventime.qualificationproject.util.Strings;

/**
 * Manage login in app
 *
 * @author kv
 */
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter{

    private final LoginContract.Interactor mInteractor;
    private final LoginValidator mLoginValidator;

    public LoginPresenter(@NonNull LoginContract.Interactor aInteractor, @NonNull LoginValidator aLoginValidator){
        mInteractor = aInteractor;
        mLoginValidator = aLoginValidator;
    }

    @NonNull
    @Override
    public String onEmailChanged(@NonNull final String aEmail){
        if(mLoginValidator.validateLoginPassword(aEmail)){
            return Strings.EMPTY;
        } else{
            return Strings.EMPTY;
        }
    }

    @NonNull
    @Override
    public String onPasswordChanged(@NonNull final String aPassword){
        if(mLoginValidator.validateLoginPassword(aPassword)){
            return Strings.EMPTY;
        } else{
            return Strings.EMPTY;
        }
    }

    @Override
    public void onLoginClicked(){

    }
}
