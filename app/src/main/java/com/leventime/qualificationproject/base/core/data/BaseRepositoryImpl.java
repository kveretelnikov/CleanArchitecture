package com.leventime.qualificationproject.base.core.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Provide base data
 *
 * @author kv
 */
public class BaseRepositoryImpl implements BaseRepository{

    private final ResourceManager mResourceManager;

    /**
     * @param aResourceManager resource manager
     */
    public BaseRepositoryImpl(@NonNull ResourceManager aResourceManager){
        mResourceManager = aResourceManager;
    }

    @NonNull
    @Override
    public String getStringResource(final int aResourceId, @Nullable final Object... aFormatArgs){
        return mResourceManager.getStringResource(aResourceId, aFormatArgs);
    }
}
