package com.leventime.qualificationproject.features.splash.data;

import android.support.annotation.NonNull;

import com.google.common.base.Strings;
import com.leventime.qualificationproject.base.resources.PreferenceManager;
import com.leventime.qualificationproject.features.splash.SplashContract;

import io.reactivex.Single;

/**
 * Splash repository
 *
 * @author kv
 */
public class SplashRepository implements SplashContract.Repository{

    private final PreferenceManager mPreferenceManager;

    /**
     * @param aPreferenceManager preference manager
     */
    public SplashRepository(@NonNull PreferenceManager aPreferenceManager){
        mPreferenceManager = aPreferenceManager;
    }

    @Override
    public Single<Boolean> isUserLogged(){
        return Single.fromCallable(() -> !Strings.isNullOrEmpty(mPreferenceManager.getToken()));
    }
}
