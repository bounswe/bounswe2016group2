package com.example.bounswegroup2.Models;

/**
 * Created by Enes on 21.12.2016.
 */

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Diet add response.
 */
public class DietAddResponse implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("ingredients")
    @Expose
    private List<Integer> ingredients = null;
    @SerializedName("users")
    @Expose
    private List<Integer> users = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slug")
    @Expose
    private String slug;
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

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets ingredients.
     *
     * @return the ingredients
     */
    public List<Integer> getIngredients() {
        return ingredients;
    }

    /**
     * Sets ingredients.
     *
     * @param ingredients the ingredients
     */
    public void setIngredients(List<Integer> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets min energy.
     *
     * @return the min energy
     */
    public Double getMinEnergy() {
        return minEnergy;
    }

    /**
     * Sets min energy.
     *
     * @param minEnergy the min energy
     */
    public void setMinEnergy(Double minEnergy) {
        this.minEnergy = minEnergy;
    }

    /**
     * Gets max energy.
     *
     * @return the max energy
     */
    public Double getMaxEnergy() {
        return maxEnergy;
    }

    /**
     * Sets max energy.
     *
     * @param maxEnergy the max energy
     */
    public void setMaxEnergy(Double maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    /**
     * Gets min protein val.
     *
     * @return the min protein val
     */
    public Double getMinProteinVal() {
        return minProteinVal;
    }

    /**
     * Sets min protein val.
     *
     * @param minProteinVal the min protein val
     */
    public void setMinProteinVal(Double minProteinVal) {
        this.minProteinVal = minProteinVal;
    }

    /**
     * Gets max protein val.
     *
     * @return the max protein val
     */
    public Double getMaxProteinVal() {
        return maxProteinVal;
    }

    /**
     * Sets max protein val.
     *
     * @param maxProteinVal the max protein val
     */
    public void setMaxProteinVal(Double maxProteinVal) {
        this.maxProteinVal = maxProteinVal;
    }

    /**
     * Gets min carb val.
     *
     * @return the min carb val
     */
    public Double getMinCarbVal() {
        return minCarbVal;
    }

    /**
     * Sets min carb val.
     *
     * @param minCarbVal the min carb val
     */
    public void setMinCarbVal(Double minCarbVal) {
        this.minCarbVal = minCarbVal;
    }

    /**
     * Gets max carb val.
     *
     * @return the max carb val
     */
    public Double getMaxCarbVal() {
        return maxCarbVal;
    }

    /**
     * Sets max carb val.
     *
     * @param maxCarbVal the max carb val
     */
    public void setMaxCarbVal(Double maxCarbVal) {
        this.maxCarbVal = maxCarbVal;
    }

    /**
     * Gets min fat val.
     *
     * @return the min fat val
     */
    public Double getMinFatVal() {
        return minFatVal;
    }

    /**
     * Sets min fat val.
     *
     * @param minFatVal the min fat val
     */
    public void setMinFatVal(Double minFatVal) {
        this.minFatVal = minFatVal;
    }

    /**
     * Gets max fat val.
     *
     * @return the max fat val
     */
    public Double getMaxFatVal() {
        return maxFatVal;
    }

    /**
     * Sets max fat val.
     *
     * @param maxFatVal the max fat val
     */
    public void setMaxFatVal(Double maxFatVal) {
        this.maxFatVal = maxFatVal;
    }

    /**
     * Gets min protein rate.
     *
     * @return the min protein rate
     */
    public Double getMinProteinRate() {
        return minProteinRate;
    }

    /**
     * Sets min protein rate.
     *
     * @param minProteinRate the min protein rate
     */
    public void setMinProteinRate(Double minProteinRate) {
        this.minProteinRate = minProteinRate;
    }

    /**
     * Gets max protein rate.
     *
     * @return the max protein rate
     */
    public Double getMaxProteinRate() {
        return maxProteinRate;
    }

    /**
     * Sets max protein rate.
     *
     * @param maxProteinRate the max protein rate
     */
    public void setMaxProteinRate(Double maxProteinRate) {
        this.maxProteinRate = maxProteinRate;
    }

    /**
     * Gets min carb rate.
     *
     * @return the min carb rate
     */
    public Double getMinCarbRate() {
        return minCarbRate;
    }

    /**
     * Sets min carb rate.
     *
     * @param minCarbRate the min carb rate
     */
    public void setMinCarbRate(Double minCarbRate) {
        this.minCarbRate = minCarbRate;
    }

    /**
     * Gets max carb rate.
     *
     * @return the max carb rate
     */
    public Double getMaxCarbRate() {
        return maxCarbRate;
    }

    /**
     * Sets max carb rate.
     *
     * @param maxCarbRate the max carb rate
     */
    public void setMaxCarbRate(Double maxCarbRate) {
        this.maxCarbRate = maxCarbRate;
    }

    /**
     * Gets min fat rate.
     *
     * @return the min fat rate
     */
    public Double getMinFatRate() {
        return minFatRate;
    }

    /**
     * Sets min fat rate.
     *
     * @param minFatRate the min fat rate
     */
    public void setMinFatRate(Double minFatRate) {
        this.minFatRate = minFatRate;
    }

    /**
     * Gets max fat rate.
     *
     * @return the max fat rate
     */
    public Double getMaxFatRate() {
        return maxFatRate;
    }

    /**
     * Sets max fat rate.
     *
     * @param maxFatRate the max fat rate
     */
    public void setMaxFatRate(Double maxFatRate) {
        this.maxFatRate = maxFatRate;
    }

    /**
     * Gets slug.
     *
     * @return the slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     * Sets slug.
     *
     * @param slug the slug
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    public List<Integer> getUsers() {
        return users;
    }

    /**
     * Sets users.
     *
     * @param users the users
     */
    public void setUsers(List<Integer> users) {
        this.users = users;
    }
}