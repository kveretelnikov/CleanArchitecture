package com.leventime.qualificationproject.features.login.presentation.states;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.features.login.presentation.LoginView;

/**
 * Describe login init state
 *
 * @author kv
 */
public class LoginInitState extends LoginBaseState{

    @NonNull
    @Override
    public String getName(){
        return getType().name();
    }

    @NonNull
    @Override
    public LoginStateType getType(){
        return LoginStateType.INIT;
    }

    @Override
    public void invalidateView(@NonNull final LoginView aView){
        super.invalidateView(aView);
        LoginOwner owner = getOwner();
        boolean hasErrors = owner.getLoginValidationErrors().hasErrors();
        owner.getView().setLoginEnabled(!hasErrors);
    }
}
