package com.example.bounswegroup2.Models;

/**
 * Created by Enes on 18.12.2016.
 */

        import java.io.Serializable;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class FoodAddResponse implements Serializable
{

    @SerializedName("food")
    @Expose
    private Integer food;
    @SerializedName("ingredient")
    @Expose
    private Integer ingredient;
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("unit")
    @Expose
    private String unit;
    private final static long serialVersionUID = 4720889938615122963L;

    public Integer getFood() {
        return food;
    }

    public void setFood(Integer food) {
        this.food = food;
    }

    public Integer getIngredient() {
        return ingredient;
    }

    public void setIngredient(Integer ingredient) {
        this.ingredient = ingredient;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}