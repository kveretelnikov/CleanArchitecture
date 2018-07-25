package com.leventime.qualificationproject.features.login.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.leventime.qualificationproject.App;
import com.leventime.qualificationproject.R;
import com.leventime.qualificationproject.base.core.BaseContract;
import com.leventime.qualificationproject.base.core.presentation.BaseActivity;
import com.leventime.qualificationproject.base.di.features.login.LoginModule;
import com.leventime.qualificationproject.features.login.LoginContract;

import javax.inject.Inject;

/**
 * Show login
 *
 * @author kv
 */
public class LoginActivity extends BaseActivity implements LoginContract.View{

    @Inject
    LoginContract.Presenter mPresenter;

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
}
