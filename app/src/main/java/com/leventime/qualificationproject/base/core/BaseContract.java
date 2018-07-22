package com.leventime.qualificationproject.base.core;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

/**
 * Base contract
 *
 * @author kv
 */
public interface BaseContract{

    /**
     * Presenter determines what view should do and proceed its input
     *
     * @param <V> view
     */
    interface Presenter<V extends View>{

        /**
         * Attach view
         *
         * @param aView view
         */
        void attachView(@NonNull V aView);

        /**
         * Detach view
         */
        void detachView();

        /**
         * Check that view is attached
         *
         * @return true if view attached
         */
        boolean isViewAttached();
    }

    /**
     * Show data and reacts to user actions
     */
    interface View{

    }

    /**
     * Repository
     */
    interface Repository{

        /**
         * Get string resource
         *
         * @param aResourceId resource id
         * @param aFormatArgs format args
         * @return string resource
         */
        String getStringResource(@StringRes int aResourceId, @NonNull Object... aFormatArgs);
    }
}
