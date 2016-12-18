package com.example.bounswegroup2.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Enes on 14.12.2016.
 */

public class Details implements Serializable
{

    @SerializedName("energy")
    @Expose
    private Double energy;
    @SerializedName("protein")
    @Expose
    private Protein protein;
    @SerializedName("others")
    @Expose
    private Others others;
    @SerializedName("carb")
    @Expose
    private Carb carb;
    @SerializedName("fat")
    @Expose
    private Fat fat;
    @SerializedName("rate")
    @Expose
    private Double rate;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("weight")
    @Expose
    private Double weight;
    private final static long serialVersionUID = -2022732311336839633L;

    public Double getEnergy() {
        return energy;
    }

    public void setEnergy(Double energy) {
        this.energy = energy;
    }

    public Protein getProtein() {
        return protein;
    }

    public void setProtein(Protein protein) {
        this.protein = protein;
    }

    public Others getOthers() {
        return others;
    }

    public void setOthers(Others others) {
        this.others = others;
    }

    public Carb getCarb() {
        return carb;
    }

    public void setCarb(Carb carb) {
        this.carb = carb;
    }

    public Fat getFat() {
        return fat;
    }

    public void setFat(Fat fat) {
        this.fat = fat;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

}