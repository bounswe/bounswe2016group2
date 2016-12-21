package com.example.bounswegroup2.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

<<<<<<< HEAD
import java.io.Serializable;

/**
 * Created by Enes on 17.12.2016.
 */

public class RestaurantRate implements Serializable{
=======
/**
 * Created by Enes on 17.12.2016.
 */
public class RestaurantRate {
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("score")
    @Expose
    private float score;
    @SerializedName("user")
    @Expose
<<<<<<< HEAD
    private Integer user;
    @SerializedName("restaurant")
    @Expose
    private Integer restaurant;

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
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public Integer getId() {
        return id;
    }

<<<<<<< HEAD
=======
    /**
     * Sets id.
     *
     * @param id the id
     */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public void setId(Integer id) {
        this.id = id;
    }

<<<<<<< HEAD
=======
    /**
     * Gets score.
     *
     * @return the score
     */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public float getScore() {
        return score;
    }

<<<<<<< HEAD
=======
    /**
     * Sets score.
     *
     * @param score the score
     */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public void setScore(float score) {
        this.score = score;
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
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
        this.restaurant = restaurant;
    }
}
