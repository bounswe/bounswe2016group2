package com.example.bounswegroup2.Utils;

import com.example.bounswegroup2.Models.Food;
import com.example.bounswegroup2.Models.Ingredient;
import com.example.bounswegroup2.Models.User;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;

import retrofit2.http.POST;
import retrofit2.http.PUT;
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


    @POST("api/users/signup")
    void postSignupUser(@FieldMap Map<String,String> userInfo, Callback<Response> callback);

    @POST("api/users/signin")
    void postSigninUser(@FieldMap Map<String,String> userInfo,Callback<Response> callback);

    @POST("api/users/signout")
    void postSignout();

    @GET("api/users/signout")
    void getSignout();

    @POST("api/users/me")
    void sendYourself();

    @GET("api/users/me")
    void getYourself();

    @GET("api/users")
    Call<List<User>> getUsers(@QueryMap Map<String, String> options);

    @GET("api/users/{userId}")
    void getUserWithId();

    @PUT("api/users/{userId}")
    void putUserWithId();

    @DELETE("api/users/{userId}")
    void deleteUserWithId();

    @GET("api/ingredients")
    Call<List<Ingredient>> getIngredients(@QueryMap Map<String, String> options);

    @POST("api/ingredients")
    void sendIngredients();

//    @GET("api/ingredients/{ingredientId}")
//
//    @PUT("api/ingredients/{ingredientId}")
//
//    @DELETE("api/ingredients/{ingredientId}")
//
//    @GET("api/ingredients/{sluq}")
//
//    @DELETE("api/ingredients/{sluq}")
//
//    @POST("api/ingredientMocks")

    @GET("api/foods")
    Call<List<Food>> getFoods(@QueryMap Map<String, String> options);

//    @POST("api/foods")
//
//    @GET("api/foods/{foodId}")
//
//    @PUT("api/foods/{foodId}")
//
//    @DELETE("api/foods/{foodId}")
//
//    @GET("api/foods/{slug}")
//
//    @DELETE("api/foods/{slug}")
//
//    @GET("api/foodSearch")
//
//    @POST("api/foodMocks")
//
//    @GET("api/foods/{food}/ingredients/{ingredient}")
//
//    @POST("api/foods/{food}/ingredients/{ingredient}")
//
//    @PUT("api/foods/{food}/ingredients/{ingredient}")
//
//    @DELETE("api/foods/{food}/ingredients/{ingredient}")










}
