package com.ajayam.usermanagement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static String BASE_URL="http://192.168.247.210/UserApi/";
    static RetrofitClient retrofitClient;
    private static Retrofit retrofit;

    //for DATA VIEW IN LOGGER -----------------------------------------------
//    private OkHttpClient.Builder builder = new OkHttpClient.Builder();
//    private HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    // -----------------------------------------------------------------------

    private RetrofitClient() {

        // for DATA VIEW IN LOGGER -----------------------
//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();
//        interceptor.level(HttpLoggingInterceptor.Level.BODY);
//        builder.addInterceptor(interceptor);
        // ------------------------------------------------

        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
//                .client(builder.build())
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
