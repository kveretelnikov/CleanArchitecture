package com.leventime.qualificationproject.features.login.presentation.states;

import com.leventime.qualificationproject.features.login.presentation.LoginView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test {@link LoginCompleteState}
 *
 * @author kv
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class LoginCompleteStateTest{

    @Mock
    private LoginView mView;
    @Mock
    private LoginOwner mOwner;

    private LoginCompleteState mState = new LoginCompleteState();

    @Before
    public void setUp(){
        when(mOwner.getView()).thenReturn(mView);
        mState.setOwner(mOwner);
    }

    @Test
    public void onEnter(){
        mState.onEnter(mView);
        verify(mView).navigateToMainView();
    }
}