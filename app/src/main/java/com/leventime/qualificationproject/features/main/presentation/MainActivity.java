package com.leventime.qualificationproject.features.main.presentation;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.leventime.qualificationproject.R;
import com.leventime.qualificationproject.base.core.BaseContract;
import com.leventime.qualificationproject.base.core.presentation.BaseActivity;

/**
 * Show main
 *
 * @author kv
 */
public class MainActivity extends BaseActivity{

    /**
     * Create intent to start {@link MainActivity}
     *
     * @param aContext android context
     * @return intent
     */
    @NonNull
    public static Intent getStartIntent(@NonNull final Context aContext){
        return new Intent(aContext, MainActivity.class);
    }

    @Override
    protected int getLayoutResource(){
        return R.layout.activity_main;
    }

    @Nullable
    @Override
    protected BaseContract.Presenter getPresenter(){
        return null;
    }
}
