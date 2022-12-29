package com.karandev.util.retrofit;

import java.util.function.BiConsumer;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitUtil {
    private RetrofitUtil()
    {}

    public static Retrofit createRetrofitClient(String baseUrl)
    {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();
    }

    public static Retrofit createRetrofitClientWithLogging(String baseUrl)
    {
        var interceptor = new HttpLoggingInterceptor();

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        var client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public static <T, C extends Call<T>> void enqueue(C call, BiConsumer<Call<T>, Response<T>> responseConsumer, BiConsumer<Call<T>, Throwable> failConsumer)
    {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response)
            {
                responseConsumer.accept(call, response);
            }

            @Override
            public void onFailure(Call<T> call, Throwable ex)
            {
                failConsumer.accept(call, ex);
            }
        });
    }

    //...
}
