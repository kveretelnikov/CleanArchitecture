package com.leventime.qualificationproject.base.network;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Used to build api instances to use for network calls
 */
public class ApiClient{

    private OkHttpClient.Builder mOkBuilder;
    private Retrofit.Builder mAdapterBuilder;

    public ApiClient(){
        createAdapterBuilder();
    }

    /**
     * Create api
     *
     * @param aApiClass api class
     * @param <S> class mType
     * @return api instance
     */
    public <S> S createApi(Class<S> aApiClass){
        return mAdapterBuilder
                .client(mOkBuilder.build())
                .build()
                .create(aApiClass);
    }

    /**
     * Get adapter builder
     *
     * @return adapter builder
     */
    public Retrofit.Builder getAdapterBuilder(){
        return mAdapterBuilder;
    }

    /**
     * Get okhttp builder
     *
     * @return okhttp builder
     */
    @NonNull
    public OkHttpClient.Builder getOkBuilder(){
        return mOkBuilder;
    }

    /**
     * Create adapter builder
     */
    private void createAdapterBuilder(){
        mOkBuilder = new OkHttpClient.Builder()
                .connectTimeout(BuildConfig.CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .readTimeout(BuildConfig.NETWORK_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .writeTimeout(BuildConfig.NETWORK_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        mAdapterBuilder = new Retrofit
                .Builder();
    }
}