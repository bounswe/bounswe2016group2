package com.example.bounswegroup2.Models;

/**
 * Created by Enes on 19.12.2016.
 */

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type User more.
 */
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

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets diets.
     *
     * @return the diets
     */
    public List<Diet> getDiets() {
        return diets;
    }

    /**
     * Sets diets.
     *
     * @param diets the diets
     */
    public void setDiets(List<Diet> diets) {
        this.diets = diets;
    }

    /**
     * Gets foods.
     *
     * @return the foods
     */
    public List<FoodLess> getFoods() {
        return foods;
    }

    /**
     * Sets foods.
     *
     * @param foods the foods
     */
    public void setFoods(List<FoodLess> foods) {
        this.foods = foods;
    }

    /**
     * Gets restaurants.
     *
     * @return the restaurants
     */
    public List<RestaurantMore> getRestaurants() {
        return restaurants;
    }

    /**
     * Sets restaurants.
     *
     * @param restaurants the restaurants
     */
    public void setRestaurants(List<RestaurantMore> restaurants) {
        this.restaurants = restaurants;
    }

    /**
     * Gets is server.
     *
     * @return the is server
     */
    public Boolean getIsServer() {
        return isServer;
    }

    /**
     * Sets is server.
     *
     * @param isServer the is server
     */
    public void setIsServer(Boolean isServer) {
        this.isServer = isServer;
    }

    /**
     * Gets food comments.
     *
     * @return the food comments
     */
    public List<FoodComment> getFoodComments() {
        return foodComments;
    }

    /**
     * Sets food comments.
     *
     * @param foodComments the food comments
     */
    public void setFoodComments(List<FoodComment> foodComments) {
        this.foodComments = foodComments;
    }

    /**
     * Gets food rates.
     *
     * @return the food rates
     */
    public List<FoodRate> getFoodRates() {
        return foodRates;
    }

    /**
     * Sets food rates.
     *
     * @param foodRates the food rates
     */
    public void setFoodRates(List<FoodRate> foodRates) {
        this.foodRates = foodRates;
    }

    /**
     * Gets restaurant comments.
     *
     * @return the restaurant comments
     */
    public List<RestaurantComment> getRestaurantComments() {
        return restaurantComments;
    }

    /**
     * Sets restaurant comments.
     *
     * @param restaurantComments the restaurant comments
     */
    public void setRestaurantComments(List<RestaurantComment> restaurantComments) {
        this.restaurantComments = restaurantComments;
    }

    /**
     * Gets restaurant rates.
     *
     * @return the restaurant rates
     */
    public List<RestaurantRate> getRestaurantRates() {
        return restaurantRates;
    }

    /**
     * Sets restaurant rates.
     *
     * @param restaurantRates the restaurant rates
     */
    public void setRestaurantRates(List<RestaurantRate> restaurantRates) {
        this.restaurantRates = restaurantRates;
    }

}