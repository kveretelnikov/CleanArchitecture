package com.leventime.qualificationproject.util;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.base.network.exception.RetrofitException;

/**
 * Contain methods for work with errors
 *
 * @author kv
 */
public final class Errors{

    private Errors(){
    }

    /**
     * Get error message
     *
     * @param aThrowable error
     * @param aNetworkErrorDefault network error default
     * @param aErrorDefault error default
     * @return error message
     */
    @NonNull
    public static String getErrorMessage(@NonNull Throwable aThrowable, @NonNull String aNetworkErrorDefault, @NonNull String aErrorDefault){
        if(aThrowable instanceof RetrofitException){
            return NetworkUtil.getNetworkErrorMessage(aThrowable, aNetworkErrorDefault);
        } else{
            return aErrorDefault;
        }
    }

    /**
     * Get error message
     *
     * @param aThrowable error
     * @param aErrorDefault error default
     * @return error message
     */
    @NonNull
    public static String getErrorMessage(@NonNull Throwable aThrowable, @NonNull String aErrorDefault){
        if(aThrowable instanceof RetrofitException){
            return NetworkUtil.getNetworkErrorMessage(aThrowable, aErrorDefault);
        } else{
            return aErrorDefault;
        }
    }

}
