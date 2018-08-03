package com.leventime.qualificationproject.base.core.presentation;

import android.support.annotation.NonNull;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Manage base view, subscriptions
 *
 * @author kv
 */
public class BasePresenterImpl<VIEW extends BaseView> implements BasePresenter<VIEW>{

    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private final VIEW mEmptyView;
    private VIEW mView;

    /**
     * @param aEmptyView empty view
     */
    public BasePresenterImpl(@NonNull final VIEW aEmptyView){
        if(aEmptyView == null){
            throw new RuntimeException("View can not be empty");
        }
        mEmptyView = aEmptyView;
        mView = aEmptyView;
    }

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
        mView = mEmptyView;
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
    @NonNull
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
