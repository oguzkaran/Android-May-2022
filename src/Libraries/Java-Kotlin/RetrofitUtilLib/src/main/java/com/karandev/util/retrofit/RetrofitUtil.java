/*----------------------------------------------------------------------
	FILE        : RetrofitUtil.java
	AUTHOR      : Oğuz Karan
	LAST UPDATE : 30.12.2022

	RetrofitUtil class for "retrofit"

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package com.karandev.util.retrofit;

import java.util.function.BiConsumer;
import java.util.stream.Stream;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitUtil {
    private RetrofitUtil()
    {}

    public static Retrofit createRetrofit(String baseUrl)
    {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();
    }

    public static Retrofit createRetrofitWithLogging(String baseUrl)
    {
        var interceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

        return createRetrofitWithInterceptors(baseUrl, interceptor);
    }

    public static Retrofit createRetrofitWithInterceptors(String baseUrl, Interceptor...interceptors)
    {
        var builder = new OkHttpClient.Builder();

        Stream.of(interceptors).forEach(builder::addInterceptor);

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
    }

    public static <T, C extends Call<T>> void enqueue(C call, BiConsumer<Call<T>, Response<T>> responseConsumer, BiConsumer<Call<T>, Throwable> failConsumer)
    {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(@NotNull Call<T> call, @NotNull Response<T> response)
            {
                responseConsumer.accept(call, response);
            }

            @Override
            public void onFailure(@NotNull Call<T> call, @NotNull Throwable ex)
            {
                failConsumer.accept(call, ex);
            }
        });
    }

    //...
}
