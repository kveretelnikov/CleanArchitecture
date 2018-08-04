package com.leventime.qualificationproject.features.login.presentation;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.R;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.leventime.qualificationproject.util.Util.pauseTest;

/**
 * Describe login page object
 *
 * @author kv
 */
public class LoginPageObject{

    /**
     * Type email
     *
     * @param aEmail email
     */
    public void typeEmail(@NonNull String aEmail){
        onView(withId(R.id.etEmailLogin))
                .perform(typeText(aEmail));
    }

    /**
     * Type password
     *
     * @param aPassword password
     */
    public void typePassword(@NonNull String aPassword){
        onView(withId(R.id.etPasswordLogin))
                .perform(typeText(aPassword));
    }

    /**
     * clear email
     */
    public void clearEmail(){
        onView(withId(R.id.etEmailLogin))
                .perform(clearText());
        pauseTest();
    }

    /**
     * clear password
     */
    public void clearPassword(){
        onView(withId(R.id.etPasswordLogin))
                .perform(clearText());
        pauseTest();
    }

    /**
     * Hide keyboard
     */
    public void hideKeyboard(){
        closeSoftKeyboard();
    }

    /**
     * Press button login
     */
    public void pressLoginButton(){
        onView(withId(R.id.btnLogin))
                .perform(click());
    }

}
