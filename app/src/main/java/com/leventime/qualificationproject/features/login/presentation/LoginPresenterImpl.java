package com.leventime.qualificationproject.features.login.presentation;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.BuildConfig;
import com.leventime.qualificationproject.R;
import com.leventime.qualificationproject.base.core.presentation.BasePresenterImpl;
import com.leventime.qualificationproject.features.login.domain.LoginDomain;
import com.leventime.qualificationproject.features.login.domain.LoginInteractor;
import com.leventime.qualificationproject.util.Errors;
import com.leventime.qualificationproject.util.Strings;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Manage login in app
 *
 * @author kv
 */
public class LoginPresenterImpl extends BasePresenterImpl<LoginView> implements LoginPresenter{

    private final LoginInteractor mInteractor;
    private final LoginValidator mLoginValidator;

    /**
     * @param aInteractor interactor
     * @param aLoginValidator login validator
     */
    public LoginPresenterImpl(@NonNull LoginInteractor aInteractor,
                              @NonNull LoginValidator aLoginValidator){
        mInteractor = aInteractor;
        mLoginValidator = aLoginValidator;
    }

    @Override
    public void attachView(@NonNull final LoginView aView){
        super.attachView(aView);
        aView.setListener(this);
    }

    @Override
    public void detachView(){
        getView().setListener(null);
        super.detachView();
    }

    @NonNull
    @Override
    public String onEmailChanged(@NonNull final String aEmail){
        mInteractor.setEmail(aEmail);
        if(mLoginValidator.validateLoginEmail(aEmail)){
            return Strings.EMPTY;
        } else{
            return mInteractor.getStringResource(R.string.error_login_email_validate);
        }
    }

    @NonNull
    @Override
    public String onPasswordChanged(@NonNull final String aPassword){
        mInteractor.setPassword(aPassword);
        if(mLoginValidator.validateLoginPassword(aPassword)){
            return Strings.EMPTY;
        } else{
            return mInteractor.getStringResource(R.string.error_login_password_validate);
        }
    }

    @Override
    public void onLoginClicked(){
        LoginValidationErrors validationResult = getValidationResult();
        if(validationResult.hasErrors()){
            getView().showValidationErrors(validationResult);
        } else{
            Disposable disposable = mInteractor.login()
                    .retry(BuildConfig.COUNT_RETRY_REQUEST)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(aDisposable -> getView().showLoadingProgress())
                    .doFinally(getView()::hideLoadingProgress)
                    .subscribe(getView()::navigateToMainView, aThrowable -> {
                        Timber.e(aThrowable);
                        getView().showError(Errors.getErrorMessage(aThrowable, mInteractor.getStringResource(R.string.error_network)));
                    });
            addToDispose(disposable);
        }
    }

    /**
     * Get validation result
     *
     * @return validation result
     */
    @NonNull
    private LoginValidationErrors getValidationResult(){
        LoginDomain loginData = mInteractor.getLoginData();
        LoginValidationErrors loginValidationErrors = new LoginValidationErrors();
        String emailError = mLoginValidator.validateLoginEmail(loginData.getEmail()) ? null : mInteractor.getStringResource(R.string.error_login_email_validate);
        String passwordError = mLoginValidator.validateLoginPassword(loginData.getPassword()) ? null : mInteractor.getStringResource(R.string.error_login_password_validate);
        loginValidationErrors.setEmailError(emailError);
        loginValidationErrors.setPasswordError(passwordError);
        return loginValidationErrors;
    }
}
