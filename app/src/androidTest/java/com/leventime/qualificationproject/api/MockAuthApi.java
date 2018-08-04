package com.leventime.qualificationproject.api;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.features.login.data.LoginRequestEntity;
import com.leventime.qualificationproject.features.login.data.LoginResponseEntity;
import com.leventime.qualificationproject.features.login.data.UserInfoResponseEntity;

import io.reactivex.Single;
import retrofit2.mock.BehaviorDelegate;

/**
 * Provide mock auth api
 *
 * @author kv
 */
public class MockAuthApi implements AuthApi{

    private final BehaviorDelegate<AuthApi> mBehaviorDelegate;

    public MockAuthApi(@NonNull BehaviorDelegate<AuthApi> aBehaviorDelegate){
        mBehaviorDelegate = aBehaviorDelegate;
    }

    @NonNull
    @Override
    public Single<LoginResponseEntity> login(@NonNull final LoginRequestEntity aLoginRequestEntity){
        return mBehaviorDelegate.returningResponse(null).login(aLoginRequestEntity);
    }

    @NonNull
    @Override
    public Single<UserInfoResponseEntity> getUserInfo(){
        return mBehaviorDelegate.returningResponse(null).getUserInfo();
    }
}
