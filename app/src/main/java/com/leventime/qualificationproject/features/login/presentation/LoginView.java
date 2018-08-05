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
        public void setLoginEnabled(final boolean aEnabled){

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
     * Set login enabled
     *
     * @param aEnabled true if login need set enabled
     */
    void setLoginEnabled(boolean aEnabled);

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
        String onEmailChanged(@Nullable String aEmail);

        /**
         * Handle [assword changed
         *
         * @param aPassword password
         * @return error string or empty string
         */
        @NonNull
        String onPasswordChanged(@Nullable String aPassword);

        /**
         * Handle login click
         */
        void onLoginClicked();
    }
}
