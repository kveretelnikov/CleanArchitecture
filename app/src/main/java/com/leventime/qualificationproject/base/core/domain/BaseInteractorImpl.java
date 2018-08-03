package com.leventime.qualificationproject.base.core.domain;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.base.core.data.BaseRepository;

/**
 * Provide base data
 *
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

    @Override
    public String getStringResource(final int aResourceId, @NonNull final Object... aFormatArgs){
        return mRepository.getStringResource(aResourceId, aFormatArgs);
    }
}
