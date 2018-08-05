package com.leventime.qualificationproject.features.splash.domain;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.features.splash.data.SplashRepository;

import io.reactivex.Single;

/**
 * Provide splash data
 *
 * @author kv
 */
public class SplashInteractorImpl implements SplashInteractor{

    private final SplashRepository mRepository;

    /**
     * @param aRepository repository
     */
    public SplashInteractorImpl(@NonNull SplashRepository aRepository){
        mRepository = aRepository;
    }

    @NonNull
    @Override
    public Single<Boolean> isUserLogged(){
        return mRepository.isUserLogged();
    }
}
