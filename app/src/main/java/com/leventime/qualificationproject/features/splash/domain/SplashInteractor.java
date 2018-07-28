package com.leventime.qualificationproject.features.splash.domain;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.features.splash.SplashContract;

import io.reactivex.Single;

/**
 * Provide splash data
 *
 * @author kv
 */
public class SplashInteractor implements SplashContract.Interactor{

    private final SplashContract.Repository mRepository;

    /**
     * @param aRepository repository
     */
    public SplashInteractor(@NonNull SplashContract.Repository aRepository){
        mRepository = aRepository;
    }

    @NonNull
    @Override
    public Single<Boolean> isUserLogged(){
        return mRepository.isUserLogged();
    }
}
