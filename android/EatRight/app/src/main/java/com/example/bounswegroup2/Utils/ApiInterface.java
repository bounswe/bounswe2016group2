package com.example.bounswegroup2.Utils;

import com.example.bounswegroup2.Models.AteFood;
import com.example.bounswegroup2.Models.Diet;
import com.example.bounswegroup2.Models.DietAddResponse;
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

    /**
     * The constant BASE_URL.
     */
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

    /**
     * The constant retrofit.
     */
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ApiInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    /**
     * Post signup user call.
     *
     * @param signUpParams the sign up params
     * @return the call
     */
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("api/users/signup")
    Call<signUpRequest> postSignupUser(@Body RequestBody signUpParams);


    /**
     * Post signin user call.
     *
     * @param signInParams the sign in params
     * @return the call
     */
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("api/users/signin")
    Call<signInRequest> postSigninUser(@Body RequestBody signInParams);

    /**
     * Post signout.
     */
    @POST("api/users/signout")
    void postSignout();

    /**
     * Gets signout.
     */
    @GET("api/users/signout")
    void getSignout();

    /**
     * Send yourself.
     */
    @POST("api/users/me")
    void sendYourself();

    /**
     * Gets yourself.
     */
    @GET("api/users/me")
    void getYourself();

    /**
     * Gets users.
     *
     * @param options the options
     * @return the users
     */
    @GET("api/users")
    Call<List<User>> getUsers(@QueryMap Map<String, String> options);

    /**
     * Gets user with id.
     *
     * @param key    the key
     * @param userId the user id
     * @return the user with id
     */
    @GET("api/users/{userId}")
    Call<User> getUserWithId(@Header("Authorization") String key, @Path("userId") int userId);

    /**
     * Put user with id.
     */
    @PUT("api/users/{userId}")
    void putUserWithId();

    /**
     * Delete user with id.
     */
    @DELETE("api/users/{userId}")
    void deleteUserWithId();

    /**
     * Gets ingredients.
     *
     * @param options the options
     * @return the ingredients
     */
    @Headers("Content-Type: application/json")
    @GET("api/ingredients")
    Call<List<Ingredient>> getIngredients(@QueryMap Map<String, String> options);

    /**
     * Send ingredients.
     */
    @POST("api/ingredients")
    void sendIngredients();

    /**
     * Gets foods.
     *
     * @return the foods
     */
    @Headers("Content-Type: application/json")
    @GET("api/foods")
    Call<List<FoodLess>> getFoods();

    /**
     * Eat food call.
     *
     * @param key    the key
     * @param foodId the food id
     * @param body   the body
     * @return the call
     */
    @Headers("Content-Type: application/json")
    @POST("/api/foods/{foodId}/ate")
    Call<AteFood> eatFood(@Header("Authorization") String key, @Path("foodId") int foodId, @Body RequestBody body);

    /**
     * Comment food call.
     *
     * @param key    the key
     * @param foodId the food id
     * @param body   the body
     * @return the call
     */
    @Headers("Content-Type: application/json")
    @POST("/api/foods/{foodId}/comment")
    Call<FoodComment> commentFood(@Header("Authorization") String key, @Path("foodId") int foodId, @Body RequestBody body);

    /**
     * Rate food call.
     *
     * @param key    the key
     * @param foodId the food id
     * @param body   the body
     * @return the call
     */
    @Headers("Content-Type: application/json")
    @POST("/api/foods/{foodId}/rate")
    Call<FoodRate> rateFood(@Header("Authorization") String key, @Path("foodId") int foodId, @Body RequestBody body);

    /**
     * Gets food with id.
     *
     * @param key    the key
     * @param foodId the food id
     * @return the food with id
     */
    @Headers("Content-Type: application/json")
    @GET("api/foods/{foodId}")
    Call<Food> getFoodWithId(@Header("Authorization") String key,@Path("foodId") int foodId);

    /**
     * Search food call.
     *
     * @param key  the key
     * @param body the body
     * @return the call
     */
    @Headers("Content-Type: application/json")
     @POST("api/searchFood")
     Call<List<Food>> searchFood(@Header("Authorization") String key, @Body RequestBody body);

    /**
     * Add food call.
     *
     * @param key  the key
     * @param body the body
     * @return the call
     */
    @Headers("Content-Type: application/json")
    @POST("api/foods")
    Call<FoodLess> addFood(@Header("Authorization") String key,@Body RequestBody body);

    /**
     * Gets me.
     *
     * @param key the key
     * @return the me
     */
    @Headers("Content-Type: application/json")
    @GET("api/users/me")
    Call<UserMore> getMe(@Header("Authorization") String key);

    /**
     * Gets diets.
     *
     * @return the diets
     */
    @Headers("Content-Type: application/json")
    @GET("api/diets")
    Call<List<Diet>> getDiets();

    /**
     * Gets my diets.
     *
     * @return the my diets
     */
    @Headers("Content-Type: application/json")
    @GET("api/myDiets")
    Call<List<Diet>> getMyDiets();

    /**
     * Sets my diet.
     *
     * @param dietId the diet id
     * @return the my diet
     */
    @Headers("Content-Type: application/json")
    @POST("api/myDiets/{dietId}")
    Call<List<Diet>> setMyDiet(@Path("dietId") int dietId);

    /**
     * Gets restaurants.
     *
     * @return the restaurants
     */
    @Headers("Content-Type: application/json")
    @GET("api/restaurants")
    Call<List<Restaurant>> getRestaurants();

    /**
     * Gets restaurant with id.
     *
     * @param restaurantId the restaurant id
     * @return the restaurant with id
     */
    @Headers("Content-Type: application/json")
    @GET("api/restaurants/{restaurantId}")
    Call<RestaurantMore> getRestaurantWithId(@Path("restaurantId") int restaurantId);

    /**
     * Comment restaurant call.
     *
     * @param restaurantId the restaurant id
     * @return the call
     */
    @Headers("Content-Type: application/json")
    @POST("api/restaurants/{restaurantId}/comment")
    Call<Response> commentRestaurant(@Path("restaurantId") int restaurantId);

    /**
     * Rate restaurant call.
     *
     * @param restaurantId the restaurant id
     * @return the call
     */
    @Headers("Content-Type: application/json")
    @POST("api/restaurants/{restaurantId}/rate")
    Call<Response> rateRestaurant(@Path("restaurantId") int restaurantId);

    /**
     * Add ingredient to food call.
     *
     * @param key          the key
     * @param foodId       the food id
     * @param ingredientId the ingredient id
     * @param body         the body
     * @return the call
     */
    @Headers("Content-Type: application/json")
    @POST("api/foods/{foodId}/ingredients/{ingredientId}")
    Call<FoodAddResponse> addIngredientToFood(@Header("Authorization") String key,@Path("foodId") int foodId,@Path("ingredientId") int ingredientId,@Body RequestBody body);

    /**
     * Gets my foods.
     *
     * @param key the key
     * @return the my foods
     */
    @Headers("Content-Type: application/json")
    @GET("api/myFoods")
    Call<List<FoodLess>> getMyFoods(@Header("Authorization") String key);

    /**
     * Gets tags.
     *
     * @param key   the key
     * @param query the query
     * @return the tags
     */
    @Headers("Content-Type: application/json")
    @GET("api/searchTag")
    Call<List<Tag>> getTags(@Header("Authorization") String key,@Query("query") String query);

    /**
     * Gets food history.
     *
     * @param token the token
     * @return the food history
     */
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("api/users/me/history")
    Call<TotalUserHistory> getuserFoodHistory(@Header("Authorization") String token);

    /**
     * Add tag to food call.
     *
     * @param token  the token
     * @param foodId the food id
     * @param body   the body
     * @return the call
     */
    @Headers("Content-Type: application/json")
    @POST("api/foods/{foodId}/tag")
    Call<ResponseBody> addTagToFood(@Header("Authorization") String token, @Path("foodId") int foodId, @Body RequestBody body);

    /**
     * Add diet call.
     *
     * @param token the token
     * @param body  the body
     * @return the call
     */
    @Headers("Content-Type: application/json")
    @POST("api/diets")
    Call<DietAddResponse> addDiet(@Header("Authorization") String token, @Body RequestBody body);

    /**
     * Add my diet call.
     *
     * @param token  the token
     * @param dietId the diet id
     * @return the call
     */
    @Headers("Content-Type: application/json")
    @POST("api/myDiets/{dietId}")
    Call<Void> addMyDiet(@Header("Authorization") String token, @Path("dietId") int dietId);

    /**
     * Gets my diets.
     *
     * @param token the token
     * @return the my diets
     */
    @Headers("Content-Type: application/json")
    @GET("api/myDiets")
    Call<List<Diet>> getMyDiets(@Header("Authorization") String token);


}