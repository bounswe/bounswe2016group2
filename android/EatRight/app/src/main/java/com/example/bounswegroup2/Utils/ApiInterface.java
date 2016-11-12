package com.example.bounswegroup2.Utils;

import com.example.bounswegroup2.Models.User;
import com.example.bounswegroup2.Models.Users;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import retrofit2.http.POST;

import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * This interface is to define endpoints
 * Created by Enes on 29.10.2016.
 */

public interface ApiInterface {

    String BASE_URL="http://52.208.59.70/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ApiInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("api/users")
    Call<User> getUsers(@QueryMap Map<String, String> options);

}
