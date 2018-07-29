package com.leventime.qualificationproject.features.splash.presentation;

import com.leventime.qualificationproject.features.splash.SplashContract;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * TEst {@link SplashPageObject}
 *
 * @author kv
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class SplashPageObjectTest{

    @Mock
    SplashContract.View mView;

    private SplashPageObject mSplashPageObject = new SplashPageObject();

    @Before
    public void setUp() throws Exception{
        mSplashPageObject.setView(mView);
    }

    @After
    public void tearDown() throws Exception{
        mSplashPageObject.removeView();
    }

    @Test
    public void navigateToMainView(){
        mSplashPageObject.navigateToMainView();
        verify(mView).navigateToMainView();
    }

    @Test
    public void navigateToLoginView(){
        mSplashPageObject.navigateToLoginView();
        verify(mView).navigateToLoginView();
    }
}