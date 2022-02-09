package com.example.digi_yatra_12.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    RetrofitBuilder() {

    }

    public static RetrofitService create() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return new Retrofit.Builder()
                .baseUrl(Const.BASE_URL_Get_ACCESS_TOKEN)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitService.class);
    }
}
