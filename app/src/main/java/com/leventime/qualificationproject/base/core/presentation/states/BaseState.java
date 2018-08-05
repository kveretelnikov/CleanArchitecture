package com.leventime.qualificationproject.base.core.presentation.states;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.base.core.presentation.BaseView;

/**
 *
 * Describe base state
 *
 * @param <VIEW> view
 * @param <OWNER> owner
 * @author kv
 */
public interface BaseState<VIEW extends BaseView, OWNER extends BaseOwner>{

    /**
     * Get name
     *
     * @return name
     */
    @NonNull
    String getName();

    /**
     * Enter to state
     *
     * @param aView view
     */
    void onEnter(@NonNull VIEW aView);

    /**
     * Exit from state
     */
    void onExit();

    /**
     * Return to next state
     */
    void forward();

    /**
     * Return to previous state
     */
    void back();

    /**
     * Invalidate view
     *
     * @param aView view
     */
    void invalidateView(@NonNull VIEW aView);

    /**
     * Get owner
     *
     * @return owner
     */
    @NonNull
    OWNER getOwner();

    /**
     * Set owner
     *
     * @param aOwner owner
     */
    void setOwner(@NonNull OWNER aOwner);
}
