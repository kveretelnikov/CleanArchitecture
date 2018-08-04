package com.leventime.qualificationproject.base.di;

import android.content.Context;
import android.support.annotation.NonNull;

import com.leventime.qualificationproject.api.AuthApi;
import com.leventime.qualificationproject.api.MockAuthApi;
import com.leventime.qualificationproject.base.network.ApiClient;

import dagger.Module;

/**
 * Provide mock api module
 *
 * @author kv
 */
@Module
public class MockApiModule extends ApiModule{

    private final Context mContext;

    public MockApiModule(@NonNull Context aContext){
        mContext = aContext;
    }

    @NonNull
    @Override
    AuthApi loginApi(@NonNull final ApiClient aClient){
        return new MockAuthApi(mContext, aClient.createMockApiDelegate(AuthApi.class));
    }
}
