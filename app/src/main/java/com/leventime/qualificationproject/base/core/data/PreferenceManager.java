package com.leventime.qualificationproject.base.core.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Manage preferences in application
 *
 * @author kv
 */
public interface PreferenceManager{

    /**
     * Save token
     *
     * @param aToken token
     */
    void saveToken(@NonNull String aToken);

    /**
     * Get token
     *
     * @return token
     */
    @Nullable
    String getToken();

    /**
     * Clear all data
     */
    void clearAll();
}
