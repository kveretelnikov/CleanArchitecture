package com.leventime.qualificationproject.features.splash.di;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.features.splash.presentation.SplashActivity;

import dagger.Subcomponent;

/**
 * Splash component
 *
 * @author kv
 */
@SplashScope
@Subcomponent(modules = {SplashModule.class})
public interface SplashComponent{

    void inject(@NonNull SplashActivity aActivity);

    /**
     * Splash component builder
     */
    @Subcomponent.Builder
    interface Builder{

        @NonNull
        Builder splashModule(@NonNull SplashModule aModule);

        @NonNull
        SplashComponent build();
    }
}
