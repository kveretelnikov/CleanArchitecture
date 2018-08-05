package com.leventime.qualificationproject.base.core.presentation;

import android.os.Parcelable;
import android.support.annotation.Nullable;

/**
 * Describe cache
 *
 * @author kv
 */
public interface Cache{

    /**
     * Save cache data
     *
     * @param aData data
     */
    void saveCacheData(@Nullable Parcelable aData);

    @Nullable
    Parcelable getCacheData();

    /**
     * Check that cache exist
     *
     * @return true if cache exist
     */
    boolean isCacheExist();
}
