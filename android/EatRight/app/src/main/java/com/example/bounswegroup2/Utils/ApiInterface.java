package com.example.bounswegroup2.Utils;

import com.example.bounswegroup2.Models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * This interface is to define endpoints
 * Created by Enes on 29.10.2016.
 */

public interface ApiInterface {

    @GET("user/{email}")
    Call<User> getUser(@Path("email") String email, @Query("api_key") String apiKey);
    @POST("user/{email,password,uname}")
    Call<User> createUser(@Path("email") String email,@Path("password") String pass,@Path("uname") String uname,@Query("api_key") String apiKey);
}
