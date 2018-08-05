package com.leventime.qualificationproject.base.di;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.leventime.qualificationproject.base.core.data.PreferenceManager;
import com.leventime.qualificationproject.base.core.data.PreferenceManagerImpl;
import com.leventime.qualificationproject.base.core.data.ResourceManager;
import com.leventime.qualificationproject.base.core.data.ResourceManagerImpl;
import com.leventime.qualificationproject.base.room.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Application-wide module
 *
 * @author kv
 */
@Module
public class AppModule{

    private Application mApplication;

    public AppModule(@NonNull Application aApplication){
        this.mApplication = aApplication;
    }

    @NonNull
    @Provides
    @Singleton
    Application providesApplication(){
        return mApplication;
    }

    @NonNull
    @Provides
    @Singleton
    Context providesContext(){
        return mApplication;
    }

    @NonNull
    @Provides
    @Singleton
    PreferenceManager providesPreferenceManager(@NonNull Context aContext){
        return new PreferenceManagerImpl(aContext);
    }

    @NonNull
    @Provides
    @Singleton
    ResourceManager providesResourceManager(@NonNull Context aContext){
        return new ResourceManagerImpl(aContext);
    }

    @NonNull
    @Provides
    @Singleton
    AppDatabase providesAppDatabase(@NonNull Context aContext){
        return AppDatabase.build(aContext);
    }
}