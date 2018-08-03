package com.leventime.qualificationproject.base.core.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import javax.inject.Inject;

/**
 * Manage resources in application
 *
 * @author kv
 */
public class ResourceManagerImpl implements ResourceManager{

    private final Context mContext;

    /**
     * @param aContext context
     */
    @Inject
    public ResourceManagerImpl(@NonNull Context aContext){
        mContext = aContext;
    }

    /**
     * Get string by android resource id
     *
     * @param aResourceId resource id
     * @return string
     */
    @NonNull
    @Override
    public String getStringResource(@StringRes int aResourceId, @Nullable Object... aFormatArgs){
        return mContext.getString(aResourceId, aFormatArgs);
    }
}
