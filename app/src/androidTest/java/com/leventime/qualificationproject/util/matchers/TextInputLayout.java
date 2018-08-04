package com.leventime.qualificationproject.util.matchers;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * @author kv
 */
public final class TextInputLayout{

    private TextInputLayout(){
    }

    /**
     * Check that display error message
     *
     * @param aError error message
     * @return true if display error
     */
    public static Matcher<View> hasErrorText(@NonNull final String aError){
        return new TypeSafeMatcher<View>(){

            @Override
            public boolean matchesSafely(View view){
                if(!(view instanceof android.support.design.widget.TextInputLayout)){
                    return false;
                }
                CharSequence error = ((android.support.design.widget.TextInputLayout) view).getError();
                return !TextUtils.isEmpty(error) && aError.contentEquals(error);
            }

            @Override
            public void describeTo(Description description){
            }
        };
    }

    /**
     * Check that {@link android.support.design.widget.TextInputLayout} display error
     *
     * @return return true if display error
     */
    public static Matcher<View> isError(){
        return new TypeSafeMatcher<View>(){

            @Override
            public boolean matchesSafely(View aView){
                return aView instanceof android.support.design.widget.TextInputLayout && ((android.support.design.widget.TextInputLayout) aView).isErrorEnabled();

            }

            @Override
            public void describeTo(Description aDescription){
            }
        };
    }
}
