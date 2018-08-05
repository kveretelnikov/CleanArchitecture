package com.leventime.qualificationproject.features.login.domain;

import com.leventime.qualificationproject.features.login.data.LoginRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Single;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test {@link LoginInteractor}
 *
 * @author kv
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class LoginInteractorTest{

    @Mock
    private LoginRepository mRepository;

    private LoginInteractor mInteractor;

    @Before
    public void setUp() throws Exception{
        mInteractor = new LoginInteractorImpl(mRepository);
    }

    @Test
    public void setEmail(){
        String email = "user@user.ru";
        mInteractor.setEmail(email);
        assertEquals(email, mInteractor.getLoginData().getEmail());
    }

    @Test
    public void setPassword(){
        String password = "password";
        mInteractor.setPassword(password);
        assertEquals(password, mInteractor.getLoginData().getPassword());
    }

    @Test
    public void getLoginData(){
        String email = "user@user.ru";
        String password = "password";
        mInteractor.setEmail(email);
        mInteractor.setPassword(password);
        LoginDomain loginData = mInteractor.getLoginData();
        assertEquals(email, loginData.getEmail());
        assertEquals(password, loginData.getPassword());
    }

    @Test
    public void loginSuccess(){
        String token = "token";
        LoginResponseDomain loginResponseDomain = new LoginResponseDomain(token);
        when(mRepository.login(any(LoginDomain.class))).thenReturn(Single.just(loginResponseDomain));
        long id = 1;
        String avatarUrl = "url";
        String firstName = "first";
        String lastName = "last";
        UserInfoDomain userInfoDomain = new UserInfoDomain(id, avatarUrl, firstName, lastName);
        when(mRepository.getUserInfo()).thenReturn(Single.just(userInfoDomain));
        mInteractor.login()
                .test()
                .assertComplete()
                .assertNoErrors()
                .assertNoValues();
        verify(mRepository).login(any(LoginDomain.class));
        verify(mRepository).saveToken(anyString());
        verify(mRepository).getUserInfo();
        verify(mRepository).saveUserInfo(any(UserInfoDomain.class));
        verify(mRepository, never()).clearLoginData();
    }

    @Test
    public void loginFail(){
        RuntimeException exception = new RuntimeException();
        when(mRepository.login(any(LoginDomain.class))).thenReturn(Single.error(exception));
        mInteractor.login()
                .test()
                .assertNotComplete()
                .assertError(exception)
                .assertNoValues();
        verify(mRepository).login(any(LoginDomain.class));
        verify(mRepository).clearLoginData();
    }
}