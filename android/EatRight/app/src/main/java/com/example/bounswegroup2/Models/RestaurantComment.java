package com.example.bounswegroup2.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

<<<<<<< HEAD
import java.io.Serializable;

/**
 * Created by Enes on 17.12.2016.
 */

public class RestaurantComment implements Serializable{
=======
/**
 * Created by Enes on 17.12.2016.
 */
public class RestaurantComment {
    @SerializedName("id")
    @Expose
    private Integer id;
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b

    @SerializedName("comment")
    @Expose
    private String comment;

    @SerializedName("user")
    @Expose
<<<<<<< HEAD
    private Integer user;

    @SerializedName("restaurant")
    @Expose
    private Integer restaurant;

    private transient  String  username;
=======
    private User user;

    @SerializedName("restaurant")
    @Expose
    private Restaurant restaurant;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets comment.
     *
     * @return the comment
     */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public String getComment() {
        return comment;
    }

<<<<<<< HEAD
=======
    /**
     * Sets comment.
     *
     * @param comment the comment
     */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public void setComment(String comment) {
        this.comment = comment;
    }

<<<<<<< HEAD
    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Integer restaurant) {
        this.restaurant = restaurant;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
=======
    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets restaurant.
     *
     * @return the restaurant
     */
    public Restaurant getRestaurant() {
        return restaurant;
    }

    /**
     * Sets restaurant.
     *
     * @param restaurant the restaurant
     */
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
}
