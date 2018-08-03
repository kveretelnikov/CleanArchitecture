package com.leventime.qualificationproject.features.login.di;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.api.AuthApi;
import com.leventime.qualificationproject.base.core.data.PreferenceManager;
import com.leventime.qualificationproject.base.core.data.ResourceManager;
import com.leventime.qualificationproject.base.core.presentation.Validator;
import com.leventime.qualificationproject.features.login.data.LoginRepository;
import com.leventime.qualificationproject.features.login.data.LoginRepositoryImpl;
import com.leventime.qualificationproject.features.login.domain.LoginInteractor;
import com.leventime.qualificationproject.features.login.domain.LoginInteractorImpl;
import com.leventime.qualificationproject.features.login.presentation.LoginPresenter;
import com.leventime.qualificationproject.features.login.presentation.LoginPresenterImpl;
import com.leventime.qualificationproject.features.login.presentation.LoginValidator;

import dagger.Module;
import dagger.Provides;

/**
 * Login module
 *
 * @author kv
 */
@LoginScope
@Module
public class LoginModule{

    @LoginScope
    @Provides
    @NonNull
    LoginValidator providesValidator(){
        return new Validator();
    }

    @LoginScope
    @Provides
    @NonNull
    LoginPresenter providesPresenter(@NonNull LoginInteractor aInteractor, @NonNull LoginValidator aValidator){
        return new LoginPresenterImpl(aInteractor, aValidator);
    }

    @LoginScope
    @Provides
    @NonNull
    LoginInteractor providesInteractor(@NonNull LoginRepository aRepository){
        return new LoginInteractorImpl(aRepository);
    }

    @LoginScope
    @Provides
    @NonNull
    LoginRepository providesRepository(@NonNull ResourceManager aResourceManager, @NonNull PreferenceManager aPreferenceManager, @NonNull AuthApi aApi){
        return new LoginRepositoryImpl(aResourceManager, aPreferenceManager, aApi);
    }

}
