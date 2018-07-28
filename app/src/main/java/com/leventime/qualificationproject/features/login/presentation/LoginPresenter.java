package com.leventime.qualificationproject.features.login.presentation;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.BuildConfig;
import com.leventime.qualificationproject.R;
import com.leventime.qualificationproject.base.core.presentation.BasePresenter;
import com.leventime.qualificationproject.features.login.LoginContract;
import com.leventime.qualificationproject.features.login.domain.LoginDomain;
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
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter{

    private final LoginContract.Interactor mInteractor;
    private final LoginValidator mLoginValidator;
    private final LoginContract.PageObject mLoginPageObject;

    /**
     * @param aInteractor interactor
     * @param aLoginValidator login validator
     * @param aLoginPageObject login page object
     */
    public LoginPresenter(@NonNull LoginContract.Interactor aInteractor,
                          @NonNull LoginValidator aLoginValidator,
                          @NonNull LoginContract.PageObject aLoginPageObject){
        super(aLoginPageObject);
        mInteractor = aInteractor;
        mLoginValidator = aLoginValidator;
        mLoginPageObject = aLoginPageObject;
    }

    @Override
    public void attachView(@NonNull final LoginContract.View aView){
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
            mLoginPageObject.showValidationErrors(validationResult);
        } else{
            Disposable disposable = mInteractor.login()
                    .retry(BuildConfig.COUNT_RETRY_REQUEST)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(aDisposable -> mLoginPageObject.showLoadingProgress())
                    .doFinally(mLoginPageObject::hideLoadingProgress)
                    .subscribe(mLoginPageObject::navigateToMainView, aThrowable -> {
                        Timber.e(aThrowable);
                        mLoginPageObject.showError(Errors.getErrorMessage(aThrowable, mInteractor.getStringResource(R.string.error_network)));
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
