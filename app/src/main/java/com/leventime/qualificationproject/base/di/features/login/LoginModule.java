package com.leventime.qualificationproject.base.di.features.login;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.features.login.presentation.LoginValidator;
import com.leventime.qualificationproject.base.core.presentation.Validator;
import com.leventime.qualificationproject.base.network.Api;
import com.leventime.qualificationproject.base.resources.ResourceManager;
import com.leventime.qualificationproject.features.login.LoginContract;
import com.leventime.qualificationproject.features.login.data.LoginRepository;
import com.leventime.qualificationproject.features.login.domain.LoginInteractor;
import com.leventime.qualificationproject.features.login.presentation.LoginPresenter;

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
    LoginValidator providesLoginValidator(){
        return new Validator();
    }

    @LoginScope
    @Provides
    @NonNull
    LoginContract.Presenter providesPresenter(@NonNull LoginContract.Interactor aInteractor, @NonNull LoginValidator aValidator){
        return new LoginPresenter(aInteractor, aValidator);
    }

    @LoginScope
    @Provides
    @NonNull
    LoginContract.Interactor providesInteractor(@NonNull LoginContract.Repository aRepository){
        return new LoginInteractor(aRepository);
    }

    @LoginScope
    @Provides
    @NonNull
    LoginContract.Repository providesRepository(@NonNull ResourceManager aResourceManager, @NonNull Api aApi){
        return new LoginRepository(aResourceManager, aApi);
    }

}
