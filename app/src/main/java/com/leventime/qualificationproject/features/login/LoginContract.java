package com.leventime.qualificationproject.features.login;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.leventime.qualificationproject.base.core.BaseContract;
import com.leventime.qualificationproject.base.core.presentation.BaseViewState;
import com.leventime.qualificationproject.features.login.domain.LoginDomain;
import com.leventime.qualificationproject.features.login.presentation.LoginValidationErrors;

import io.reactivex.Completable;

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
         * Show validation errors
         *
         * @param aLoginValidationErrors login validation errors
         */
        void showValidationErrors(@NonNull LoginValidationErrors aLoginValidationErrors);

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
    interface Interactor extends BaseContract.Interactor{

        /**
         * Set email
         *
         * @param aEmail email
         */
        void setEmail(@Nullable String aEmail);

        /**
         * Set password
         *
         * @param aPassword password
         */
        void setPassword(@Nullable String aPassword);

        /**
         * Get login data
         *
         * @return login data
         */
        @NonNull
        LoginDomain getLoginData();

        /**
         * Login
         *
         * @return success
         */
        @NonNull
        Completable login();
    }

    /**
     * Login repository
     */
    interface Repository extends BaseContract.Repository{

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
         * Show validation errors
         *
         * @param aLoginValidationErrors login validation errors
         */
        void showValidationErrors(@NonNull LoginValidationErrors aLoginValidationErrors);

        /**
         * Navigate to main view
         */
        void navigateToMainView();
    }

}
