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

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
