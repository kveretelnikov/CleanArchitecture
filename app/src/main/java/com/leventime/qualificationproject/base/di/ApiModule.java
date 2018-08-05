package com.leventime.qualificationproject.base.di;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.api.AuthApi;
import com.leventime.qualificationproject.base.network.ApiClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provide api dependencies
 *
 * @author kv
 */
@Module
public class ApiModule{

    @NonNull
    @Provides
    @Singleton
    AuthApi loginApi(@NonNull ApiClient aClient){
        return aClient.createApi(AuthApi.class);
    }
}
