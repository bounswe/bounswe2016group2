package com.example.bounswegroup2.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Enes on 17.12.2016.
 */
public class FoodRate implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("score")
    @Expose
    private float score;
    @SerializedName("user")
    @Expose
    private Integer user;
    @SerializedName("food")
    @Expose
    private Integer food;

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
     * Gets score.
     *
     * @return the score
     */
    public float getScore() {
        return score;
    }

    /**
     * Sets score.
     *
     * @param score the score
     */
    public void setScore(float score) {
        this.score = score;
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
     * Gets food.
     *
     * @return the food
     */
    public Integer getFood() {
        return food;
    }

    /**
     * Sets food.
     *
     * @param food the food
     */
    public void setFood(Integer food) {
        this.food = food;
    }
}
