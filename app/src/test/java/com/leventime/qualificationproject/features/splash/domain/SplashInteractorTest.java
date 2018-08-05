package com.leventime.qualificationproject.features.splash.domain;

import com.leventime.qualificationproject.features.splash.data.SplashRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Single;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test {@link SplashInteractorImpl}
 *
 * @author kv
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class SplashInteractorTest{

    @Mock
    private SplashRepository mRepository;

    private SplashInteractorImpl mInteractor;

    @Before
    public void setUp() throws Exception{
        mInteractor = new SplashInteractorImpl(mRepository);
    }

    @Test
    public void isUserLogged(){
        when(mRepository.isUserLogged()).thenReturn(Single.just(true));
        mInteractor.isUserLogged()
                .test()
                .assertComplete()
                .assertNoErrors();
        verify(mRepository).isUserLogged();
    }
}