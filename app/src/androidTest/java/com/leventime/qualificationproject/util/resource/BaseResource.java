package com.leventime.qualificationproject.util.resource;

import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;

/**
 * Base idl resource
 *
 * @author kv
 */
public abstract class BaseResource implements IdlingResource{

    @Nullable
    private ResourceCallback mCallback;

    @Override
    public boolean isIdleNow(){

        if(checkCondition()){
            notifyDone();
            return true;
        }
        return false;
    }

    @Override
    public void registerIdleTransitionCallback(final ResourceCallback aCallback){
        mCallback = aCallback;
    }

    /**
     * Check condition
     *
     * @return true if condition correspondence
     */
    public abstract boolean checkCondition();

    /**
     * Notify done
     */
    protected void notifyDone(){
        if(mCallback != null){
            mCallback.onTransitionToIdle();
        }
    }
}
