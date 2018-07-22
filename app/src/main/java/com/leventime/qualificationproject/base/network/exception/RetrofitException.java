package com.leventime.qualificationproject.base.network.exception;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import timber.log.Timber;

/**
 * This class is necessary in order to obtain value object error, http error codes
 *
 * @author kv
 */
public class RetrofitException extends RuntimeException{

    private final String mUrl;
    private final Kind mKind;
    private final transient Retrofit mRetrofit;
    private final String mErrorBody;
    private final Integer mHttpCode;

    /**
     * @param aMessage message
     * @param aUrl url
     * @param aResponse response data
     * @param aKind type exception
     * @param aException exception
     * @param aRetrofit retrofit instance
     */
    public RetrofitException(@NonNull String aMessage,
                             @Nullable String aUrl,
                             @Nullable Response aResponse,
                             @NonNull Kind aKind,
                             @Nullable Throwable aException,
                             @Nullable Retrofit aRetrofit){
        super(aMessage, aException);
        this.mUrl = aUrl;
        String responseErrorBody = null;
        if(aResponse != null){
            try{
                responseErrorBody = aResponse.errorBody().string();
            } catch(IOException | NullPointerException aE){
                Timber.d(aE);
            }
            mHttpCode = aResponse.raw().code();
        } else{
            mHttpCode = null;
        }
        mErrorBody = responseErrorBody;
        mKind = aKind;
        mRetrofit = aRetrofit;
    }

    /**
     * @param aUrl url
     * @param aResponse response data
     * @param aRetrofit retrofit instance
     * @return retrofit exception
     */
    @NonNull
    public static RetrofitException httpError(@NonNull String aUrl, @NonNull Response aResponse, @NonNull Retrofit aRetrofit){
        return new RetrofitException(aResponse.message(), aUrl, aResponse, Kind.HTTP, null, aRetrofit);
    }

    /**
     * @param aException io exception
     * @return retrofit exception
     */
    @NonNull
    public static RetrofitException networkError(@NonNull IOException aException){
        return new RetrofitException(aException.getMessage(), null, null, Kind.NETWORK, aException, null);
    }

    /**
     * @param aException exception
     * @return retrofit exception
     */
    @NonNull
    public static RetrofitException unexpectedError(@NonNull Throwable aException){
        return new RetrofitException(aException.getMessage(), null, null, Kind.UNEXPECTED, aException, null);
    }

    /**
     * The request URL which produced the error.
     */
    public String getUrl(){
        return mUrl;
    }

    /**
     * @return http error code
     */
    @Nullable
    public Integer getHttpCode(){
        return mHttpCode;
    }

    /**
     * The event mKind which triggered this error.
     */
    public Kind getKind(){
        return mKind;
    }

    /**
     * The Retrofit this request was executed on
     */
    public Retrofit getRetrofit(){
        return mRetrofit;
    }

    /**
     * Convert error body to another object with provided class
     *
     * @param aType conversion target class to convert to
     */
    public <T> T convertTo(@NonNull Class<T> aType){
        String body;
        if(mKind == Kind.HTTP){
            body = mErrorBody;
        } else{
            body = getMessage();
        }
        if(body == null){
            return null;
        }
        Converter<ResponseBody, T> converter = mRetrofit.responseBodyConverter(aType, new Annotation[0]);
        try{
            return converter.convert(ResponseBody.create(MediaType.parse("application/json"), body));
        } catch(IOException aE){
            Timber.v("Unable to convertTo %s to %s", body, aType.getSimpleName());
            return null;
        }
    }

    /**
     * Identifies the event mKind which triggered a {@link RetrofitException}.
     */
    public enum Kind{
        /**
         * An {@link IOException} occurred while communicating to the server.
         */
        NETWORK,
        /**
         * A non-200 HTTP status code was received from the server.
         */
        HTTP,
        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        UNEXPECTED
    }

}
