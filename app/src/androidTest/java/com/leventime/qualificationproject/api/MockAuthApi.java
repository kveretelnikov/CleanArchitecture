package com.leventime.qualificationproject.api;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.features.login.data.LoginRequestEntity;
import com.leventime.qualificationproject.features.login.data.LoginResponseEntity;
import com.leventime.qualificationproject.features.login.data.UserInfoResponseEntity;
import com.leventime.qualificationproject.util.MockResponses;

import io.reactivex.Single;
import retrofit2.mock.BehaviorDelegate;

import static com.leventime.qualificationproject.features.login.presentation.LoginActivityTest.EMAIL;
import static com.leventime.qualificationproject.features.login.presentation.LoginActivityTest.PASSWORD;

/**
 * Provide mock auth api
 *
 * @author kv
 */
public class MockAuthApi implements AuthApi{

    private final BehaviorDelegate<AuthApi> mBehaviorDelegate;

    /**
     * @param aBehaviorDelegate behavior delegate
     */
    public MockAuthApi(@NonNull BehaviorDelegate<AuthApi> aBehaviorDelegate){
        mBehaviorDelegate = aBehaviorDelegate;
    }

    @NonNull
    @Override
    public Single<LoginResponseEntity> login(@NonNull final LoginRequestEntity aLoginRequestEntity){
        if(EMAIL.equals(aLoginRequestEntity.getEmail()) && PASSWORD.equals(aLoginRequestEntity.getPassword())){
            return mBehaviorDelegate.returningResponse(MockResponses.getResponse("login_200_response.json", LoginResponseEntity.class))
                    .login(aLoginRequestEntity);
        } else{
            return mBehaviorDelegate.returningResponse(MockResponses.getResponse("login_400_response.json", ApiError.class))
                    .login(aLoginRequestEntity);
        }
    }

    @NonNull
    @Override
    public Single<UserInfoResponseEntity> getUserInfo(){
        return mBehaviorDelegate.returningResponse(MockResponses.getResponse("user_200_response.json", UserInfoResponseEntity.class))
                .getUserInfo();
    }
}
