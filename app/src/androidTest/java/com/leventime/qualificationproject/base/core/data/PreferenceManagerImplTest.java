package com.leventime.qualificationproject.base.core.data;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Test {@link PreferenceManagerImplTest}
 *
 * @author kv
 */
@RunWith(AndroidJUnit4.class)
public class PreferenceManagerImplTest{

    PreferenceManagerImpl mPreferenceManager;

    @Before
    public void setUp() throws Exception{
        Context context = InstrumentationRegistry.getTargetContext();
        mPreferenceManager = new PreferenceManagerImpl(context);
    }

    @Test
    public void saveAndGetToken(){
        String token = "token";
        mPreferenceManager.saveToken(token);
        String actualToken = mPreferenceManager.getToken();
        assertEquals(token, actualToken);
    }

    @Test
    public void clearAll(){
        String token = "token";
        mPreferenceManager.saveToken(token);
        mPreferenceManager.clearAll();
        String actualToken = mPreferenceManager.getToken();
        assertEquals(null, actualToken);
    }
}