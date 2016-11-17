package com.example.bounswegroup2.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yigitozgumus on 11/12/16.
 */

public class Users {

    @SerializedName("users")
    @Expose
    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }


}