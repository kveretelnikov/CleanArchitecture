package com.leventime.qualificationproject.features.login.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.leventime.qualificationproject.base.core.data.BaseInteractor;
import com.leventime.qualificationproject.features.login.LoginContract;

import io.reactivex.Completable;

/**
 * Provide login data
 *
 * @author kv
 */
public class LoginInteractor extends BaseInteractor<LoginContract.Repository> implements LoginContract.Interactor{

    LoginDomain mLoginDomain = new LoginDomain();

    public LoginInteractor(@NonNull LoginContract.Repository aRepository){
        super(aRepository);
    }

    @Override
    public void setEmail(@Nullable final String aEmail){
        mLoginDomain.setEmail(aEmail);
    }

    @Override
    public void setPassword(@Nullable final String aPassword){
        mLoginDomain.setPassword(aPassword);
    }

    @NonNull
    @Override
    public LoginDomain getLoginData(){
        return mLoginDomain.makeCopy(mLoginDomain);
    }

    @NonNull
    @Override
    public Completable login(){
        return null;
    }

}
