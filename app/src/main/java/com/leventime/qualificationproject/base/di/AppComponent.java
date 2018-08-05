package com.leventime.qualificationproject.base.di;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.App;
import com.leventime.qualificationproject.features.login.di.LoginComponent;
import com.leventime.qualificationproject.features.splash.di.SplashComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * App component
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, ApiModule.class})
public interface AppComponent{

    @NonNull
    LoginComponent.Builder loginComponentBuilder();

    @NonNull
    SplashComponent.Builder splashComponentBuilder();

    void inject(@NonNull App aApp);
}