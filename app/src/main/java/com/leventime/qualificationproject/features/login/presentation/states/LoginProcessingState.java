package com.leventime.qualificationproject.features.login.presentation.states;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.features.login.presentation.LoginView;

/**
 * Describe login processing state
 *
 * @author kv
 */
public class LoginProcessingState extends LoginBaseState{

    @NonNull
    @Override
    public String getName(){
        return getType().name();
    }

    @NonNull
    @Override
    public LoginStateType getType(){
        return LoginStateType.PROCESSING;
    }

    @Override
    public void onEnter(@NonNull final LoginView aView){
        super.onEnter(aView);
        getOwner().login();
    }

    @Override
    public void invalidateView(@NonNull final LoginView aView){
        super.invalidateView(aView);
        LoginOwner owner = getOwner();
        boolean hasErrors = owner.getLoginValidationErrors().hasErrors();
        owner.getView().setLoginEnabled(!hasErrors);
    }
}
