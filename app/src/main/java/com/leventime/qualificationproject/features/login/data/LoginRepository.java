package com.leventime.qualificationproject.features.login.data;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.base.core.data.BaseRepository;
import com.leventime.qualificationproject.base.resources.PreferenceManager;
import com.leventime.qualificationproject.base.resources.ResourceManager;
import com.leventime.qualificationproject.features.login.LoginContract;
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
public class LoginRepository extends BaseRepository implements LoginContract.Repository{

    private final LoginApi mLoginApi;
    private final PreferenceManager mPreferenceManager;

    /**
     * @param aResourceManager resource manager
     * @param aLoginApi login api
     */
    public LoginRepository(@NonNull final ResourceManager aResourceManager,
                           @NonNull PreferenceManager aPreferenceManager,
                           @NonNull LoginApi aLoginApi){
        super(aResourceManager);
        mPreferenceManager = aPreferenceManager;
        mLoginApi = aLoginApi;
    }

    @NonNull
    @Override
    public Single<LoginResponseDomain> login(@NonNull final LoginDomain aLoginDomain){
        return mLoginApi.login(LoginDomainMapper.mapLoginToEntity(aLoginDomain))
                .map(LoginDomainMapper::mapLoginResponseFromEntity);
    }

    @Override
    public void saveToken(@NonNull final String aToken){
        mPreferenceManager.saveToken(aToken);
    }

    @NonNull
    @Override
    public Single<UserInfoDomain> getUserInfo(){
        return mLoginApi.getUserInfo()
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
