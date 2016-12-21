package com.example.bounswegroup2.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yigitozgumus on 12/19/16.
 */
public class AteFoodUserless {
    @SerializedName("food")
    @Expose
    private Food food;
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("created")
    @Expose
    private String created;
    private final static long serialVersionUID = -4324884701801355221L;

    /**
     * Gets food.
     *
     * @return the food
     */
    public Food getFood() {
        return food;
    }

    /**
     * Sets food.
     *
     * @param food the food
     */
    public void setFood(Food food) {
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
