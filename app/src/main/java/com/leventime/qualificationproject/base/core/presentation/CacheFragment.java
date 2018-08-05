package com.leventime.qualificationproject.base.core.presentation;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.leventime.qualificationproject.BuildConfig;

/**
 * Describe cache
 *
 * @author kv
 */
public class CacheFragment extends Fragment implements Cache{

    private static final String EXTRA_DATA = BuildConfig.APPLICATION_ID + ".EXTRA_DATA";
    private Parcelable mData;

    /**
     * Get instance {@link CacheFragment}
     *
     * @param aData data
     * @return {@link CacheFragment}
     */
    @NonNull
    public static CacheFragment newInstance(@Nullable Parcelable aData){
        CacheFragment fragment = new CacheFragment();
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_DATA, aData);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Get instance {@link CacheFragment}
     *
     * @return {@link CacheFragment}
     */
    @NonNull
    public static CacheFragment newInstance(){
        return CacheFragment.newInstance(null);
    }

    @Override
    public void onCreate(@Nullable final Bundle aSavedInstanceState){
        super.onCreate(aSavedInstanceState);
        setRetainInstance(true);
        if(mData == null){
            mData = aSavedInstanceState == null ? null : aSavedInstanceState.getParcelable(EXTRA_DATA);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull final Bundle aOutState){
        aOutState.putParcelable(EXTRA_DATA, mData);
        super.onSaveInstanceState(aOutState);
    }

    @Override
    public void saveCacheData(@Nullable final Parcelable aData){
        mData = aData;
    }

    @Nullable
    @Override
    public Parcelable getCacheData(){
        return mData;
    }

    @Override
    public boolean isCacheExist(){
        return mData != null;
    }
}
