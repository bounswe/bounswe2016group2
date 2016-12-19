package com.example.bounswegroup2.Models;

/**
 * Created by Enes on 19.12.2016.
 */

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserMore implements Serializable
{

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("diets")
    @Expose
    private List<Diet> diets = null;
    @SerializedName("foods")
    @Expose
    private List<FoodLess> foods = null;
    @SerializedName("restaurants")
    @Expose
    private List<RestaurantMore> restaurants = null;
    @SerializedName("isServer")
    @Expose
    private Boolean isServer;
    @SerializedName("foodComments")
    @Expose
    private List<FoodComment> foodComments = null;
    @SerializedName("foodRates")
    @Expose
    private List<FoodRate> foodRates = null;
    @SerializedName("restaurantComments")
    @Expose
    private List<RestaurantComment> restaurantComments = null;
    @SerializedName("restaurantRates")
    @Expose
    private List<RestaurantRate> restaurantRates = null;
    private final static long serialVersionUID = -8415525740748802204L;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Diet> getDiets() {
        return diets;
    }

    public void setDiets(List<Diet> diets) {
        this.diets = diets;
    }

    public List<FoodLess> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodLess> foods) {
        this.foods = foods;
    }

    public List<RestaurantMore> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<RestaurantMore> restaurants) {
        this.restaurants = restaurants;
    }

    public Boolean getIsServer() {
        return isServer;
    }

    public void setIsServer(Boolean isServer) {
        this.isServer = isServer;
    }

    public List<FoodComment> getFoodComments() {
        return foodComments;
    }

    public void setFoodComments(List<FoodComment> foodComments) {
        this.foodComments = foodComments;
    }

    public List<FoodRate> getFoodRates() {
        return foodRates;
    }

    public void setFoodRates(List<FoodRate> foodRates) {
        this.foodRates = foodRates;
    }

    public List<RestaurantComment> getRestaurantComments() {
        return restaurantComments;
    }

    public void setRestaurantComments(List<RestaurantComment> restaurantComments) {
        this.restaurantComments = restaurantComments;
    }

    public List<RestaurantRate> getRestaurantRates() {
        return restaurantRates;
    }

    public void setRestaurantRates(List<RestaurantRate> restaurantRates) {
        this.restaurantRates = restaurantRates;
    }

}