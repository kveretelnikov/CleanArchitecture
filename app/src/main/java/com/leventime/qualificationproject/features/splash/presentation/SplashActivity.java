package com.leventime.qualificationproject.features.splash.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.leventime.qualificationproject.App;
import com.leventime.qualificationproject.R;
import com.leventime.qualificationproject.base.core.BaseContract;
import com.leventime.qualificationproject.base.core.presentation.BaseActivity;
import com.leventime.qualificationproject.base.di.features.splash.SplashModule;
import com.leventime.qualificationproject.features.login.presentation.LoginActivity;
import com.leventime.qualificationproject.features.main.presentation.MainActivity;
import com.leventime.qualificationproject.features.splash.SplashContract;

import javax.inject.Inject;

/**
 * Manage first routing in app
 *
 * @author kv
 */
public class SplashActivity extends BaseActivity implements SplashContract.View{

    @Inject
    SplashContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable final Bundle aSavedInstanceState){
        super.onCreate(aSavedInstanceState);
        App.get(this).getAppComponent()
                .splashComponentBuilder()
                .splashModule(new SplashModule())
                .build()
                .inject(this);
        mPresenter.attachView(this);
    }

    @Override
    protected int getLayoutResource(){
        return R.layout.activity_splash;
    }

    @Nullable
    @Override
    protected BaseContract.Presenter getPresenter(){
        return mPresenter;
    }

    @Override
    public void navigateToMainView(){
        startActivity(MainActivity.getStartIntent(this));
        finish();
    }

    @Override
    public void navigateToLoginView(){
        startActivity(LoginActivity.getStartIntent(this));
        finish();
    }
}
