package com.leventime.qualificationproject.base.core.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leventime.qualificationproject.base.core.BaseContract;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Base screen, which contain logic of manage base view.
 * Add binding to views, contain rx subscription and base methods
 */
public abstract class BaseFragment extends Fragment implements BaseContract.View{

    private final CompositeDisposable mDestroyCompositeDisposable = new CompositeDisposable();
    private final CompositeDisposable mStopCompositeDisposable = new CompositeDisposable();
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater aInflater, @Nullable ViewGroup aContainer, @Nullable Bundle aSavedInstanceState){
        View view = aInflater.inflate(getLayoutResource(), aContainer, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStop(){
        mStopCompositeDisposable.clear();
        super.onStop();
    }

    @Override
    public void onDestroyView(){
        mDestroyCompositeDisposable.clear();
        if(getPresenter() != null){
            getPresenter().detachView();
        }
        mUnbinder.unbind();
        super.onDestroyView();
    }

    /**
     * Define layout resource
     *
     * @return layout resource
     */
    protected abstract int getLayoutResource();

    /**
     * Get presenter
     *
     * @return presenter
     */
    @Nullable
    protected abstract BaseContract.Presenter getPresenter();

    /**
     * Add disposables to dispose in {@link Activity#onDestroy()}
     *
     * @param aDisposables disposables
     */
    protected void disposeOnDestroy(Disposable... aDisposables){
        if(aDisposables != null){
            for(Disposable disposable : aDisposables){
                mDestroyCompositeDisposable.add(disposable);
            }
        }
    }

    /**
     * Add disposables to dispose in {@link Activity#onStop()}
     *
     * @param aDisposables disposables
     */
    protected void disposeOnStop(Disposable... aDisposables){
        if(aDisposables != null){
            for(Disposable disposable : aDisposables){
                mStopCompositeDisposable.add(disposable);
            }
        }
    }
}
