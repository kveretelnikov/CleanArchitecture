package com.leventime.qualificationproject.base.core.presentation;

import android.support.annotation.NonNull;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Manage base view, subscriptions
 *
 * @param <VIEW> view
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
        onViewAttached();
    }

    @Override
    public void detachView(){
        mView = mEmptyView;
        mCompositeDisposable.clear();
    }

    /**
     * Get view
     *
     * @return view
     */
    @NonNull
    @Override
    public VIEW getView(){
        return mView;
    }

    /**
     * Use to update view presentation after calling {@link #attachView}
     */
    protected void onViewAttached(){
        //Use when need, depends on realization
    }

    /**
     * Clear disposables
     */
    protected void clearDisposables(){
        mCompositeDisposable.clear();
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
