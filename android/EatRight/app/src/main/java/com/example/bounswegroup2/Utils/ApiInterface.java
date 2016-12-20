package com.example.bounswegroup2.Utils;

import com.example.bounswegroup2.Models.AteFood;
import com.example.bounswegroup2.Models.Diet;
import com.example.bounswegroup2.Models.Food;
import com.example.bounswegroup2.Models.FoodAddResponse;
import com.example.bounswegroup2.Models.FoodComment;
import com.example.bounswegroup2.Models.FoodLess;
import com.example.bounswegroup2.Models.FoodRate;
import com.example.bounswegroup2.Models.Ingredient;
import com.example.bounswegroup2.Models.Restaurant;
import com.example.bounswegroup2.Models.RestaurantMore;
import com.example.bounswegroup2.Models.Tag;
import com.example.bounswegroup2.Models.TagResponse;
import com.example.bounswegroup2.Models.TotalUserHistory;
import com.example.bounswegroup2.Models.User;
import com.example.bounswegroup2.Models.UserMore;
import com.example.bounswegroup2.Models.signInRequest;
import com.example.bounswegroup2.Models.signUpRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * This interface is to define endpoints
 * Created by Enes on 29.10.2016.
 */

public interface ApiInterface {

    String BASE_URL="http://52.213.193.130/";
  /*  OkHttpClient defaultHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                    //getAccessToken is your own accessToken(retrieve it by saving in shared preference or any other option )
                    if(Constants.API_KEY.isEmpty()){
                        return chain.proceed(chain.request());
                    }
                    Request authorisedRequest = chain.request().newBuilder()
                            .addHeader("Authorization", "Token "+Constants.API_KEY).build();
                    return chain.proceed(authorisedRequest);
                }}).build();*/

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ApiInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("api/users/signup")
    Call<signUpRequest> postSignupUser(@Body RequestBody signUpParams);


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("api/users/signin")
    Call<signInRequest> postSigninUser(@Body RequestBody signInParams);

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
    Call<User> getUserWithId(@Header("Authorization") String key, @Path("userId") int userId);

    @PUT("api/users/{userId}")
    void putUserWithId();

    @DELETE("api/users/{userId}")
    void deleteUserWithId();

    @Headers("Content-Type: application/json")
    @GET("api/ingredients")
    Call<List<Ingredient>> getIngredients(@QueryMap Map<String, String> options);

    @POST("api/ingredients")
    void sendIngredients();

    @Headers("Content-Type: application/json")
    @GET("api/foods")
    Call<List<FoodLess>> getFoods();

    @Headers("Content-Type: application/json")
    @POST("/api/foods/{foodId}/ate")
    Call<AteFood> eatFood(@Header("Authorization") String key, @Path("foodId") int foodId, @Body RequestBody body);

    @Headers("Content-Type: application/json")
    @POST("/api/foods/{foodId}/comment")
    Call<FoodComment> commentFood(@Header("Authorization") String key, @Path("foodId") int foodId, @Body RequestBody body);

    @Headers("Content-Type: application/json")
    @POST("/api/foods/{foodId}/rate")
    Call<FoodRate> rateFood(@Header("Authorization") String key, @Path("foodId") int foodId, @Body RequestBody body);

    @Headers("Content-Type: application/json")
    @GET("api/foods/{foodId}")
    Call<Food> getFoodWithId(@Header("Authorization") String key,@Path("foodId") int foodId);

     @Headers("Content-Type: application/json")
     @POST("api/searchFood")
     Call<List<Food>> searchFood(@Header("Authorization") String key, @Body RequestBody body);

    @Headers("Content-Type: application/json")
    @POST("api/foods")
    Call<FoodLess> addFood(@Header("Authorization") String key,@Body RequestBody body);

    @Headers("Content-Type: application/json")
    @GET("api/users/me")
    Call<UserMore> getMe(@Header("Authorization") String key);

    @Headers("Content-Type: application/json")
    @GET("api/diets")
    Call<List<Diet>> getDiets();

    @Headers("Content-Type: application/json")
    @GET("api/myDiets")
    Call<List<Diet>> getMyDiets();

    @Headers("Content-Type: application/json")
    @POST("api/myDiets/{dietId}")
    Call<List<Diet>> setMyDiet(@Path("dietId") int dietId);

    @Headers("Content-Type: application/json")
    @GET("api/restaurants")
    Call<List<Restaurant>> getRestaurants();

    @Headers("Content-Type: application/json")
    @GET("api/restaurants/{restaurantId}")
    Call<RestaurantMore> getRestaurantWithId(@Path("restaurantId") int restaurantId);

    @Headers("Content-Type: application/json")
    @POST("api/restaurants/{restaurantId}/comment")
    Call<Response> commentRestaurant(@Path("restaurantId") int restaurantId);

    @Headers("Content-Type: application/json")
    @POST("api/restaurants/{restaurantId}/rate")
    Call<Response> rateRestaurant(@Path("restaurantId") int restaurantId);

    @Headers("Content-Type: application/json")
    @POST("api/foods/{foodId}/ingredients/{ingredientId}")
    Call<FoodAddResponse> addIngredientToFood(@Header("Authorization") String key,@Path("foodId") int foodId,@Path("ingredientId") int ingredientId,@Body RequestBody body);

    @Headers("Content-Type: application/json")
    @GET("api/myFoods")
    Call<List<FoodLess>> getMyFoods(@Header("Authorization") String key);

    @Headers("Content-Type: application/json")
    @GET("api/searchTag")
    Call<List<Tag>> getTags(@Header("Authorization") String key,@Query("query") String query);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("api/users/me/history")
    Call<TotalUserHistory> getuserFoodHistory(@Header("Authorization") String token);

    @Headers("Content-Type: application/json")
    @POST("api/foods/{foodId}/tag")
    Call<ResponseBody> addTagToFood(@Header("Authorization") String token, @Path("foodId") int foodId, @Body RequestBody body);

}