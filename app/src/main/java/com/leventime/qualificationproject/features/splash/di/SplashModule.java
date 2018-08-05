package com.leventime.qualificationproject.features.splash.di;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.base.core.data.PreferenceManager;
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

    @NonNull
    @SplashScope
    @Provides
    SplashPresenter providesPresenter(@NonNull SplashInteractor aInteractor){
        return new SplashPresenterImpl(aInteractor);
    }

    @NonNull
    @SplashScope
    @Provides
    SplashInteractor providesInteractor(@NonNull SplashRepository aRepository){
        return new SplashInteractorImpl(aRepository);
    }

    @NonNull
    @SplashScope
    @Provides
    SplashRepository providesRepository(@NonNull PreferenceManager aPreferenceManager){
        return new SplashRepositoryImpl(aPreferenceManager);
    }

}
