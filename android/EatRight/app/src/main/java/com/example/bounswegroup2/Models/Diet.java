package com.example.bounswegroup2.Models;

/**
 * Created by Enes on 16.12.2016.
 */

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Diet implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("ingredients")
    @Expose
    private List<IngrLess> ingredients = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("minEnergy")
    @Expose
    private Double minEnergy;
    @SerializedName("maxEnergy")
    @Expose
    private Double maxEnergy;
    @SerializedName("minProteinVal")
    @Expose
    private Double minProteinVal;
    @SerializedName("maxProteinVal")
    @Expose
    private Double maxProteinVal;
    @SerializedName("minCarbVal")
    @Expose
    private Double minCarbVal;
    @SerializedName("maxCarbVal")
    @Expose
    private Double maxCarbVal;
    @SerializedName("minFatVal")
    @Expose
    private Double minFatVal;
    @SerializedName("maxFatVal")
    @Expose
    private Double maxFatVal;
    @SerializedName("minProteinRate")
    @Expose
    private Double minProteinRate;
    @SerializedName("maxProteinRate")
    @Expose
    private Double maxProteinRate;
    @SerializedName("minCarbRate")
    @Expose
    private Double minCarbRate;
    @SerializedName("maxCarbRate")
    @Expose
    private Double maxCarbRate;
    @SerializedName("minFatRate")
    @Expose
    private Double minFatRate;
    @SerializedName("maxFatRate")
    @Expose
    private Double maxFatRate;
 private final static long serialVersionUID = -7336370091332770404L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<IngrLess> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngrLess> ingredients) {
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getMinEnergy() {
        return minEnergy;
    }

    public void setMinEnergy(Double minEnergy) {
        this.minEnergy = minEnergy;
    }

    public Double getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(Double maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public Double getMinProteinVal() {
        return minProteinVal;
    }

    public void setMinProteinVal(Double minProteinVal) {
        this.minProteinVal = minProteinVal;
    }

    public Double getMaxProteinVal() {
        return maxProteinVal;
    }

    public void setMaxProteinVal(Double maxProteinVal) {
        this.maxProteinVal = maxProteinVal;
    }

    public Double getMinCarbVal() {
        return minCarbVal;
    }

    public void setMinCarbVal(Double minCarbVal) {
        this.minCarbVal = minCarbVal;
    }

    public Double getMaxCarbVal() {
        return maxCarbVal;
    }

    public void setMaxCarbVal(Double maxCarbVal) {
        this.maxCarbVal = maxCarbVal;
    }

    public Double getMinFatVal() {
        return minFatVal;
    }

    public void setMinFatVal(Double minFatVal) {
        this.minFatVal = minFatVal;
    }

    public Double getMaxFatVal() {
        return maxFatVal;
    }

    public void setMaxFatVal(Double maxFatVal) {
        this.maxFatVal = maxFatVal;
    }

    public Double getMinProteinRate() {
        return minProteinRate;
    }

    public void setMinProteinRate(Double minProteinRate) {
        this.minProteinRate = minProteinRate;
    }

    public Double getMaxProteinRate() {
        return maxProteinRate;
    }

    public void setMaxProteinRate(Double maxProteinRate) {
        this.maxProteinRate = maxProteinRate;
    }

    public Double getMinCarbRate() {
        return minCarbRate;
    }

    public void setMinCarbRate(Double minCarbRate) {
        this.minCarbRate = minCarbRate;
    }

    public Double getMaxCarbRate() {
        return maxCarbRate;
    }

    public void setMaxCarbRate(Double maxCarbRate) {
        this.maxCarbRate = maxCarbRate;
    }

    public Double getMinFatRate() {
        return minFatRate;
    }

    public void setMinFatRate(Double minFatRate) {
        this.minFatRate = minFatRate;
    }

    public Double getMaxFatRate() {
        return maxFatRate;
    }

    public void setMaxFatRate(Double maxFatRate) {
        this.maxFatRate = maxFatRate;
    }

}