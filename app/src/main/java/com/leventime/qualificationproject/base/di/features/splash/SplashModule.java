package com.leventime.qualificationproject.base.di.features.splash;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.base.resources.PreferenceManager;
import com.leventime.qualificationproject.features.splash.SplashContract;
import com.leventime.qualificationproject.features.splash.data.SplashRepository;
import com.leventime.qualificationproject.features.splash.domain.SplashInteractor;
import com.leventime.qualificationproject.features.splash.presentation.SplashPageObject;
import com.leventime.qualificationproject.features.splash.presentation.SplashPresenter;

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
    SplashContract.PageObject providesPageObject(){
        return new SplashPageObject();
    }

    @SplashScope
    @Provides
    @NonNull
    SplashContract.Presenter providesPresenter(@NonNull SplashContract.Interactor aInteractor, @NonNull SplashContract.PageObject aPageObject){
        return new SplashPresenter(aPageObject, aInteractor);
    }

    @SplashScope
    @Provides
    @NonNull
    SplashContract.Interactor providesInteractor(@NonNull SplashContract.Repository aRepository){
        return new SplashInteractor(aRepository);
    }

    @SplashScope
    @Provides
    @NonNull
    SplashContract.Repository providesRepository(@NonNull PreferenceManager aPreferenceManager){
        return new SplashRepository(aPreferenceManager);
    }

}
