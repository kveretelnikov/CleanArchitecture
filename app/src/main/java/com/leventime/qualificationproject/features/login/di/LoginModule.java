package com.leventime.qualificationproject.features.login.di;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.api.AuthApi;
import com.leventime.qualificationproject.base.core.data.PreferenceManager;
import com.leventime.qualificationproject.base.core.data.ResourceManager;
import com.leventime.qualificationproject.base.core.presentation.Validator;
import com.leventime.qualificationproject.base.room.AppDatabase;
import com.leventime.qualificationproject.base.room.dao.UserDao;
import com.leventime.qualificationproject.features.login.data.LoginRepository;
import com.leventime.qualificationproject.features.login.data.LoginRepositoryImpl;
import com.leventime.qualificationproject.features.login.domain.LoginInteractor;
import com.leventime.qualificationproject.features.login.domain.LoginInteractorImpl;
import com.leventime.qualificationproject.features.login.presentation.LoginPresenter;
import com.leventime.qualificationproject.features.login.presentation.LoginPresenterImpl;
import com.leventime.qualificationproject.features.login.presentation.LoginValidator;
import com.leventime.qualificationproject.features.login.presentation.states.LoginBaseState;

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

    private final LoginBaseState.LoginStateType mType;

    /**
     * @param aType current presenter state type
     */
    public LoginModule(@NonNull LoginBaseState.LoginStateType aType){
        mType = aType;
    }

    @NonNull
    @LoginScope
    @Provides
    LoginValidator providesValidator(){
        return new Validator();
    }

    @NonNull
    @LoginScope
    @Provides
    LoginPresenter providesPresenter(@NonNull LoginInteractor aInteractor, LoginValidator aValidator){
        return new LoginPresenterImpl(aInteractor, aValidator, LoginBaseState.getState(mType));
    }

    @NonNull
    @LoginScope
    @Provides
    LoginInteractor providesInteractor(@NonNull LoginRepository aRepository){
        return new LoginInteractorImpl(aRepository);
    }

    @NonNull
    @LoginScope
    @Provides
    UserDao providesUserDao(@NonNull AppDatabase aAppDatabase){
        return aAppDatabase.userDao();
    }

    @NonNull
    @LoginScope
    @Provides
    LoginRepository providesRepository(@NonNull ResourceManager aResourceManager, @NonNull PreferenceManager aPreferenceManager, @NonNull AuthApi aApi, @NonNull UserDao aUserDao){
        return new LoginRepositoryImpl(aResourceManager, aPreferenceManager, aApi, aUserDao);
    }

}
