package com.leventime.qualificationproject.features.login.data;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.api.AuthApi;
import com.leventime.qualificationproject.base.core.data.BaseRepositoryImpl;
import com.leventime.qualificationproject.base.core.data.PreferenceManager;
import com.leventime.qualificationproject.base.core.data.ResourceManager;
import com.leventime.qualificationproject.base.room.dao.UserDao;
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
    private final UserDao mUserDao;

    /**
     * @param aResourceManager resource manager
     * @param aPreferenceManager preference manager
     * @param aLoginApi login api
     * @param aUserDao user dao
     */
    public LoginRepositoryImpl(@NonNull final ResourceManager aResourceManager,
                               @NonNull PreferenceManager aPreferenceManager,
                               @NonNull AuthApi aLoginApi,
                               @NonNull UserDao aUserDao){
        super(aResourceManager);
        mPreferenceManager = aPreferenceManager;
        mAuthApi = aLoginApi;
        mUserDao = aUserDao;
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
        mUserDao.saveUser(LoginDomainMapper.mapUserInfoToEntity(aUserInfoDomain));
    }

    @Override
    public void clearLoginData(){
        mPreferenceManager.clearAll();
    }
}
