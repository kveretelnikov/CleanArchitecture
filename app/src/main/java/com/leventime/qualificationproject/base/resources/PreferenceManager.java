package com.leventime.qualificationproject.base.resources;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.leventime.qualificationproject.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Manage preferences in application
 *
 * @author kv
 */
public final class PreferenceManager{

    private SharedPreferences mPreference;

    /**
     * @param aContext android context
     */
    public PreferenceManager(@NonNull Context aContext){
        String appName = aContext.getResources().getString(R.string.app_name);
        mPreference = aContext.getSharedPreferences(appName, MODE_PRIVATE);
    }
}
