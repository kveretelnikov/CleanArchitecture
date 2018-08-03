package com.leventime.qualificationproject.features.login.data;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.api.AuthApi;
import com.leventime.qualificationproject.base.core.data.BaseRepositoryImpl;
import com.leventime.qualificationproject.base.core.data.PreferenceManager;
import com.leventime.qualificationproject.base.core.data.ResourceManager;
import com.leventime.qualificationproject.features.login.domain.LoginDomain;
import com.leventime.qualificationproject.features.login.domain.LoginDomainMapper;
import com.leventime.qualificationproject.features.login.domain.LoginResponseDomain;
import com.leventime.qualificationproject.features.login.domain.UserInfoDomain;

import io.reactivex.Single;

/**
 * Provide login data
 *
 * @author kv
 */
public class LoginRepositoryImpl extends BaseRepositoryImpl implements LoginRepository{

    private final AuthApi mAuthApi;
    private final PreferenceManager mPreferenceManager;

    /**
     * @param aResourceManager resource manager
     * @param aLoginApi login api
     */
    public LoginRepositoryImpl(@NonNull final ResourceManager aResourceManager,
                               @NonNull PreferenceManager aPreferenceManager,
                               @NonNull AuthApi aLoginApi){
        super(aResourceManager);
        mPreferenceManager = aPreferenceManager;
        mAuthApi = aLoginApi;
    }

    @NonNull
    @Override
    public Single<LoginResponseDomain> login(@NonNull final LoginDomain aLoginDomain){
        return mAuthApi.login(LoginDomainMapper.mapLoginToEntity(aLoginDomain))
                .map(LoginDomainMapper::mapLoginResponseFromEntity);
    }

    @Override
    public void saveToken(@NonNull final String aToken){
        mPreferenceManager.saveToken(aToken);
    }

    @NonNull
    @Override
    public Single<UserInfoDomain> getUserInfo(){
        return mAuthApi.getUserInfo()
                .map(LoginDomainMapper::mapUserInfoFromEntity);
    }

    @Override
    public void saveUserInfo(@NonNull final UserInfoDomain aUserInfoDomain){
        //TODO kv 28/07/18: need add save to db
    }

    @Override
    public void clearLoginData(){
        mPreferenceManager.clearAll();
    }
}
