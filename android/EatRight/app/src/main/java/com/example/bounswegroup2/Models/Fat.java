package com.example.bounswegroup2.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Enes on 15.12.2016.
 */

public class Fat implements Serializable
{

    @SerializedName("rate")
    @Expose
    private Integer rate;
    @SerializedName("weight")
    @Expose
    private Double weight;
    private final static long serialVersionUID = -8178553691364773570L;

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