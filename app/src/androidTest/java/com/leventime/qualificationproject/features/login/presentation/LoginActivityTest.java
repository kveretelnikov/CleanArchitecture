package com.leventime.qualificationproject.features.login.presentation;

import android.content.ComponentName;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.DialogFragment;

import com.leventime.qualificationproject.R;
import com.leventime.qualificationproject.base.core.presentation.views.ProgressDialog;
import com.leventime.qualificationproject.features.main.presentation.MainActivity;
import com.leventime.qualificationproject.util.Strings;
import com.leventime.qualificationproject.util.matchers.TextInputLayout;
import com.leventime.qualificationproject.util.resource.BaseResource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

/**
 * Test {@link LoginActivity}
 *
 * @author kv
 */
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest{

    public static final String EMAIL = "username@username.ru";
    public static final String PASSWORD = "username123";
    private static final String PASSWORD_INCORECT = "username";
    private static final String EMAIL_NOT_VALID = "username@username";
    private static final String PASSWORD_NOT_VALID = Strings.EMPTY;
    @Rule
    public IntentsTestRule<LoginActivity> mActivityRule = new IntentsTestRule<>(LoginActivity.class);
    private LoginActivity mActivity;
    private LoginPageObject mLoginPageObject = new LoginPageObject();
    private Resources mResources;

    @Before
    public void setUp() throws Exception{
        mActivity = mActivityRule.getActivity();
        mResources = mActivity.getResources();
    }

    /**
     * Test init state activity
     */
    @Test
    public void testInitState(){
        //Check button login disabled and displayed
        onView(withId(R.id.btnLogin))
                .check(matches(allOf(isDisplayed(), not(isEnabled()))));
        //Check that email displayed
        onView(withId(R.id.tilEmailLogin))
                .check(matches(isDisplayed()));
        onView(withId(R.id.etEmailLogin))
                .check(matches(isDisplayed()));
        //Check that password displayed
        onView(withId(R.id.tilPasswordLogin))
                .check(matches(isDisplayed()));
        onView(withId(R.id.etPasswordLogin))
                .check(matches(isDisplayed()));
        //Check that not display error in email input
        onView(withId(R.id.tilEmailLogin))
                .check(matches(not(TextInputLayout.isError())));
        //Check that not display error in password input
        onView(withId(R.id.tilPasswordLogin))
                .check(matches(not(TextInputLayout.isError())));
    }

    /**
     * Test validation email
     */
    @Test
    public void testValidationEmail(){
        //Check that not display error in email input
        onView(withId(R.id.tilEmailLogin))
                .check(matches(not(TextInputLayout.isError())));
        //Type not valid email
        mLoginPageObject.typeEmail(EMAIL_NOT_VALID);
        //Check that display error in email input
        onView(withId(R.id.tilEmailLogin))
                .check(matches(TextInputLayout.isError()));
        //Clear email
        mLoginPageObject.clearEmail();
        //Check that display error in email input
        onView(withId(R.id.tilEmailLogin))
                .check(matches(TextInputLayout.isError()));
        //Type valid email
        mLoginPageObject.typeEmail(EMAIL);
        //Check that not display error in email input
        onView(withId(R.id.tilEmailLogin))
                .check(matches(not(TextInputLayout.isError())));
    }

    /**
     * Test validation email
     */
    @Test
    public void testValidationPassword(){
        //Check that not display error in password input
        onView(withId(R.id.tilPasswordLogin))
                .check(matches(not(TextInputLayout.isError())));
        //Type valid password
        mLoginPageObject.typePassword(PASSWORD);
        //Check that not display error in password input
        onView(withId(R.id.tilPasswordLogin))
                .check(matches(not(TextInputLayout.isError())));

        //Type not valid password
        mLoginPageObject.clearPassword();
        //Check that display error in password input
        onView(withId(R.id.tilPasswordLogin))
                .check(matches(TextInputLayout.isError()));
    }

    /**
     * Test login with success
     */
    @Test
    public void loginSuccess(){
        BaseResource dialogShowRegistry = createDialogShowRegistry();
        BaseResource dialogHideRegistry = createDialogHideRegistry();
        //Check that button login disable
        onView(withId(R.id.btnLogin))
                .check(matches(allOf(isDisplayed(), not(isEnabled()))));
        //Type valid email
        mLoginPageObject.typeEmail(EMAIL);
        //Check that button login disable
        onView(withId(R.id.btnLogin))
                .check(matches(allOf(isDisplayed(), not(isEnabled()))));
        //Type valid password
        mLoginPageObject.typePassword(PASSWORD);
        //Check that button login enable
        onView(withId(R.id.btnLogin))
                .check(matches(allOf(isDisplayed(), isEnabled())));
        //Hide keyboard
        mLoginPageObject.hideKeyboard();
        //Press login
        mLoginPageObject.pressLoginButton();
        IdlingRegistry.getInstance().register(dialogShowRegistry);
        //Check that show progress dialog
        onView(withText(mResources.getString(R.string.progress_loading)))
                .check(matches(isDisplayed()));
        IdlingRegistry.getInstance().unregister(dialogShowRegistry);
        IdlingRegistry.getInstance().register(dialogHideRegistry);
        //Check that hide progress dialog
        onView(withText(mResources.getString(R.string.progress_loading)))
                .check(doesNotExist());
        IdlingRegistry.getInstance().unregister(dialogHideRegistry);
        //Check that start new activity
        intended(hasComponent(new ComponentName(mActivity, MainActivity.class)));
    }

    /**
     * Test login with fail
     */
    @Test
    public void loginFail(){
        BaseResource dialogShowRegistry = createDialogShowRegistry();
        BaseResource dialogHideRegistry = createDialogHideRegistry();
        //Check that button login disable
        onView(withId(R.id.btnLogin))
                .check(matches(allOf(isDisplayed(), not(isEnabled()))));
        //Type valid email
        mLoginPageObject.typeEmail(EMAIL);
        //Check that button login disable
        onView(withId(R.id.btnLogin))
                .check(matches(allOf(isDisplayed(), not(isEnabled()))));
        //Type valid password
        mLoginPageObject.typePassword(PASSWORD_INCORECT);
        //Check that button login enable
        onView(withId(R.id.btnLogin))
                .check(matches(allOf(isDisplayed(), isEnabled())));
        //Hide keyboard
        mLoginPageObject.hideKeyboard();
        //Press login
        mLoginPageObject.pressLoginButton();
        IdlingRegistry.getInstance().register(dialogShowRegistry);
        //Check that show progress dialog
        onView(withText(mResources.getString(R.string.progress_loading)))
                .check(matches(isDisplayed()));
        IdlingRegistry.getInstance().unregister(dialogShowRegistry);
        IdlingRegistry.getInstance().register(dialogHideRegistry);
        //Check that hide progress dialog
        onView(withText(mResources.getString(R.string.progress_loading)))
                .check(doesNotExist());
        IdlingRegistry.getInstance().unregister(dialogHideRegistry);
        //Check that show login error
        onView(withText(mResources.getString(R.string.error_network)))
                .check(matches(isDisplayed()));
        //Check that not start new Activity
        intended(hasComponent(new ComponentName(mActivity, MainActivity.class)), Intents.times(0));
    }

    /**
     * create dialog show registry
     *
     * @return registry for dialog show detect
     */
    @NonNull
    private BaseResource createDialogShowRegistry(){
        return new BaseResource(){

            @Override
            public String getName(){
                return "showProgressDialog";
            }

            @Override
            public boolean checkCondition(){
                if(mActivity == null){
                    return false;
                }
                DialogFragment progressDialog = (DialogFragment) mActivity.getSupportFragmentManager().findFragmentByTag(ProgressDialog.class.getName());
                return progressDialog != null;
            }
        };
    }

    /**
     * create dialog hide registry
     *
     * @return registry for dialog hide detect
     */
    @NonNull
    private BaseResource createDialogHideRegistry(){
        return new BaseResource(){

            @Override
            public String getName(){
                return "hideProgressDialog";
            }

            @Override
            public boolean checkCondition(){
                if(mActivity == null){
                    return false;
                }
                DialogFragment progressDialog = (DialogFragment) mActivity.getSupportFragmentManager().findFragmentByTag(ProgressDialog.class.getName());
                return progressDialog == null;
            }
        };
    }
}