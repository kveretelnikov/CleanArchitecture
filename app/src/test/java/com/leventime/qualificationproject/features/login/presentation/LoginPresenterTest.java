package com.leventime.qualificationproject.features.login.presentation;

import com.google.common.base.Strings;
import com.leventime.qualificationproject.base.core.presentation.Validator;
import com.leventime.qualificationproject.features.login.LoginContract;
import com.leventime.qualificationproject.features.login.domain.LoginDomain;
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
import static org.mockito.ArgumentMatchers.any;
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
    LoginContract.View mView;
    @Mock
    LoginContract.Interactor mInteractor;
    @Mock
    LoginContract.PageObject mPageObject;
    private final LoginValidator mValidator = new Validator();
    private LoginPresenterImpl mPresenter;

    @Before
    public void setUp(){
        when(mInteractor.getStringResource(anyInt())).thenReturn(ERROR_MESSAGE);
        mPresenter = new LoginPresenterImpl(mInteractor, mValidator, mPageObject);
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
        assertFalse(Strings.isNullOrEmpty(mPresenter.onEmailChanged(email)));
    }

    @Test
    public void onEmailChangedSuccess(){
        String email = "user@user.ru";
        assertTrue(Strings.isNullOrEmpty(mPresenter.onEmailChanged(email)));
    }

    @Test
    public void onPasswordChangedFail(){
        String password = com.leventime.qualificationproject.util.Strings.EMPTY;
        assertFalse(Strings.isNullOrEmpty(mPresenter.onPasswordChanged(password)));
    }

    @Test
    public void onPasswordChangedSuccess(){
        String password = "password";
        assertTrue(Strings.isNullOrEmpty(mPresenter.onPasswordChanged(password)));
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
        verify(mPageObject).showLoadingProgress();
        verify(mPageObject).hideLoadingProgress();
        verify(mPageObject).navigateToMainView();
        verify(mPageObject, never()).showError(anyString());
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
        verify(mPageObject).showLoadingProgress();
        verify(mPageObject).hideLoadingProgress();
        verify(mPageObject).showError(anyString());
        verify(mPageObject, never()).navigateToMainView();
    }

    @Test
    public void onLoginClickedWithValidationError(){
        String email = com.leventime.qualificationproject.util.Strings.EMPTY;
        String password = com.leventime.qualificationproject.util.Strings.EMPTY;
        LoginDomain loginDomain = new LoginDomain();
        loginDomain.setPassword(password);
        loginDomain.setEmail(email);
        when(mInteractor.getLoginData()).thenReturn(loginDomain);
        mPresenter.onLoginClicked();
        verify(mPageObject).showValidationErrors(any(LoginValidationErrors.class));
        verify(mPageObject, never()).showLoadingProgress();
        verify(mPageObject, never()).hideLoadingProgress();
        verify(mPageObject, never()).showError(anyString());
        verify(mPageObject, never()).navigateToMainView();
    }
}