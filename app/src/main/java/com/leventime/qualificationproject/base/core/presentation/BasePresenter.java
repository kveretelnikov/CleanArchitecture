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
public class BasePresenter<V extends BaseContract.View> implements BaseContract.Presenter<V>{

    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private V mView;

    @Override
    public void attachView(@NonNull final V aView){
        mView = aView;
        if(mView instanceof BaseViewState.ProgressSupport){
            //Need to remove progress if the page has been recreated
            ((BaseViewState.ProgressSupport) mView).hideProgress();
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
     * @return
     */
    @Nullable
    protected V getView(){
        return mView;
    }

    /**
     * Handle get error in rx operation
     *
     * @param aThrowable error
     */
    protected void handleGetErrorRxOperation(@NonNull Throwable aThrowable){
        //Use when need, depends on realization
    }

    /**
     * Handle get success in rx operation
     */
    protected void handleGetSuccessRxOperation(){
        //Use when need, depends on realization
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
