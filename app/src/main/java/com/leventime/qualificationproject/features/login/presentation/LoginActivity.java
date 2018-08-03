package com.leventime.qualificationproject.features.login.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.leventime.qualificationproject.App;
import com.leventime.qualificationproject.R;
import com.leventime.qualificationproject.base.core.presentation.BaseActivity;
import com.leventime.qualificationproject.base.core.presentation.BasePresenter;
import com.leventime.qualificationproject.base.core.presentation.views.ProgressDialog;
import com.leventime.qualificationproject.features.login.di.LoginModule;
import com.leventime.qualificationproject.features.main.presentation.MainActivity;
import com.leventime.qualificationproject.util.Strings;
import com.leventime.qualificationproject.util.Views;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Show login
 *
 * @author kv
 */
public class LoginActivity extends BaseActivity implements LoginView{

    @Inject
    LoginPresenter mPresenter;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.etEmailLogin)
    AppCompatEditText mEtEmail;
    @BindView(R.id.tilEmailLogin)
    TextInputLayout mTilEmail;
    @BindView(R.id.etPasswordLogin)
    AppCompatEditText mEtPassword;
    @BindView(R.id.tilPasswordLogin)
    TextInputLayout mTilPassword;
    private static final int SKIP_COUNT = 1;
    @Nullable
    private Actions mListener;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout mCoordinatorLayout;

    /**
     * Create intent to start {@link LoginActivity}
     *
     * @param aContext android context
     * @return intent
     */
    @NonNull
    public static Intent getStartIntent(@NonNull final Context aContext){
        return new Intent(aContext, LoginActivity.class);
    }


    @Override
    protected void onCreate(@Nullable final Bundle aSavedInstanceState){
        super.onCreate(aSavedInstanceState);
        App.get(this).getAppComponent()
                .loginComponentBuilder()
                .loginModule(new LoginModule())
                .build()
                .inject(this);
        initViews();
        mPresenter.attachView(this);
    }

    @Override
    public void showValidationErrors(@NonNull final LoginValidationErrors aLoginValidationErrors){
        setValidationError(mTilEmail, aLoginValidationErrors.getEmailError());
        setValidationError(mTilPassword, aLoginValidationErrors.getPasswordError());
    }

    @Override
    public void showLoadingProgress(){
        ProgressDialog.showDialog(getSupportFragmentManager());
    }

    @Override
    public void hideLoadingProgress(){
        ProgressDialog.hideDialog(getSupportFragmentManager());
    }

    @Override
    protected int getLayoutResource(){
        return R.layout.activity_login;
    }

    @Nullable
    @Override
    protected BasePresenter getPresenter(){
        return mPresenter;
    }

    @Override
    public void navigateToMainView(){
        startActivity(MainActivity.getStartIntent(this));
        finish();
    }

    @Override
    public void showError(@NonNull final String aError){
        Views.showErrorSnackbar(this, mCoordinatorLayout, aError);
    }

    /**
     * Handle click on login
     */
    @OnClick(R.id.btnLogin)
    public void onLoginClicked(){
        if(mListener != null){
            mListener.onLoginClicked();
        }
    }

    /**
     * Init views
     */
    private void initViews(){
        disposeOnDestroy(
                RxTextView.textChanges(mEtEmail)
                        .skip(SKIP_COUNT)
                        .map(CharSequence::toString)
                        .map(aEmail -> {
                            if(mListener != null){
                                return mListener.onEmailChanged(aEmail);
                            } else{
                                return Strings.EMPTY;
                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(aError -> setValidationError(mTilEmail, aError), this::handleValidationError),

                RxTextView.textChanges(mEtPassword)
                        .skip(SKIP_COUNT)
                        .map(CharSequence::toString)
                        .map(aPassword -> {
                            if(mListener != null){
                                return mListener.onPasswordChanged(aPassword);
                            } else{
                                return Strings.EMPTY;
                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(aError -> setValidationError(mTilPassword, aError), this::handleValidationError)
        );
        configureToolbar(mToolbar, getString(R.string.login_title), false);
    }

    /**
     * Handle validation error
     *
     * @param aThrowable error
     */
    private void handleValidationError(@NonNull Throwable aThrowable){
        Timber.e(aThrowable);
        showError(aThrowable.getLocalizedMessage());
    }

    @Override
    public void setListener(@Nullable final Actions aListener){
        mListener = aListener;
    }

    /**
     * Set validation error
     *
     * @param aTextInputLayout view
     * @param aError error message
     */
    private void setValidationError(@NonNull TextInputLayout aTextInputLayout, @Nullable String aError){
        aTextInputLayout.setErrorEnabled(!TextUtils.isEmpty(aError));
        if(!TextUtils.isEmpty(aError)){
            aTextInputLayout.setError(aError);
        }
    }
}
