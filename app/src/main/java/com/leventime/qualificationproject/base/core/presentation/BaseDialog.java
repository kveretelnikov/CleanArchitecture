package com.leventime.qualificationproject.base.core.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Base dialog screen. Add binding to views.
 */
public abstract class BaseDialog extends DialogFragment{

    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater aInflater, @Nullable ViewGroup aContainer, @Nullable Bundle aSavedInstanceState){
        View view = aInflater.inflate(getLayoutResource(), aContainer, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView(){
        mUnbinder.unbind();
        super.onDestroyView();
    }

    /**
     * Define layout resource
     *
     * @return layout resource
     */
    protected abstract int getLayoutResource();
}
