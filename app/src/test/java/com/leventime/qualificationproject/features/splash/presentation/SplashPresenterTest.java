package com.leventime.qualificationproject.features.splash.presentation;

import com.leventime.qualificationproject.features.splash.domain.SplashInteractor;
import com.leventime.qualificationproject.util.RxSchedulerRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Single;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test {@link SplashPresenterImpl}
 *
 * @author kv
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class SplashPresenterTest{

    @Rule
    public final RxSchedulerRule mOverrideSchedulersRule = new RxSchedulerRule();

    @Mock
    SplashView mView;
    @Mock
    SplashInteractor mInteractor;

    SplashPresenterImpl mPresenter;

    @Before
    public void setUp(){
        when(mInteractor.isUserLogged()).thenReturn(Single.just(false));
        mPresenter = new SplashPresenterImpl(mInteractor);
        reset(mView);
    }

    @After
    public void tearDown(){
        mPresenter.detachView();
    }

    @Test
    public void onViewAttachedNavigateToMainVIew(){
        when(mInteractor.isUserLogged()).thenReturn(Single.just(true));
        mPresenter.attachView(mView);
        verify(mView).navigateToMainView();
        verify(mView, never()).navigateToLoginView();
    }

    @Test
    public void onViewAttachedNavigateToLoginVIew(){
        when(mInteractor.isUserLogged()).thenReturn(Single.just(false));
        mPresenter.attachView(mView);
        verify(mView).navigateToLoginView();
        verify(mView, never()).navigateToMainView();
    }

    @Test
    public void onViewAttachedFail(){
        when(mInteractor.isUserLogged()).thenReturn(Single.error(new Exception()));
        mPresenter.attachView(mView);
        verify(mView, never()).navigateToLoginView();
        verify(mView, never()).navigateToMainView();
    }
}