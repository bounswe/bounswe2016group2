package com.example.bounswegroup2.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by yigitozgumus on 12/16/16.
 */

public class signInRequest implements Serializable {

    @SerializedName("token")
    @Expose
    private String token;
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



}
