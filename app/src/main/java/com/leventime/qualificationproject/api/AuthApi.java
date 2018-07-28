package com.leventime.qualificationproject.api;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.features.login.data.LoginEntity;
import com.leventime.qualificationproject.features.login.data.LoginResponseEntity;
import com.leventime.qualificationproject.features.login.data.UserInfoEntity;

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
     * @param aLoginEntity login data
     * @return login response data
     */
    @POST("auth/login")
    @NonNull
    Single<LoginResponseEntity> login(@Body @NonNull LoginEntity aLoginEntity);

    /**
     * Get user info
     *
     * @return user info
     */
    @GET("user")
    @NonNull
    Single<UserInfoEntity> getUserInfo();
}
