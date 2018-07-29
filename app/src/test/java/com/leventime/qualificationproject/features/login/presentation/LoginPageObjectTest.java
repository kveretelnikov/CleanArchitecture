package com.leventime.qualificationproject.features.login.presentation;

import com.leventime.qualificationproject.features.login.LoginContract;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

/**
 * Test {@link LoginPageObject}
 *
 * @author kv
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class LoginPageObjectTest{

    @Mock
    LoginContract.View mView;

    private LoginPageObject mPageObject = new LoginPageObject();

    @Before
    public void setUp() throws Exception{
        mPageObject.setView(mView);
    }

    @After
    public void tearDown() throws Exception{
        mPageObject.removeView();
    }

    @Test
    public void showLoadingProgress(){
        mPageObject.showLoadingProgress();
        verify(mView).showLoadingProgress();
    }

    @Test
    public void hideLoadingProgress(){
        mPageObject.hideLoadingProgress();
        verify(mView).hideLoadingProgress();
    }

    @Test
    public void showError(){
        mPageObject.showError("error");
        verify(mView).showError(anyString());
    }

    @Test
    public void showValidationErrors(){
        LoginValidationErrors errors = new LoginValidationErrors();
        mPageObject.showValidationErrors(errors);
        verify(mView).showValidationErrors(any(LoginValidationErrors.class));
    }

    @Test
    public void navigateToMainView(){
        mPageObject.navigateToMainView();
        verify(mView).navigateToMainView();
    }
}