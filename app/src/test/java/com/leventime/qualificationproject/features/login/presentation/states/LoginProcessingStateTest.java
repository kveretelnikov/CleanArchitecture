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
 * Test {@link LoginProcessingState}
 *
 * @author kv
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class LoginProcessingStateTest{

    @Mock
    LoginView mView;
    @Mock
    LoginOwner mOwner;

    LoginProcessingState mState = new LoginProcessingState();

    @Before
    public void setUp(){
        mState.setOwner(mOwner);
    }

    @Test
    public void onEnter(){
        mState.onEnter(mView);
        verify(mOwner).login();
    }

    @Test
    public void invalidateView(){
        LoginValidationErrors errors = new LoginValidationErrors();
        errors.setPasswordError("error");
        errors.setEmailError("error");
        when(mOwner.getLoginValidationErrors()).thenReturn(errors);
        when(mOwner.getView()).thenReturn(mView);
        mState.invalidateView(mView);
        verify(mView).setLoginEnabled(anyBoolean());
    }
}