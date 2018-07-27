package com.leventime.qualificationproject.features.login.data;

import android.support.annotation.NonNull;

import io.reactivex.Single;

/**
 * Describe login api
 *
 * @author kv
 */
public interface LoginApi{

    /**
     * Login
     *
     * @param aLoginEntity login data
     * @return login response data
     */
    @NonNull
    Single<LoginResponseEntity> login(@NonNull LoginEntity aLoginEntity);

    /**
     * Get user info
     *
     * @return user info
     */
    @NonNull
    Single<UserInfoEntity> getUserInfo();
}
