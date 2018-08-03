package com.leventime.qualificationproject.features.splash.data;

import android.support.annotation.NonNull;

import io.reactivex.Single;

/**
 * Provide splash data
 *
 * @author kv
 */
public interface SplashRepository{

    /**
     * Check that user logged
     *
     * @return true if user logged
     */
    @NonNull
    Single<Boolean> isUserLogged();
}
