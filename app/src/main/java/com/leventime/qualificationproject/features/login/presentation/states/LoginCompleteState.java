package com.leventime.qualificationproject.features.login.presentation.states;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.features.login.presentation.LoginView;

/**
 * Describe login complete state
 *
 * @author kv
 */
public class LoginCompleteState extends LoginBaseState{

    @NonNull
    @Override
    public LoginStateType getType(){
        return LoginStateType.COMPLETE;
    }

    @Override
    public void onEnter(@NonNull final LoginView aView){
        super.onEnter(aView);
        aView.navigateToMainView();
    }
}
