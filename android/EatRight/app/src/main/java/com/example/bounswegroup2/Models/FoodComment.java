package com.example.bounswegroup2.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Enes on 17.12.2016.
 */

public class FoodComment implements Serializable {

    @SerializedName("comment")
    @Expose
    private String comment;

    @SerializedName("user")
    @Expose
    private Integer user;

    @SerializedName("food")
    @Expose
    private Integer food;

    private transient  String  username;
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}