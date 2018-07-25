package com.leventime.qualificationproject.features.login.domain;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.features.login.LoginContract;

/**
 * Provide login data
 *
 * @author kv
 */
public class LoginInteractor implements LoginContract.Interactor{

    private final LoginContract.Repository mRepository;

    public LoginInteractor(@NonNull LoginContract.Repository aRepository){
        mRepository = aRepository;
    }
}
