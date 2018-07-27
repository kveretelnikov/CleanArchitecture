package com.leventime.qualificationproject.features.login;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.base.core.BaseContract;
import com.leventime.qualificationproject.base.core.presentation.BaseViewState;

/**
 * @author kv
 */
public interface LoginContract{

    interface View extends BaseContract.View, BaseViewState.ErrorsSupport, BaseViewState.ProgressSupport{

    }

    interface Presenter extends BaseContract.Presenter<View>{

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

    interface Interactor{

    }

    interface Repository{

    }

}
