package com.leventime.qualificationproject.features.splash;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.base.core.BaseContract;
import com.leventime.qualificationproject.base.core.presentation.BaseViewState;

import io.reactivex.Single;

/**
 * Manage of routing in app
 *
 * @author kv
 */
public interface SplashContract{

    /**
     * Splash view
     */
    interface View extends BaseContract.View, BaseViewState.ErrorsSupport{

        /**
         * Navigate to main view
         */
        void navigateToMainView();

        /**
         * Navigate to login view
         */
        void navigateToLoginView();
    }

    /**
     * Splash presenter
     */
    interface Presenter extends BaseContract.Presenter<View>{

    }

    /**
     * Splash interactor
     */
    interface Interactor{

        /**
         * Check that user logged
         *
         * @return true if user logged
         */
        @NonNull
        Single<Boolean> isUserLogged();
    }

    /**
     * Splash repository
     */
    interface Repository{

        /**
         * Check that user logged
         *
         * @return true if user logged
         */
        Single<Boolean> isUserLogged();
    }

    /**
     * Splash page object
     */
    interface PageObject extends BaseContract.PageObject<View>, BaseViewState.ErrorsSupport{

        /**
         * Navigate to main view
         */
        void navigateToMainView();

        /**
         * Navigate to login view
         */
        void navigateToLoginView();
    }

}
