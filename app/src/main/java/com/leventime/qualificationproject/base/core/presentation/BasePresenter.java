package com.leventime.qualificationproject.base.core.presentation;

import android.support.annotation.NonNull;

/**
 * Presenter determines what view should do and proceed its input
 *
 * @param <VIEW> view
 * @author kv
 */
public interface BasePresenter<VIEW extends BaseView>{

    /**
     * Attach view
     *
     * @param aView view
     */
    void attachView(@NonNull VIEW aView);

    /**
     * Detach view
     */
    void detachView();

    /**
     * Check that view is attached
     *
     * @return true if view attached
     */
    boolean isViewAttached();
}
