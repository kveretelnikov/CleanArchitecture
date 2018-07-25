package com.leventime.qualificationproject.base.di.features.login;

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

    void inject(LoginActivity aActivity);

    /**
     * Login component builder
     */
    @Subcomponent.Builder
    interface Builder{

        Builder loginModule(LoginModule aModule);

        LoginComponent build();
    }
}
