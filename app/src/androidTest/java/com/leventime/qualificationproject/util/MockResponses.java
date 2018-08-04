package com.leventime.qualificationproject.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

import timber.log.Timber;

/**
 * Contain methods for obtain mock responses
 *
 * @author kv
 */
public final class MockResponses{

    private MockResponses(){
    }

    /**
     * Get response object from json file
     *
     * @param aContext android context
     * @param aResourceId resource id
     * @param aClass class
     * @param <T> generic type
     * @return response object
     */
    public static <T> T getResponse(@NonNull Context aContext, @RawRes int aResourceId, Class<T> aClass){
        String jsonString = getJsonString(aContext, aResourceId);
        return new Gson().fromJson(jsonString, aClass);
    }

    /**
     * Get json string from file in asset by file name
     *
     * @param aContext android context
     * @param aResourceId resource id
     * @return json string
     */
    @Nullable
    private static String getJsonString(@NonNull Context aContext, @RawRes int aResourceId){
        try{
            byte[] buffer;
            try(InputStream is = aContext.getResources().openRawResource(aResourceId)){
                int size = is.available();
                buffer = new byte[size];
                is.read(buffer);
                is.close();
            }
            return new String(buffer, "UTF-8");
        } catch(IOException aE){
            Timber.e(aE);
            return null;
        }
    }
}
