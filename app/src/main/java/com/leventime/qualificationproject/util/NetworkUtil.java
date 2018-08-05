package com.leventime.qualificationproject.util;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.api.ApiError;
import com.leventime.qualificationproject.base.network.exception.RetrofitException;

import static com.leventime.qualificationproject.util.Constants.UNDEFINED_VALUE;

/**
 * Contain methods for work with network
 *
 * @author kv
 */
public final class NetworkUtil{

    private NetworkUtil(){
    }

    /**
     * Get http code from response exception
     *
     * @param aThrowable exception of response
     * @return http code
     */
    public static int getHttpCode(@NonNull Throwable aThrowable){
        if(aThrowable instanceof RetrofitException){
            RetrofitException retrofitException = (RetrofitException) aThrowable;
            return retrofitException.getHttpCode();
        }
        return UNDEFINED_VALUE;
    }

    /**
     * Get network error message
     *
     * @param aThrowable exception
     * @param aError default error message
     * @return error message
     */
    @NonNull
    public static String getNetworkErrorMessage(@NonNull Throwable aThrowable, @NonNull String aError){
        if(aThrowable instanceof RetrofitException){
            RetrofitException retrofitException = (RetrofitException) aThrowable;
            if(retrofitException.getKind() == RetrofitException.Kind.HTTP){
                ApiError apiError = retrofitException.convertTo(ApiError.class);
                return apiError != null ? apiError.getMessage() : aError;
            } else{
                return aError;
            }
        } else{
            return aError;
        }
    }
}
