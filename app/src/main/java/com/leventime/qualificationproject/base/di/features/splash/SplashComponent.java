package com.leventime.qualificationproject.base.di.features.splash;

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

    void inject(SplashActivity aActivity);

    /**
     * Splash component builder
     */
    @Subcomponent.Builder
    interface Builder{

        Builder splashModule(SplashModule aModule);

        SplashComponent build();
    }
}
