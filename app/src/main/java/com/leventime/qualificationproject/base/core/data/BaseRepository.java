package com.leventime.qualificationproject.base.core.data;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.base.core.BaseContract;
import com.leventime.qualificationproject.base.resources.ResourceManager;

/**
 * Provide base data
 *
 * @author kv
 */
public class BaseRepository implements BaseContract.Repository{

    private final ResourceManager mResourceManager;

    public BaseRepository(@NonNull ResourceManager aResourceManager){
        mResourceManager = aResourceManager;
    }

    @Override
    public String getStringResource(final int aResourceId, @NonNull final Object... aFormatArgs){
        return mResourceManager.getStringResource(aResourceId, aFormatArgs);
    }
}
