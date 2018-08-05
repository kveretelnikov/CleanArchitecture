package com.leventime.qualificationproject.features.login.di;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.api.AuthApi;
import com.leventime.qualificationproject.base.core.data.PreferenceManager;
import com.leventime.qualificationproject.base.core.data.ResourceManager;
import com.leventime.qualificationproject.base.core.presentation.Cache;
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

    private final Cache mCache;

    /**
     * @param aCache cache
     */
    public LoginModule(@NonNull Cache aCache){
        mCache = aCache;
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
        return new LoginPresenterImpl(aInteractor, aValidator);
    }

    @NonNull
    @LoginScope
    @Provides
    LoginInteractor providesInteractor(@NonNull LoginRepository aRepository){
        return new LoginInteractorImpl(aRepository, mCache);
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
