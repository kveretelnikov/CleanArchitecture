package com.leventime.qualificationproject.features.splash.data;

import com.leventime.qualificationproject.base.core.data.PreferenceManagerImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * Test {@link SplashRepositoryImpl}
 *
 * @author kv
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class SplashRepositoryTest{

    @Mock
    private PreferenceManagerImpl mPreferenceManager;

    private SplashRepositoryImpl mRepository;

    @Before
    public void setUp() throws Exception{
        mRepository = new SplashRepositoryImpl(mPreferenceManager);
    }

    @Test
    public void isUserLogged(){
        mRepository.isUserLogged()
                .test()
                .assertComplete()
                .assertNoErrors();
        verify(mPreferenceManager).getToken();
    }
}