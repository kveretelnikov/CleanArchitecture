package com.leventime.qualificationproject.base.core.presentation.states;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.base.core.presentation.BaseView;

import timber.log.Timber;

/**
 * Describe base state
 *
 * @param <VIEW> view
 * @param <OWNER> owner
 */
public abstract class BaseStateImpl<VIEW extends BaseView, OWNER extends BaseOwner> implements BaseState<VIEW, OWNER>{

    private OWNER mOwner;

    @Override
    public void onEnter(@NonNull final VIEW aView){
        Timber.d("onEnter");
        //depend from realization
    }

    @Override
    public void onExit(){
        Timber.d("onExit");
        //depend from realization
    }

    @Override
    public void forward(){
        Timber.d("forward");
        onExit();
        //depend from realization
    }

    @Override
    public void back(){
        Timber.d("back");
        onExit();
        //depend from realization
    }

    @Override
    public void invalidateView(@NonNull final VIEW aView){
        Timber.d("invalidateView");
        //depend from realization
    }

    @NonNull
    @Override
    public OWNER getOwner(){
        return mOwner;
    }

    @Override
    public void setOwner(@NonNull final OWNER aOwner){
        mOwner = aOwner;
    }
}
