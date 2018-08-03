package com.leventime.qualificationproject.features.splash.domain;

import android.support.annotation.NonNull;

import io.reactivex.Single;

/**
 * Provide splash data
 *
 * @author kv
 */
public interface SplashInteractor{

    /**
     * Check that user logged
     *
     * @return true if user logged
     */
    @NonNull
    Single<Boolean> isUserLogged();
}
