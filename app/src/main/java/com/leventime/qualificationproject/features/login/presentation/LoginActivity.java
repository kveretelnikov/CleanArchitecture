package com.leventime.qualificationproject.features.login.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;

import com.leventime.qualificationproject.App;
import com.leventime.qualificationproject.R;
import com.leventime.qualificationproject.base.core.BaseContract;
import com.leventime.qualificationproject.base.core.presentation.BaseActivity;
import com.leventime.qualificationproject.base.di.features.login.LoginModule;
import com.leventime.qualificationproject.features.login.LoginContract;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Show login
 *
 * @author kv
 */
public class LoginActivity extends BaseActivity implements LoginContract.View{

    @Inject
    LoginContract.Presenter mPresenter;
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
    @BindView(R.id.btnLogin)
    AppCompatButton mBtnLogin;
    @Nullable
    private Actions mListener;

    @Override
    protected void onCreate(@Nullable final Bundle aSavedInstanceState){
        super.onCreate(aSavedInstanceState);
        App.get(this).getAppComponent()
                .loginComponentBuilder()
                .loginModule(new LoginModule())
                .build()
                .inject(this);
        mPresenter.attachView(this);

    }

    @Override
    protected int getLayoutResource(){
        return R.layout.activity_login;
    }

    @Nullable
    @Override
    protected BaseContract.Presenter getPresenter(){
        return mPresenter;
    }

    @Override
    public void navigateToMainView(){

    }

    @Override
    public void showValidationErrors(@NonNull final LoginValidationErrors aLoginValidationErrors){

    }

    @Override
    public void showLoadingProgress(){

    }

    @Override
    public void hideLoadingProgress(){

    }

    @Override
    public void showError(@NonNull final String aError){

    }

    @Override
    public void setListener(@Nullable final Actions aListener){
        mListener = aListener;
    }
}
