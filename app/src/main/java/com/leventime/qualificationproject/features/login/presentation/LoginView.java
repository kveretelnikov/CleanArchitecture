package com.leventime.qualificationproject.features.login.presentation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.leventime.qualificationproject.base.core.presentation.BaseView;
import com.leventime.qualificationproject.base.core.presentation.BaseViewState;

/**
 * Show login
 *
 * @author kv
 */
public interface LoginView extends BaseView,
        BaseViewState.ErrorsSupport,
        BaseViewState.ProgressSupport,
        BaseViewState.ActionsSupport<LoginView.Actions>{

    /**
     * Empty login view
     */
    LoginView EMPTY = new LoginView(){

        @Override
        public void navigateToMainView(){

        }

        @Override
        public void showValidationErrors(@NonNull final LoginValidationErrors aLoginValidationErrors){

        }

        @Override
        public void setListener(@Nullable final Actions aListener){

        }

        @Override
        public void showError(@NonNull final String aError){

        }

        @Override
        public void showLoadingProgress(){

        }

        @Override
        public void hideLoadingProgress(){

        }
    };

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
