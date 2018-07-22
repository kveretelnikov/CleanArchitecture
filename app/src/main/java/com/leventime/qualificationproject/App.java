package com.leventime.qualificationproject;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.leventime.qualificationproject.base.di.AppComponent;
import com.leventime.qualificationproject.base.di.AppModule;
import com.leventime.qualificationproject.base.di.DaggerAppComponent;
import com.leventime.qualificationproject.base.di.NetworkModule;

import timber.log.Timber;

/**
 * Maintains global application stat
 *
 * @author kv
 */
public class App extends Application{

    private AppComponent mAppComponent;

    /**
     * Get application instance
     *
     * @param aContext context
     * @return application instance
     */
    @NonNull
    public static App get(@NonNull Context aContext){
        return (App) aContext.getApplicationContext();
    }

    @Override
    public void onCreate(){
        super.onCreate();
        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(BuildConfig.BASE_URL))
                .build();
    }

    /**
     * Get app component
     *
     * @return app component
     */
    @NonNull
    public AppComponent getAppComponent(){
        return mAppComponent;
    }
}
