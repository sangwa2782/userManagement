package com.ajayam.usermanagement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static String BASE_URL="http://192.168.247.244/UserApi/";
    static RetrofitClient retrofitClient;
    private static Retrofit retrofit;


    private RetrofitClient() {

        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if(retrofitClient==null) {
            retrofitClient=new RetrofitClient();
        }
        return retrofitClient;
    }

    public Api getApi () {
        return retrofit.create(Api.class);
    }
}
