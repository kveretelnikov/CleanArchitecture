package com.leventime.qualificationproject.api;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.features.login.data.LoginRequestEntity;
import com.leventime.qualificationproject.features.login.data.LoginResponseEntity;
import com.leventime.qualificationproject.features.login.data.UserInfoResponseEntity;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Describe auth api
 *
 * @author kv
 */
public interface AuthApi{

    /**
     * Login
     *
     * @param aLoginRequestEntity login data
     * @return login response data
     */
    @POST("auth/login")
    @NonNull
    Single<LoginResponseEntity> login(@Body @NonNull LoginRequestEntity aLoginRequestEntity);

    /**
     * Get user info
     *
     * @return user info
     */
    @GET("user")
    @NonNull
    Single<UserInfoResponseEntity> getUserInfo();
}
