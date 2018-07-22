package com.leventime.qualificationproject.base.core.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.leventime.qualificationproject.base.core.BaseContract;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Base screen, which contain logic of manage base view.
 * Add binding to views, contain rx subscription and base methods
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseContract.View{

    private final CompositeDisposable mDestroyCompositeDisposable = new CompositeDisposable();
    private final CompositeDisposable mStopCompositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable final Bundle aSavedInstanceState){
        super.onCreate(aSavedInstanceState);
        setContentView(getLayoutResource());
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy(){
        mDestroyCompositeDisposable.clear();
        BaseContract.Presenter presenter = getPresenter();
        if(presenter != null){
            presenter.detachView();
        }
        super.onDestroy();
    }

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
     * Add disposables to dispose in {@link Activity#onStop()} ()}
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

    /**
     * Define layout resource
     *
     * @return layout resource
     */
    @LayoutRes
    protected abstract int getLayoutResource();

    /**
     * Get presenter
     *
     * @return presenter
     */
    @Nullable
    protected abstract BaseContract.Presenter getPresenter();

    /**
     * Configure toolbar
     *
     * @param aToolbar toolbar
     * @param aTitle title
     * @param aShowAsUpButton true if show as up button
     * @return action bar
     */
    @Nullable
    protected ActionBar configureToolbar(@NonNull Toolbar aToolbar, @Nullable String aTitle, boolean aShowAsUpButton){
        aToolbar.setTitle(aTitle);
        setSupportActionBar(aToolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(aShowAsUpButton);
            actionBar.setHomeButtonEnabled(true);
            actionBar.show();
        }
        return actionBar;
    }

    /**
     * Configure toolbar
     *
     * @param aToolbar toolbar
     * @param aTitle title
     * @param aHomeAdUpButtonResource home us up button resource
     * @return action bar
     */
    @Nullable
    protected ActionBar configureToolbar(@NonNull Toolbar aToolbar, String aTitle, int aHomeAdUpButtonResource){
        ActionBar actionBar = configureToolbar(aToolbar, aTitle, true);
        if(actionBar != null){
            actionBar.setHomeAsUpIndicator(aHomeAdUpButtonResource);
        }
        return actionBar;
    }
}
