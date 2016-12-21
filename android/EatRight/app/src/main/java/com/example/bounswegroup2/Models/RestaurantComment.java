package com.example.bounswegroup2.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Enes on 17.12.2016.
 */
public class RestaurantComment implements Serializable{

    @SerializedName("comment")
    @Expose
    private String comment;

    @SerializedName("user")
    @Expose
    private Integer user;

    @SerializedName("restaurant")
    @Expose
    private Integer restaurant;

    private transient  String  username;

    /**
     * Gets comment.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets comment.
     *
     * @param comment the comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public Integer getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(Integer user) {
        this.user = user;
    }

    /**
     * Gets restaurant.
     *
     * @return the restaurant
     */
    public Integer getRestaurant() {
        return restaurant;
    }

    /**
     * Sets restaurant.
     *
     * @param restaurant the restaurant
     */
    public void setRestaurant(Integer restaurant) {
        this.restaurant = restaurant;
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
}
