package com.example.bounswegroup2.Models;

/**
 * Created by Enes on 15.12.2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Carb implements Serializable
{

    @SerializedName("rate")
    @Expose
    private Integer rate;
    @SerializedName("weight")
    @Expose
    private Double weight;
    private final static long serialVersionUID = -5878114610257711861L;

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

}