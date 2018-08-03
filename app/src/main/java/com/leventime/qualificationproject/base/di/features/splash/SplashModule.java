package com.leventime.qualificationproject.base.di.features.splash;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.base.resources.PreferenceManager;
import com.leventime.qualificationproject.features.splash.data.SplashRepository;
import com.leventime.qualificationproject.features.splash.data.SplashRepositoryImpl;
import com.leventime.qualificationproject.features.splash.domain.SplashInteractor;
import com.leventime.qualificationproject.features.splash.domain.SplashInteractorImpl;
import com.leventime.qualificationproject.features.splash.presentation.SplashPresenter;
import com.leventime.qualificationproject.features.splash.presentation.SplashPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Splash module
 *
 * @author kv
 */
@SplashScope
@Module
public class SplashModule{

    @SplashScope
    @Provides
    @NonNull
    SplashPresenter providesPresenter(@NonNull SplashInteractor aInteractor){
        return new SplashPresenterImpl(aInteractor);
    }

    @SplashScope
    @Provides
    @NonNull
    SplashInteractor providesInteractor(@NonNull SplashRepository aRepository){
        return new SplashInteractorImpl(aRepository);
    }

    @SplashScope
    @Provides
    @NonNull
    SplashRepository providesRepository(@NonNull PreferenceManager aPreferenceManager){
        return new SplashRepositoryImpl(aPreferenceManager);
    }

}
