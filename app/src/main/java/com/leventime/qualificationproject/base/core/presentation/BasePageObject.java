package com.leventime.qualificationproject.base.core.presentation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.leventime.qualificationproject.base.core.BaseContract;

/**
 * Describe base page object
 *
 * @author kv
 */
public class BasePageObject<VIEW extends BaseContract.View> implements BaseContract.PageObject<VIEW>{

    private VIEW mView;

    /**
     * Check that view shown in screen
     *
     * @return true if view shown
     */
    protected boolean isViewShown(){
        return mView != null;
    }

    /**
     * Get view
     *
     * @return view
     */
    @Nullable
    protected VIEW getView(){
        return mView;
    }

    @Override
    public void setView(@NonNull final VIEW aView){
        mView = aView;
    }

    @Override
    public void removeView(){
        mView = null;
    }
}
