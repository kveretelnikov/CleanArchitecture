package com.leventime.qualificationproject.base.di;

import javax.inject.Singleton;

import dagger.Component;

/**
 * App component
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent{

}