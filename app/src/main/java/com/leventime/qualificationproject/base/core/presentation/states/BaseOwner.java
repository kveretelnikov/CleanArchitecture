package com.leventime.qualificationproject.base.core.presentation.states;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.base.core.presentation.BasePresenter;
import com.leventime.qualificationproject.base.core.presentation.BaseView;

/**
 * Describe base owner
 *
 * @param <VIEW> view
 * @param <STATE> state
 * @author kv
 */
public interface BaseOwner<VIEW extends BaseView, STATE extends BaseState> extends BasePresenter<VIEW>{

    /**
     * Set state
     *
     * @param aState state
     */
    void setState(@NonNull STATE aState);
}