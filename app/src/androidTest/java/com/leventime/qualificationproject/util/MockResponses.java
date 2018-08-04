package com.leventime.qualificationproject.util;

import android.support.annotation.Nullable;
import android.support.annotation.RawRes;

import com.google.gson.Gson;
import com.leventime.qualificationproject.TestApp;

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
     * @param aResourceId resource id
     * @param aClass class
     * @param <T> generic type
     * @return response object
     */
    public static <T> T getResponse(@RawRes int aResourceId, Class<T> aClass){
        String jsonString = getJsonString(aResourceId);
        return new Gson().fromJson(jsonString, aClass);
    }

    /**
     * Get json string from file in asset by file name
     *
     * @return json string
     */
    @Nullable
    private static String getJsonString(@RawRes int aResourceId){
        try{
            byte[] buffer;
            try(InputStream is = TestApp.getInstance().getResources().openRawResource(aResourceId)){
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
