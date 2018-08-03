package com.leventime.qualificationproject.features.login.presentation.states;

import com.leventime.qualificationproject.features.login.presentation.LoginValidationErrors;
import com.leventime.qualificationproject.features.login.presentation.LoginView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test {@link LoginInitState}
 *
 * @author kv
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class LoginInitStateTest{

    @Mock
    LoginView mView;
    @Mock
    LoginOwner mOwner;

    LoginInitState mState = new LoginInitState();

    @Before
    public void setUp(){
        when(mOwner.getView()).thenReturn(mView);
        mState.setOwner(mOwner);
    }

    @Test
    public void invalidateView(){
        LoginValidationErrors errors = new LoginValidationErrors();
        errors.setPasswordError("error");
        errors.setEmailError("error");
        when(mOwner.getLoginValidationErrors()).thenReturn(errors);
        mState.invalidateView(mView);
        verify(mView).setLoginEnabled(anyBoolean());
    }
}