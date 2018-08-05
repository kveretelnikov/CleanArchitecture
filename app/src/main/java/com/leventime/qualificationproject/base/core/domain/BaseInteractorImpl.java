package com.leventime.qualificationproject.base.core.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.leventime.qualificationproject.base.core.data.BaseRepository;

/**
 * Provide base data
 *
 * @param <REPOSITORY> repository
 * @author kv
 */
public class BaseInteractorImpl<REPOSITORY extends BaseRepository> implements BaseInteractor{

    protected final REPOSITORY mRepository;

    /**
     * @param aRepository repository
     */
    public BaseInteractorImpl(@NonNull REPOSITORY aRepository){
        mRepository = aRepository;
    }

    @NonNull
    @Override
    public String getStringResource(final int aResourceId, @Nullable final Object... aFormatArgs){
        return mRepository.getStringResource(aResourceId, aFormatArgs);
    }
}
