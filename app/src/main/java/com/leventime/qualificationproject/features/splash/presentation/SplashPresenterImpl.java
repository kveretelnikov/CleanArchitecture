package com.leventime.qualificationproject.features.splash.presentation;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.base.core.presentation.BasePresenterImpl;
import com.leventime.qualificationproject.features.splash.domain.SplashInteractor;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Manage first navigation in app
 *
 * @author kv
 */
public class SplashPresenterImpl extends BasePresenterImpl<SplashView> implements SplashPresenter{

    private final SplashInteractor mInteractor;

    /**
     * @param aInteractor interactor
     */
    public SplashPresenterImpl(@NonNull SplashInteractor aInteractor){
        mInteractor = aInteractor;
    }

    @Override
    protected void onViewAttached(){
        super.onViewAttached();
        Disposable disposable = mInteractor.isUserLogged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aIsLogged -> {
                    if(aIsLogged){
                        getView().navigateToMainView();
                    } else{
                        getView().navigateToLoginView();
                    }
                }, Timber::e);
        addToDispose(disposable);
    }
}
