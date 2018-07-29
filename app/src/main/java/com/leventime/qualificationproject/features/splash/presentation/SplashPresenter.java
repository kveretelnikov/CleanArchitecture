package com.leventime.qualificationproject.features.splash.presentation;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.base.core.presentation.BasePresenter;
import com.leventime.qualificationproject.features.splash.SplashContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Manage first navigation in app
 *
 * @author kv
 */
public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter{

    private final SplashContract.PageObject mPageObject;
    private final SplashContract.Interactor mInteractor;

    /**
     * @param aPageObject page object
     * @param aInteractor interactor
     */
    public SplashPresenter(@NonNull final SplashContract.PageObject aPageObject, @NonNull SplashContract.Interactor aInteractor){
        super(aPageObject);
        mPageObject = aPageObject;
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
                        mPageObject.navigateToMainView();
                    } else{
                        mPageObject.navigateToLoginView();
                    }
                }, Timber::e);
        addToDispose(disposable);
    }
}
