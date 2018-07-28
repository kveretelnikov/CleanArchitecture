package com.leventime.qualificationproject.features.login.presentation;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.base.core.presentation.BasePageObject;
import com.leventime.qualificationproject.features.login.LoginContract;

/**
 * Describe login page object
 *
 * @author kv
 */
public class LoginPageObject extends BasePageObject<LoginContract.View> implements LoginContract.PageObject{

    @Override
    public void showLoadingProgress(){
        if(isViewShown()){
            getView().showLoadingProgress();
        }
    }

    @Override
    public void hideLoadingProgress(){
        if(isViewShown()){
            getView().hideLoadingProgress();
        }
    }

    @Override
    public void showError(@NonNull final String aError){
        if(isViewShown()){
            getView().showError(aError);
        }
    }

    @Override
    public void showValidationErrors(@NonNull final LoginValidationErrors aLoginValidationErrors){
        if(isViewShown()){
            getView().showValidationErrors(aLoginValidationErrors);
        }
    }

    @Override
    public void navigateToMainView(){
        if(isViewShown()){
            getView().navigateToMainView();
        }
    }
}
