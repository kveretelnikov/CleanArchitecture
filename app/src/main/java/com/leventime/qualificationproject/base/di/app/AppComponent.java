package com.leventime.qualificationproject.base.di.app;

import com.leventime.qualificationproject.features.login.di.LoginComponent;
import com.leventime.qualificationproject.features.splash.di.SplashComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * App component
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent{

    LoginComponent.Builder loginComponentBuilder();

    SplashComponent.Builder splashComponentBuilder();
}