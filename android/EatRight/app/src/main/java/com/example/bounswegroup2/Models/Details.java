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
     * Gets rate.
     *
     * @return the rate
     */
    public Double getRate() {
        return rate;
    }

    /**
     * Sets rate.
     *
     * @param rate the rate
     */
    public void setRate(Double rate) {
        this.rate = rate;
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