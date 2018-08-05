package com.leventime.qualificationproject.features.login.data;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.base.core.data.BaseRepository;
import com.leventime.qualificationproject.features.login.domain.LoginDomain;
import com.leventime.qualificationproject.features.login.domain.LoginResponseDomain;
import com.leventime.qualificationproject.features.login.domain.UserInfoDomain;

import io.reactivex.Single;

/**
 * Provide login data
 *
 * @author kv
 */
public interface LoginRepository extends BaseRepository{

    /**
     * Login
     *
     * @param aLoginDomain login data
     * @return token
     */
    @NonNull
    Single<LoginResponseDomain> login(@NonNull LoginDomain aLoginDomain);

    /**
     * Save token
     *
     * @param aToken token
     */
    void saveToken(@NonNull String aToken);

    /**
     * Get user info
     *
     * @return user info
     */
    @NonNull
    Single<UserInfoDomain> getUserInfo();

    /**
     * Save user info
     *
     * @param aUserInfoDomain user nfo
     */
    void saveUserInfo(@NonNull UserInfoDomain aUserInfoDomain);

    /**
     * Clear login data
     */
    void clearLoginData();
}