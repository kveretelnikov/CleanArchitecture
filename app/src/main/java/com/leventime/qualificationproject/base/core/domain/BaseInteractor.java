package com.leventime.qualificationproject.base.core.domain;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

/**
 * Base interactor
 *
 * @author kv
 */
public interface BaseInteractor{

    /**
     * Get string resource
     *
     * @param aResourceId resource id
     * @param aFormatArgs format args
     * @return string resource
     */
    String getStringResource(@StringRes int aResourceId, @NonNull Object... aFormatArgs);
}
