package com.leventime.qualificationproject.base.di;

import android.content.Context;
import android.support.annotation.NonNull;

import com.leventime.qualificationproject.BuildConfig;
import com.leventime.qualificationproject.base.network.ApiClient;
import com.leventime.qualificationproject.base.network.exception.RxErrorHandlingCallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Provides network environment dependencies
 *
 * @author kv
 */
@Module(includes = {AppModule.class})
public class NetworkModule{

    private static final long HTTP_CACHE_SIZE_BYTES = 1024 * 1024 * 50L;
    private final String mBaseUrl;

    /**
     * @param aBaseUrl base api url
     */
    public NetworkModule(@NonNull String aBaseUrl){
        mBaseUrl = aBaseUrl;
    }

    /**
     * Create network client for call api methods
     **/
    @NonNull
    @Singleton
    @Provides
    ApiClient providesApiClient(@NonNull Cache aCache){
        ApiClient apiClient = new ApiClient();
        OkHttpClient.Builder okBuilder = apiClient.getOkBuilder();
        if(BuildConfig.DEBUG){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okBuilder.addInterceptor(interceptor);
        }
        okBuilder.retryOnConnectionFailure(true);
        okBuilder.followRedirects(true);
        okBuilder.followSslRedirects(true);
        okBuilder.cache(aCache);
        OkHttpClient okHttpClient = okBuilder.build();
        apiClient.getAdapterBuilder()
                .baseUrl(mBaseUrl)
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return apiClient;
    }

    /**
     * Provides http cache
     *
     * @param aContext application context
     * @return http cache
     */
    @NonNull
    @Provides
    @Singleton
    Cache httpCache(@NonNull Context aContext){
        return new Cache(aContext.getCacheDir(), HTTP_CACHE_SIZE_BYTES);
    }
}
