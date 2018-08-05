package com.leventime.qualificationproject;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.base.di.AppComponent;
import com.leventime.qualificationproject.base.di.AppModule;
import com.leventime.qualificationproject.base.di.DaggerAppComponent;
import com.leventime.qualificationproject.base.di.MockApiModule;
import com.leventime.qualificationproject.base.di.NetworkModule;

/**
 * Global app config for tests
 *
 * @author kv
 */
public class TestApp extends App{

    @Override
    public void onCreate(){
        super.onCreate();
    }

    @NonNull
    @Override
    protected AppComponent buildAppComponent(){
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(BuildConfig.BASE_URL))
                .apiModule(new MockApiModule())
                .build();
    }
}