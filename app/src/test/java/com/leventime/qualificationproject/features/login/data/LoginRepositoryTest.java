package com.leventime.qualificationproject.features.login.data;

import com.leventime.qualificationproject.api.AuthApi;
import com.leventime.qualificationproject.base.core.data.PreferenceManagerImpl;
import com.leventime.qualificationproject.base.core.data.ResourceManagerImpl;
import com.leventime.qualificationproject.base.room.dao.UserDao;
import com.leventime.qualificationproject.base.room.entity.login.UserEntity;
import com.leventime.qualificationproject.features.login.domain.LoginDomain;
import com.leventime.qualificationproject.features.login.domain.UserInfoDomain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test {@link LoginRepositoryImpl}
 *
 * @author kv
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class LoginRepositoryTest{

    @Mock
    ResourceManagerImpl mResourceManager;
    @Mock
    PreferenceManagerImpl mPreferenceManager;
    @Mock
    AuthApi mApi;
    @Mock
    UserDao mDao;

    private LoginRepositoryImpl mRepository;

    @Before
    public void setUp() throws Exception{
        mRepository = new LoginRepositoryImpl(mResourceManager, mPreferenceManager, mApi, mDao);
    }

    @Test
    public void login(){
        LoginDomain loginDomain = new LoginDomain();
        loginDomain.setEmail("email");
        loginDomain.setPassword("password");
        String token = "token";
        LoginResponseEntity loginResponseEntity = new LoginResponseEntity(token);
        when(mApi.login(any(LoginRequestEntity.class))).thenReturn(Single.just(loginResponseEntity));
        mRepository.login(loginDomain)
                .test();
        verify(mApi).login(any(LoginRequestEntity.class));
    }

    @Test
    public void saveToken(){
        mRepository.saveToken("token");
        verify(mPreferenceManager).saveToken(anyString());
    }

    @Test
    public void getUserInfo(){
        long id = 1;
        String avatarUrl = "url";
        String firstName = "first";
        String lastName = "last";
        UserInfoResponseEntity userInfoEntity = new UserInfoResponseEntity(id, avatarUrl, firstName, lastName);
        when(mApi.getUserInfo()).thenReturn(Single.just(userInfoEntity));
        mRepository.getUserInfo()
                .test();
        verify(mApi).getUserInfo();
    }

    @Test
    public void saveUserInfo(){
        long id = 1;
        String avatarUrl = "url";
        String firstName = "first";
        String lastName = "last";
        UserInfoDomain userInfoDomain = new UserInfoDomain(id, avatarUrl, firstName, lastName);
        mRepository.saveUserInfo(userInfoDomain);
        verify(mDao).saveUser(any(UserEntity.class));
    }

    @Test
    public void clearLoginData(){
        mRepository.clearLoginData();
        verify(mPreferenceManager).clearAll();
    }
}