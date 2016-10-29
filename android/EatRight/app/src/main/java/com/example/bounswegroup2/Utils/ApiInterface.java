package com.example.bounswegroup2.Utils;

import com.example.bounswegroup2.Models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * This interface is to define endpoints
 * Created by Enes on 29.10.2016.
 */

public interface ApiInterface {

    @GET("user/{email}")
    Call<User> getUser(@Path("email") String email, @Query("api_key") String apiKey);
}
