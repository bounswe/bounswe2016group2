package com.example.bounswegroup2.Models;

/**
 * Created by Enes on 18.12.2016.
 */

        import java.io.Serializable;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

/**
 * The type Food add response.
 */
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

    /**
     * Gets food.
     *
     * @return the food
     */
    public Integer getFood() {
        return food;
    }

    /**
     * Sets food.
     *
     * @param food the food
     */
    public void setFood(Integer food) {
        this.food = food;
    }

    /**
     * Gets ingredient.
     *
     * @return the ingredient
     */
    public Integer getIngredient() {
        return ingredient;
    }

    /**
     * Sets ingredient.
     *
     * @param ingredient the ingredient
     */
    public void setIngredient(Integer ingredient) {
        this.ingredient = ingredient;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public Integer getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * Gets unit.
     *
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets unit.
     *
     * @param unit the unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

}