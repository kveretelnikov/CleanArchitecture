package com.leventime.qualificationproject.features.splash.presentation;

import android.content.ComponentName;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import com.leventime.qualificationproject.App;
import com.leventime.qualificationproject.base.core.data.PreferenceManager;
import com.leventime.qualificationproject.features.login.presentation.LoginActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;

/**
 * Test {@link SplashActivity}
 *
 * @author kv
 */
public class SplashActivityTest{

    @Rule
    public IntentsTestRule<SplashActivity> mActivityRule = new IntentsTestRule<>(SplashActivity.class, false, false);

    private Context mContext;
    private PreferenceManager mPreferenceManager;

    @Before
    public void setUp() throws Exception{
        mContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        App app = App.get(mContext);
        mPreferenceManager = app.getPreferenceManager();
        mPreferenceManager.clearAll();
    }

    @Test
    public void testNavigateToLogin(){
        //mContext.startActivity(new Intent(mContext, SplashActivity.class));
        /*mActivityRule.launchActivity(new Intent());
        Intents.release();
        Intents.init();
        SplashActivity activity = mActivityRule.getActivity();
        while(!activity.isFinishing()){

        }*/
        //mActivityRule.launchActivity(new Intent(mContext, SplashActivity.class));
        intended(hasComponent(new ComponentName(mActivityRule.getActivity(), LoginActivity.class)));
        //Intents.release();
    }
}