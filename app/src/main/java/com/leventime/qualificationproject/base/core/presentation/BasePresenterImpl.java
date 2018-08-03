package com.leventime.qualificationproject.base.core.presentation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Manage base view, subscriptions
 *
 * @author kv
 */
public class BasePresenterImpl<VIEW extends BaseView> implements BasePresenter<VIEW>{

    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private VIEW mView;

    @Override
    public void attachView(@NonNull final VIEW aView){
        mView = aView;
        if(mView instanceof BaseViewState.ProgressSupport){
            //Need to remove progress if the page has been recreated
            ((BaseViewState.ProgressSupport) mView).hideLoadingProgress();
        }
        onViewAttached();
    }

    @Override
    public void detachView(){
        mView = null;
        mCompositeDisposable.clear();
    }

    @Override
    public boolean isViewAttached(){
        return mView != null;
    }

    /**
     * Use to update view presentation after calling {@link #attachView}
     */
    protected void onViewAttached(){
        //Use when need, depends on realization
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

    /**
     * Add disposables to dispose
     *
     * @param aDisposables disposables
     */
    protected void addToDispose(Disposable... aDisposables){
        if(aDisposables != null){
            for(Disposable disposable : aDisposables){
                mCompositeDisposable.add(disposable);
            }
        }
    }
}
