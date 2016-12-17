package com.example.bounswegroup2.Models;

import java.io.Serializable;
import java.util.List;

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
    private final static long serialVersionUID = 556519721582874725L;

    public Integer getFood() {
        return food;
    }

    public void setFood(Integer food) {
        this.food = food;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

}