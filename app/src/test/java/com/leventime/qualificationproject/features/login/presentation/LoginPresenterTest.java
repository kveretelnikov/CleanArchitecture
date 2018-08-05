package com.leventime.qualificationproject.features.login.presentation;

import com.google.common.base.Strings;
import com.leventime.qualificationproject.base.core.presentation.Validator;
import com.leventime.qualificationproject.features.login.domain.LoginDomain;
import com.leventime.qualificationproject.features.login.domain.LoginInteractor;
import com.leventime.qualificationproject.features.login.presentation.states.LoginInitState;
import com.leventime.qualificationproject.util.RxSchedulerRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Completable;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test {@link LoginPresenterImpl}
 *
 * @author kv
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class LoginPresenterTest{

    private static final String ERROR_MESSAGE = "error";
    @Rule
    public final RxSchedulerRule mOverrideSchedulersRule = new RxSchedulerRule();
    @Mock
    private LoginView mView;
    @Mock
    private LoginInteractor mInteractor;
    private final LoginValidator mValidator = new Validator();
    private LoginPresenterImpl mPresenter;

    @Before
    public void setUp(){
        when(mInteractor.getStringResource(anyInt())).thenReturn(ERROR_MESSAGE);
        mPresenter = new LoginPresenterImpl(mInteractor, mValidator, new LoginInitState());
        mPresenter.attachView(mView);
        reset(mView);
    }

    @After
    public void tearDown(){
        mPresenter.detachView();
    }

    @Test
    public void onEmailChangedFail(){
        String email = "user@user";
        LoginDomain loginDomain = new LoginDomain();
        loginDomain.setEmail(email);
        when(mInteractor.getLoginData()).thenReturn(loginDomain);
        assertFalse(Strings.isNullOrEmpty(mPresenter.onEmailChanged(email)));
        verify(mView).setLoginEnabled(anyBoolean());
    }

    @Test
    public void onEmailChangedSuccess(){
        String email = "user@user.ru";
        LoginDomain loginDomain = new LoginDomain();
        loginDomain.setEmail(email);
        when(mInteractor.getLoginData()).thenReturn(loginDomain);
        assertTrue(Strings.isNullOrEmpty(mPresenter.onEmailChanged(email)));
        verify(mView).setLoginEnabled(anyBoolean());
    }

    @Test
    public void onPasswordChangedFail(){
        String password = com.leventime.qualificationproject.util.Strings.EMPTY;
        LoginDomain loginDomain = new LoginDomain();
        loginDomain.setPassword(password);
        when(mInteractor.getLoginData()).thenReturn(loginDomain);
        assertFalse(Strings.isNullOrEmpty(mPresenter.onPasswordChanged(password)));
        verify(mView).setLoginEnabled(anyBoolean());
    }

    @Test
    public void onPasswordChangedSuccess(){
        String password = "password";
        LoginDomain loginDomain = new LoginDomain();
        loginDomain.setPassword(password);
        when(mInteractor.getLoginData()).thenReturn(loginDomain);
        assertTrue(Strings.isNullOrEmpty(mPresenter.onPasswordChanged(password)));
        verify(mView).setLoginEnabled(anyBoolean());
    }

    @Test
    public void onLoginClickedSuccess(){
        String email = "user@user.ru";
        String password = "password";
        LoginDomain loginDomain = new LoginDomain();
        loginDomain.setPassword(password);
        loginDomain.setEmail(email);
        when(mInteractor.getLoginData()).thenReturn(loginDomain);
        when(mInteractor.login()).thenReturn(Completable.complete());
        mPresenter.onLoginClicked();
        verify(mView).showLoadingProgress();
        verify(mView).hideLoadingProgress();
        verify(mView).navigateToMainView();
        verify(mView, never()).showError(anyString());
        verify(mView, never()).setLoginEnabled(anyBoolean());
    }

    @Test
    public void onLoginClickedFailed(){
        String email = "user@user.ru";
        String password = "password";
        LoginDomain loginDomain = new LoginDomain();
        loginDomain.setPassword(password);
        loginDomain.setEmail(email);
        when(mInteractor.getLoginData()).thenReturn(loginDomain);
        when(mInteractor.login()).thenReturn(Completable.error(new Exception()));
        mPresenter.onLoginClicked();
        verify(mView).showLoadingProgress();
        verify(mView).hideLoadingProgress();
        verify(mView).showError(anyString());
        verify(mView, never()).navigateToMainView();
        verify(mView, never()).setLoginEnabled(anyBoolean());
    }
}