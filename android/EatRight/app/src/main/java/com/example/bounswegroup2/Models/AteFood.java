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

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getFood() {
        return food;
    }

    public void setFood(Integer food) {
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
