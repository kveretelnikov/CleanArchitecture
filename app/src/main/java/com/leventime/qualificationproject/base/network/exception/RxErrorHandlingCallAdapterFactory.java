package com.leventime.qualificationproject.base.network.exception;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import timber.log.Timber;

/**
 * Handle error api in throwable class
 *
 * @author kv
 */
public final class RxErrorHandlingCallAdapterFactory extends CallAdapter.Factory{

    private final RxJava2CallAdapterFactory mOriginal;

    private RxErrorHandlingCallAdapterFactory(){
        mOriginal = RxJava2CallAdapterFactory.create();
    }

    public static CallAdapter.Factory create(){
        return new RxErrorHandlingCallAdapterFactory();
    }

    @Override
    public CallAdapter<?, ?> get(@NonNull Type returnType, @NonNull Annotation[] annotations, @NonNull Retrofit retrofit){
        return new RxCallAdapterWrapper(retrofit, mOriginal.get(returnType, annotations, retrofit));
    }

    /**
     * RxCallAdapter wrapper
     *
     * @param <R> value
     */
    private static class RxCallAdapterWrapper<R> implements CallAdapter<R, Object>{

        private final Retrofit retrofit;
        private final CallAdapter<R, Object> wrapped;

        RxCallAdapterWrapper(@NonNull Retrofit retrofit, @NonNull CallAdapter<R, Object> wrapped){
            this.retrofit = retrofit;
            this.wrapped = wrapped;
        }

        @Override
        public Type responseType(){
            return wrapped.responseType();
        }

        @Override
        public Object adapt(@NonNull Call<R> aCall){
            Object result = wrapped.adapt(aCall);
            if(result instanceof Single){
                return ((Single) result).onErrorResumeNext(throwable -> Single.error(asRetrofitException((Throwable) throwable)));
            }
            if(result instanceof Observable){
                return ((Observable) result).onErrorResumeNext(throwable -> {
                    return Observable.error(asRetrofitException((Throwable) throwable));
                });
            }

            if(result instanceof Completable){
                return ((Completable) result).onErrorResumeNext(throwable -> Completable.error(asRetrofitException(throwable)));
            }

            return result;
        }

        /**
         * Create exception with type {@link RetrofitException} from {@link Throwable}
         *
         * @param throwable error
         * @return {@link RetrofitException}
         */
        @NonNull
        private RetrofitException asRetrofitException(@NonNull Throwable throwable){
            Timber.d("asRetrofitException");
            // We had non-200 http error
            if(throwable instanceof HttpException){
                HttpException httpException = (HttpException) throwable;
                Response response = httpException.response();
                return RetrofitException.httpError(response.raw().request().url().toString(), response, retrofit);
            }
            // A network error happened
            if(throwable instanceof IOException){
                return RetrofitException.networkError((IOException) throwable);
            }

            // We don't know what happened. We need to simply convertTo to an unknown error

            return RetrofitException.unexpectedError(throwable);
        }
    }
}
