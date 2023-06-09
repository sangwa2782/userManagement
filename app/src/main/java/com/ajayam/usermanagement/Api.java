package com.ajayam.usermanagement;

import com.ajayam.usermanagement.ModelResponse.LoginResponse;
import com.ajayam.usermanagement.ModelResponse.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("register.php")
    Call<RegisterResponse> register(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password

    );

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password

    );

    @FormUrlEncoded
    @POST("updateuser.php")
    Call<LoginResponse> updateUserAccount(
            @Field("id") int userid,
            @Field("username") String userName,
            @Field("email") String email

    );
}
