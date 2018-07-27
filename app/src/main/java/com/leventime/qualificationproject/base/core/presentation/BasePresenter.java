package com.leventime.qualificationproject.base.core.presentation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.leventime.qualificationproject.base.core.BaseContract;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Manage base view, subscriptions
 *
 * @author kv
 */
public class BasePresenter<VIEW extends BaseContract.View> implements BaseContract.Presenter<VIEW>{

    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private BaseContract.PageObject<VIEW> mPageObject = null;
    private VIEW mView;

    /**
     * @param aPageObject page object
     */
    public BasePresenter(@NonNull BaseContract.PageObject<VIEW> aPageObject){
        mPageObject = aPageObject;
    }

    public BasePresenter(){
    }

    @Override
    public void attachView(@NonNull final VIEW aView){
        mView = aView;
        if(mView instanceof BaseViewState.ProgressSupport){
            //Need to remove progress if the page has been recreated
            ((BaseViewState.ProgressSupport) mView).hideLoadingProgress();
        }
        if(mPageObject != null){
            mPageObject.setView(aView);
        }
        onViewAttached();
    }

    @Override
    public void detachView(){
        mView = null;
        if(mPageObject != null){
            mPageObject.removeView();
        }
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
     * @return
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
