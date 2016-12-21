package com.example.bounswegroup2.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Enes on 18.12.2016.
 */
public class AteFood implements Serializable{
    @SerializedName("user")
    @Expose
    private Integer user;
    @SerializedName("food")
    @Expose
    private Integer food;
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("created")
    @Expose
    private String created;
    private final static long serialVersionUID = -4324884701801355221L;

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

    /**
     * Gets value.
     *
     * @return the value
     */
    public Integer getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * Gets created.
     *
     * @return the created
     */
    public String getCreated() {
        return created;
    }

    /**
     * Sets created.
     *
     * @param created the created
     */
    public void setCreated(String created) {
        this.created = created;
    }
}
