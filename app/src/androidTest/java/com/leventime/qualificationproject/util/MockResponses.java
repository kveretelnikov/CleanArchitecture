package com.leventime.qualificationproject.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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
     * @param aResourceName resource name
     * @param aClass class
     * @param <T> generic type
     * @return response object
     */
    public static <T> T getResponse(@NonNull String aResourceName, Class<T> aClass){
        String jsonString = getJsonString(aResourceName);
        return new Gson().fromJson(jsonString, aClass);
    }

    /**
     * Get json string from file in resources by file name
     *
     * @param aResourceName resource name
     * @return json string
     */
    @Nullable
    private static String getJsonString(@NonNull String aResourceName){
        try{
            byte[] buffer;
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            try(InputStream is = classloader.getResourceAsStream(aResourceName)){
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
