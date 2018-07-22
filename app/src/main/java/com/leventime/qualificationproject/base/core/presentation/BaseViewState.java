package com.leventime.qualificationproject.base.core.presentation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;

/**
 * Manage view state
 *
 * @author kv
 */
public interface BaseViewState{

    /**
     * Manage view progress
     */
    interface ProgressSupport{

        /**
         * Show progress
         */
        void showProgress();

        /**
         * Hide progress
         */
        void hideProgress();
    }

    /**
     * Manage view errors
     */
    interface ErrorsSupport{

        /**
         * Show error message
         *
         * @param aError error message
         */
        void showError(@NonNull String aError);
    }

    /**
     * Manage view errors with hide error
     */
    interface ErrorsSupportExtended extends ErrorsSupport{

        /**
         * Hide error
         */
        void hideError();
    }

    /**
     * Manage view messages
     */
    interface MessageSupport{

        /**
         * Show message
         *
         * @param aMessage message
         */
        void showMessage(@NonNull String aMessage);
    }

    /**
     * Manage validate fields
     *
     * @param <E> validation errors class
     */
    interface ValidateSupport<E>{

        /**
         * Set error
         *
         * @param aView view
         * @param aError error
         */
        void setValidateView(@NonNull TextInputLayout aView, @NonNull String aError);

        /**
         * Show validate errors
         *
         * @param aErrors errors
         */
        void showValidateErrors(@NonNull E aErrors);
    }

    /**
     * Manage keyboard
     */
    interface KeyboardSupport{

        /**
         * Hide keyboard
         */
        void hideKeyboard();
    }

    /**
     * Navigation support
     */
    interface NavigationSupport{

        /**
         * Close view
         */
        void close();
    }

    /**
     * Support for a view generated actions
     *
     * @param <T> view actions class
     */
    interface ActionsSupport<T>{

        /**
         * Add view actions listener
         *
         * @param aListener view actions listener
         */
        void setListener(@Nullable T aListener);
    }
}
