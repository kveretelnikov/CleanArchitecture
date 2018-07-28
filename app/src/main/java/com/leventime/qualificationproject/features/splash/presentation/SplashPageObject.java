package com.leventime.qualificationproject.features.splash.presentation;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.base.core.presentation.BasePageObject;
import com.leventime.qualificationproject.features.splash.SplashContract;

/**
 * Describe splash page object
 *
 * @author kv
 */
public class SplashPageObject extends BasePageObject<SplashContract.View> implements SplashContract.PageObject{

    @Override
    public void navigateToMainView(){
        if(isViewShown()){
            getView().navigateToMainView();
        }
    }

    @Override
    public void navigateToLoginView(){
        if(isViewShown()){
            getView().navigateToLoginView();
        }
    }

    @Override
    public void showError(@NonNull final String aError){
        if(isViewShown()){
            getView().showError(aError);
        }
    }
}
