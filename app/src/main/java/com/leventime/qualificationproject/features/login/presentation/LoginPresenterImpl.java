package com.leventime.qualificationproject.features.login.presentation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.leventime.qualificationproject.BuildConfig;
import com.leventime.qualificationproject.R;
import com.leventime.qualificationproject.base.core.presentation.BasePresenterImpl;
import com.leventime.qualificationproject.features.login.domain.LoginDomain;
import com.leventime.qualificationproject.features.login.domain.LoginInteractor;
import com.leventime.qualificationproject.features.login.presentation.states.LoginBaseState;
import com.leventime.qualificationproject.features.login.presentation.states.LoginCompleteState;
import com.leventime.qualificationproject.features.login.presentation.states.LoginInitState;
import com.leventime.qualificationproject.features.login.presentation.states.LoginProcessingState;
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
    private LoginBaseState mState;

    /**
     * @param aInteractor interactor
     * @param aLoginValidator login validator
     */
    public LoginPresenterImpl(@NonNull LoginInteractor aInteractor,
                              @NonNull LoginValidator aLoginValidator,
                              @NonNull LoginBaseState aState){
        super(LoginView.EMPTY);
        mInteractor = aInteractor;
        mLoginValidator = aLoginValidator;
        mState = aState;
        mState.setOwner(this);
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
    public String onEmailChanged(@Nullable final String aEmail){
        mInteractor.setEmail(aEmail);
        mState.invalidateView(getView());
        if(mLoginValidator.validateLoginEmail(aEmail)){
            return Strings.EMPTY;
        } else{
            return mInteractor.getStringResource(R.string.error_login_email_validate);
        }
    }

    @NonNull
    @Override
    public String onPasswordChanged(@Nullable final String aPassword){
        mInteractor.setPassword(aPassword);
        mState.invalidateView(getView());
        if(mLoginValidator.validateLoginPassword(aPassword)){
            return Strings.EMPTY;
        } else{
            return mInteractor.getStringResource(R.string.error_login_password_validate);
        }
    }

    @Override
    public void onLoginClicked(){
        setState(new LoginProcessingState());
    }

    @NonNull
    @Override
    public LoginValidationErrors getLoginValidationErrors(){
        LoginDomain loginData = mInteractor.getLoginData();
        LoginValidationErrors loginValidationErrors = new LoginValidationErrors();
        String emailError = mLoginValidator.validateLoginEmail(loginData.getEmail()) ? null : mInteractor.getStringResource(R.string.error_login_email_validate);
        String passwordError = mLoginValidator.validateLoginPassword(loginData.getPassword()) ? null : mInteractor.getStringResource(R.string.error_login_password_validate);
        loginValidationErrors.setEmailError(emailError);
        loginValidationErrors.setPasswordError(passwordError);
        return loginValidationErrors;
    }

    @Override
    public void login(){
        Disposable disposable = mInteractor.login()
                .retry(BuildConfig.COUNT_RETRY_REQUEST)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(aDisposable -> getView().showLoadingProgress())
                .doFinally(getView()::hideLoadingProgress)
                .subscribe(() -> {
                    setState(new LoginCompleteState());
                }, aThrowable -> {
                    Timber.e(aThrowable);
                    setState(new LoginInitState());
                    getView().showError(Errors.getErrorMessage(aThrowable, mInteractor.getStringResource(R.string.error_network)));
                });
        addToDispose(disposable);
    }

    @Override
    public void setState(@NonNull final LoginBaseState aState){
        mState.onExit();
        mState = aState;
        clearDisposables();
        mState.setOwner(this);
        mState.onEnter(getView());
    }

    @NonNull
    @Override
    public LoginBaseState.LoginStateType getStateType(){
        return mState.getType();
    }
}
