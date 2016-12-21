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

    /**
     * Gets ate foods.
     *
     * @return the ate foods
     */
    public List<AteFoodUserless> getAteFoods() {
        return ateFoods;
    }

    /**
     * Sets ate foods.
     *
     * @param ateFoods the ate foods
     */
    public void setAteFoods(List<AteFoodUserless> ateFoods) {
        this.ateFoods = ateFoods;
    }

    /**
     * Gets carb.
     *
     * @return the carb
     */
    public Carb getCarb() {
        return carb;
    }

    /**
     * Sets carb.
     *
     * @param carb the carb
     */
    public void setCarb(Carb carb) {
        this.carb = carb;
    }

    /**
     * Gets others.
     *
     * @return the others
     */
    public Others getOthers() {
        return others;
    }

    /**
     * Sets others.
     *
     * @param others the others
     */
    public void setOthers(Others others) {
        this.others = others;
    }

    /**
     * Gets energy.
     *
     * @return the energy
     */
    public Double getEnergy() {
        return energy;
    }

    /**
     * Sets energy.
     *
     * @param energy the energy
     */
    public void setEnergy(Double energy) {
        this.energy = energy;
    }

    /**
     * Gets protein.
     *
     * @return the protein
     */
    public Protein getProtein() {
        return protein;
    }

    /**
     * Sets protein.
     *
     * @param protein the protein
     */
    public void setProtein(Protein protein) {
        this.protein = protein;
    }

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
     * Gets fat.
     *
     * @return the fat
     */
    public Fat getFat() {
        return fat;
    }

    /**
     * Sets fat.
     *
     * @param fat the fat
     */
    public void setFat(Fat fat) {
        this.fat = fat;
    }

    /**
     * Gets count.
     *
     * @return the count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * Sets count.
     *
     * @param count the count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * Gets weight.
     *
     * @return the weight
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * Sets weight.
     *
     * @param weight the weight
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
