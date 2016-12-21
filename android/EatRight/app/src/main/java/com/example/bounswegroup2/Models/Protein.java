package com.example.bounswegroup2.Models;

/**
 * Created by Enes on 15.12.2016.
 */

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Protein.
 */
public class Protein implements Serializable
{

    @SerializedName("rate")
    @Expose
    private Integer rate;
    @SerializedName("weight")
    @Expose
    private Double weight;
    private final static long serialVersionUID = 8980465361406351050L;

    /**
     * Gets rate.
     *
     * @return the rate
     */
    public Integer getRate() {
        return rate;
    }

    /**
     * Sets rate.
     *
     * @param rate the rate
     */
    public void setRate(Integer rate) {
        this.rate = rate;
    }

    /**
     * Gets weight.
     *
     * @return the weight
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * Sets weight.
     *
     * @param weight the weight
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }

}