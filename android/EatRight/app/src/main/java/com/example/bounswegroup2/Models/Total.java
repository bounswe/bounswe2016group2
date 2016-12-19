package com.example.bounswegroup2.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yigitozgumus on 12/19/16.
 */

public class Total {
    @SerializedName("ateFoods")
    @Expose
    private List<AteFoodUserless> ateFoods;

    @SerializedName("carb")
    @Expose
    private Carb carb;

    @SerializedName("others")
    @Expose
    private Others others;

    @SerializedName("energy")
    @Expose
    private Double energy;

    @SerializedName("protein")
    @Expose
    private Protein protein;

    @SerializedName("rate")
    @Expose
    private Integer rate;

    @SerializedName("fat")
    @Expose
    private Fat fat;

    @SerializedName("count")
    @Expose
    private Integer count;

    @SerializedName("weight")
    @Expose
    private Integer weight;

    public List<AteFoodUserless> getAteFoods() {
        return ateFoods;
    }

    public void setAteFoods(List<AteFoodUserless> ateFoods) {
        this.ateFoods = ateFoods;
    }

    public Carb getCarb() {
        return carb;
    }

    public void setCarb(Carb carb) {
        this.carb = carb;
    }

    public Others getOthers() {
        return others;
    }

    public void setOthers(Others others) {
        this.others = others;
    }

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

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Fat getFat() {
        return fat;
    }

    public void setFat(Fat fat) {
        this.fat = fat;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
