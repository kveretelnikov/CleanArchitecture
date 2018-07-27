package com.leventime.qualificationproject.features.login;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.base.core.BaseContract;
import com.leventime.qualificationproject.base.core.presentation.BaseViewState;

/**
 * Manage login
 *
 * @author kv
 */
public interface LoginContract{

    /**
     * Login view
     */
    interface View extends BaseContract.View,
            BaseViewState.ErrorsSupport,
            BaseViewState.ProgressSupport,
            BaseViewState.ActionsSupport<View.Actions>{

        /**
         * Navigate to main view
         */
        void navigateToMainView();

        /**
         * View generated actions
         */
        interface Actions{

            /**
             * Handle email changed
             *
             * @param aEmail email
             * @return error string or empty string
             */
            @NonNull
            String onEmailChanged(@NonNull String aEmail);

            /**
             * Handle [assword changed
             *
             * @param aPassword password
             * @return error string or empty string
             */
            @NonNull
            String onPasswordChanged(@NonNull String aPassword);

            /**
             * Handle login click
             */
            void onLoginClicked();
        }
    }

    /**
     * Login presenter
     */
    interface Presenter extends BaseContract.Presenter<View>, View.Actions{

    }

    /**
     * Login interactor
     */
    interface Interactor{

    }

    /**
     * Login repository
     */
    interface Repository{

    }

    /**
     * Login page object
     */
    interface PageObject extends BaseContract.PageObject<View>{

        /**
         * Show loading progress
         */
        void showLoadingProgress();

        /**
         * Hide loading progress
         */
        void hideLoadingProgress();

        /**
         * Show error
         *
         * @param aThrowable error
         */
        void showError(@NonNull Throwable aThrowable);

        /**
         * Navigate to main view
         */
        void navigateToMainView();
    }

}
