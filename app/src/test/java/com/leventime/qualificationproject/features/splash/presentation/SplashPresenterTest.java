package com.leventime.qualificationproject.features.splash.presentation;

import com.leventime.qualificationproject.features.splash.SplashContract;
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
    SplashContract.View mView;
    @Mock
    SplashContract.Interactor mInteractor;
    @Mock
    SplashPageObject mPageObject;

    SplashPresenterImpl mPresenter;

    @Before
    public void setUp(){
        when(mInteractor.isUserLogged()).thenReturn(Single.just(false));
        mPresenter = new SplashPresenterImpl(mPageObject, mInteractor);
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
        verify(mPageObject).navigateToMainView();
        verify(mPageObject, never()).navigateToLoginView();
    }

    @Test
    public void onViewAttachedNavigateToLoginVIew(){
        when(mInteractor.isUserLogged()).thenReturn(Single.just(false));
        mPresenter.attachView(mView);
        verify(mPageObject).navigateToLoginView();
        verify(mPageObject, never()).navigateToMainView();
    }

    @Test
    public void onViewAttachedFail(){
        when(mInteractor.isUserLogged()).thenReturn(Single.error(new Exception()));
        mPresenter.attachView(mView);
        verify(mPageObject, never()).navigateToLoginView();
        verify(mPageObject, never()).navigateToMainView();
    }
}