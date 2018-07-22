package com.leventime.qualificationproject.base.di;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

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
    @NonNull
    Application application(){
        return mApplication;
    }

    @Provides
    @Singleton
    @NonNull
    Context context(){
        return mApplication;
    }
}