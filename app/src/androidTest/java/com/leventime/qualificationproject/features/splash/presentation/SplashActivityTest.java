package com.leventime.qualificationproject.features.splash.presentation;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;

import com.leventime.qualificationproject.App;
import com.leventime.qualificationproject.base.core.data.PreferenceManager;
import com.leventime.qualificationproject.features.login.presentation.LoginActivity;
import com.leventime.qualificationproject.features.main.presentation.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.UUID;

import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.times;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;

/**
 * Test {@link SplashActivity}
 *
 * @author kv
 */
public class SplashActivityTest{

    @Rule
    public ActivityTestRule<SplashActivity> mActivityRule = new ActivityTestRule<>(SplashActivity.class, false, false);

    private Context mContext;
    private PreferenceManager mPreferenceManager;

    @Before
    public void setUp() throws Exception{
        Intents.init();
        mContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        App app = App.get(mContext);
        mPreferenceManager = app.getPreferenceManager();
        mPreferenceManager.clearAll();
    }

    @After
    public void tearDown() throws Exception{
        Intents.release();
    }

    /**
     * Test navigation to {@link LoginActivity}
     */
    @Test
    public void navigateToLogin(){
        mActivityRule.launchActivity(new Intent());
        intended(hasComponent(LoginActivity.class.getName()));
        intended(hasComponent(MainActivity.class.getName()), times(0));
    }

    /**
     * Test navigation to {@link MainActivity}
     */
    @Test
    public void navigateToMain(){
        String token = UUID.randomUUID().toString();
        mPreferenceManager.saveToken(token);
        mActivityRule.launchActivity(new Intent());
        intended(hasComponent(MainActivity.class.getName()));
        intended(hasComponent(LoginActivity.class.getName()), times(0));
    }
}