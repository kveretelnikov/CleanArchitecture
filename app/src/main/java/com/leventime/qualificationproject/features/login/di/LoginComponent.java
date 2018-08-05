package com.leventime.qualificationproject.features.login.di;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.features.login.presentation.LoginActivity;

import dagger.Subcomponent;

/**
 * Login component
 *
 * @author kv
 */
@LoginScope
@Subcomponent(modules = {LoginModule.class})
public interface LoginComponent{

    void inject(@NonNull LoginActivity aActivity);

    /**
     * Login component builder
     */
    @Subcomponent.Builder
    interface Builder{

        @NonNull
        Builder loginModule(@NonNull LoginModule aModule);

        @NonNull
        LoginComponent build();
    }
}
