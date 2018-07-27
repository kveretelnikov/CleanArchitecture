package com.leventime.qualificationproject.base.core.data;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.base.core.BaseContract;

/**
 * Provide base data
 *
 * @author kv
 */
public class BaseInteractor<REPOSITORY extends BaseContract.Repository> implements BaseContract.Interactor{

    private final BaseContract.Repository mRepository;

    /**
     * @param aRepository repository
     */
    public BaseInteractor(@NonNull REPOSITORY aRepository){
        mRepository = aRepository;
    }

    @Override
    public String getStringResource(final int aResourceId, @NonNull final Object... aFormatArgs){
        return mRepository.getStringResource(aResourceId, aFormatArgs);
    }
}
