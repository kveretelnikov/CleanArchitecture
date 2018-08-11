package com.leventime.qualificationproject.features.login.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.leventime.qualificationproject.base.core.domain.BaseInteractorImpl;
import com.leventime.qualificationproject.base.core.presentation.Cache;
import com.leventime.qualificationproject.features.login.data.LoginRepository;
import com.leventime.qualificationproject.features.login.presentation.states.LoginBaseState;
import com.leventime.qualificationproject.features.login.presentation.states.LoginInitState;

import io.reactivex.Completable;

/**
 * Provide login data
 *
 * @author kv
 */
public class LoginInteractorImpl extends BaseInteractorImpl<LoginRepository> implements LoginInteractor{

    private final Cache mCache;
    private LoginDomain mLoginDomain = new LoginDomain();

    /**
     * @param aRepository repository
     * @param aCache cache
     */
    public LoginInteractorImpl(@NonNull LoginRepository aRepository, @NonNull Cache aCache){
        super(aRepository);
        mCache = aCache;
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
        return mRepository.login(mLoginDomain)
                .doOnSuccess(aLoginResponseDomain -> mRepository.saveToken(aLoginResponseDomain.getToken()))
                .flatMap(aLoginResponseDomain -> mRepository.getUserInfo())
                .doOnSuccess(mRepository::saveUserInfo)
                .doOnError(aThrowable -> {
                    mRepository.clearLoginData();
                })
                .toCompletable();
    }

    @NonNull
    @Override
    public LoginBaseState getState(){
        if(mCache.isCacheExist()){
            return (LoginBaseState) mCache.getCacheData();
        }
        return new LoginInitState();
    }

    @Override
    public void setState(@NonNull final LoginBaseState aState){
        mCache.saveCacheData(aState);
    }

}
