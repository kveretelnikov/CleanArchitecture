package com.leventime.qualificationproject.base.resources;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.leventime.qualificationproject.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Manage preferences in application
 *
 * @author kv
 */
public final class PreferenceManager{

    private static final String PREF_TOKEN = "token";
    private SharedPreferences mPreference;

    /**
     * @param aContext android context
     */
    public PreferenceManager(@NonNull Context aContext){
        String appName = aContext.getResources().getString(R.string.app_name);
        mPreference = aContext.getSharedPreferences(appName, MODE_PRIVATE);
    }

    /**
     * Save token
     *
     * @param aToken token
     */
    public void saveToken(@NonNull String aToken){
        mPreference.edit().putString(PREF_TOKEN, aToken).apply();
    }

    /**
     * Get token
     *
     * @return token
     */
    @Nullable
    public String getToken(){
        return mPreference.getString(PREF_TOKEN, null);
    }

    /**
     * Clear all data
     */
    public void clearAll(){
        mPreference.edit().clear().apply();
    }
}
