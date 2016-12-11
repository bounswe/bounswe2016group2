package com.example.bounswegroup2.Models;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Inclusion implements Serializable
{

    @SerializedName("food")
    @Expose
    private Integer food;
    @SerializedName("value")
    @Expose
    private Double value;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("ingredient")
    @Expose
    private Ingredient ingredient;
    private final static long serialVersionUID = 7807782765017442673L;

    /**
     *
     * @return
     * The food
     */
    public Integer getFood() {
        return food;
    }

    /**
     *
     * @param food
     * The food
     */
    public void setFood(Integer food) {
        this.food = food;
    }

    /**
     *
     * @return
     * The value
     */
    public Double getValue() {
        return value;
    }

    /**
     *
     * @param value
     * The value
     */
    public void setValue(Double value) {
        this.value = value;
    }

    /**
     *
     * @return
     * The unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     *
     * @param unit
     * The unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     *
     * @param slug
     * The slug
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     *
     * @return
     * The ingredient
     */
    public Ingredient getIngredient() {
        return ingredient;
    }

    /**
     *
     * @param ingredient
     * The ingredient
     */
    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

}