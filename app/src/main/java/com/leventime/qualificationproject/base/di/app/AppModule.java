package com.leventime.qualificationproject.base.di.app;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.leventime.qualificationproject.base.resources.PreferenceManager;
import com.leventime.qualificationproject.base.resources.ResourceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Application-wide module
 */
@Module
public class AppModule{

    private Application mApplication;

    public AppModule(@NonNull Application aApplication){
        this.mApplication = aApplication;
    }

    @Provides
    @Singleton
    Application providesApplication(){
        return mApplication;
    }

    @Provides
    @Singleton
    Context providesContext(){
        return mApplication;
    }

    @Provides
    @Singleton
    PreferenceManager providesPreferenceManager(@NonNull Context aContext){
        return new PreferenceManager(aContext);
    }

    @Provides
    @Singleton
    ResourceManager providesResourceManager(@NonNull Context aContext){
        return new ResourceManager(aContext);
    }
}