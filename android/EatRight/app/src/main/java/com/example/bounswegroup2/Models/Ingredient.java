package com.example.bounswegroup2.Models;

/**
 * Created by yigitozgumus on 11/12/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * The type Ingredient.
 */
public class Ingredient implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("energy")
    @Expose
    private Double energy;
    @SerializedName("protein")
    @Expose
    private Double protein;
    @SerializedName("carb")
    @Expose
    private Double carb;
    @SerializedName("fat")
    @Expose
    private Double fat;
    @SerializedName("measureValue")
    @Expose
    private Double measureValue;
    @SerializedName("measureUnit")
    @Expose
    private String measureUnit;
    @SerializedName("defaultValue")
    @Expose
    private Double defaultValue;
    @SerializedName("defaultUnit")
    @Expose
    private String defaultUnit;
    @SerializedName("saturatedFat")
    @Expose
    private Double saturatedFat;
    @SerializedName("sugar")
    @Expose
    private Double sugar;
    @SerializedName("fibre")
    @Expose
    private Double fibre;
    @SerializedName("cholesterol")
    @Expose
    private Double cholesterol;
    @SerializedName("calcium")
    @Expose
    private Double calcium;
    @SerializedName("iron")
    @Expose
    private Double iron;
    @SerializedName("sodium")
    @Expose
    private Double sodium;
    @SerializedName("potassium")
    @Expose
    private Double potassium;
    @SerializedName("magnesium")
    @Expose
    private Double magnesium;
    @SerializedName("phosphorus")
    @Expose
    private Double phosphorus;
    @SerializedName("thiamin")
    @Expose
    private Double thiamin;
    @SerializedName("riboflavin")
    @Expose
    private Double riboflavin;
    @SerializedName("niacin")
    @Expose
    private Double niacin;
    @SerializedName("folate")
    @Expose
    private Double folate;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("photo")
    @Expose
    private String photo;
    private final static long serialVersionUID = 8877731895154071859L;

    /**
     * Gets id.
     *
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets slug.
     *
     * @return The slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     * Sets slug.
     *
     * @param slug The slug
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     * Gets energy.
     *
     * @return The energy
     */
    public Double getEnergy() {
        return energy;
    }

    /**
     * Sets energy.
     *
     * @param energy The energy
     */
    public void setEnergy(Double energy) {
        this.energy = energy;
    }

    /**
     * Gets protein.
     *
     * @return The protein
     */
    public Double getProtein() {
        return protein;
    }

    /**
     * Sets protein.
     *
     * @param protein The protein
     */
    public void setProtein(Double protein) {
        this.protein = protein;
    }

    /**
     * Gets carb.
     *
     * @return The carb
     */
    public Double getCarb() {
        return carb;
    }

    /**
     * Sets carb.
     *
     * @param carb The carb
     */
    public void setCarb(Double carb) {
        this.carb = carb;
    }

    /**
     * Gets fat.
     *
     * @return The fat
     */
    public Double getFat() {
        return fat;
    }

    /**
     * Sets fat.
     *
     * @param fat The fat
     */
    public void setFat(Double fat) {
        this.fat = fat;
    }

    /**
     * Gets measure value.
     *
     * @return The measureValue
     */
    public Double getMeasureValue() {
        return measureValue;
    }

    /**
     * Sets measure value.
     *
     * @param measureValue The measureValue
     */
    public void setMeasureValue(Double measureValue) {
        this.measureValue = measureValue;
    }

    /**
     * Gets measure unit.
     *
     * @return The measureUnit
     */
    public String getMeasureUnit() {
        return measureUnit;
    }

    /**
     * Sets measure unit.
     *
     * @param measureUnit The measureUnit
     */
    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    /**
     * Gets default value.
     *
     * @return The defaultValue
     */
    public Double getDefaultValue() {
        return defaultValue;
    }

    /**
     * Sets default value.
     *
     * @param defaultValue The defaultValue
     */
    public void setDefaultValue(Double defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * Gets default unit.
     *
     * @return The defaultUnit
     */
    public String getDefaultUnit() {
        return defaultUnit;
    }

    /**
     * Sets default unit.
     *
     * @param defaultUnit The defaultUnit
     */
    public void setDefaultUnit(String defaultUnit) {
        this.defaultUnit = defaultUnit;
    }

    /**
     * Gets saturated fat.
     *
     * @return The saturatedFat
     */
    public Double getSaturatedFat() {
        return saturatedFat;
    }

    /**
     * Sets saturated fat.
     *
     * @param saturatedFat The saturatedFat
     */
    public void setSaturatedFat(Double saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    /**
     * Gets sugar.
     *
     * @return The sugar
     */
    public Double getSugar() {
        return sugar;
    }

    /**
     * Sets sugar.
     *
     * @param sugar The sugar
     */
    public void setSugar(Double sugar) {
        this.sugar = sugar;
    }

    /**
     * Gets fibre.
     *
     * @return The fibre
     */
    public Double getFibre() {
        return fibre;
    }

    /**
     * Sets fibre.
     *
     * @param fibre The fibre
     */
    public void setFibre(Double fibre) {
        this.fibre = fibre;
    }

    /**
     * Gets cholesterol.
     *
     * @return The cholesterol
     */
    public Double getCholesterol() {
        return cholesterol;
    }

    /**
     * Sets cholesterol.
     *
     * @param cholesterol The cholesterol
     */
    public void setCholesterol(Double cholesterol) {
        this.cholesterol = cholesterol;
    }

    /**
     * Gets calcium.
     *
     * @return The calcium
     */
    public Double getCalcium() {
        return calcium;
    }

    /**
     * Sets calcium.
     *
     * @param calcium The calcium
     */
    public void setCalcium(Double calcium) {
        this.calcium = calcium;
    }

    /**
     * Gets iron.
     *
     * @return The iron
     */
    public Double getIron() {
        return iron;
    }

    /**
     * Sets iron.
     *
     * @param iron The iron
     */
    public void setIron(Double iron) {
        this.iron = iron;
    }

    /**
     * Gets sodium.
     *
     * @return The sodium
     */
    public Double getSodium() {
        return sodium;
    }

    /**
     * Sets sodium.
     *
     * @param sodium The sodium
     */
    public void setSodium(Double sodium) {
        this.sodium = sodium;
    }

    /**
     * Gets potassium.
     *
     * @return The potassium
     */
    public Double getPotassium() {
        return potassium;
    }

    /**
     * Sets potassium.
     *
     * @param potassium The potassium
     */
    public void setPotassium(Double potassium) {
        this.potassium = potassium;
    }

    /**
     * Gets magnesium.
     *
     * @return The magnesium
     */
    public Double getMagnesium() {
        return magnesium;
    }

    /**
     * Sets magnesium.
     *
     * @param magnesium The magnesium
     */
    public void setMagnesium(Double magnesium) {
        this.magnesium = magnesium;
    }

    /**
     * Gets phosphorus.
     *
     * @return The phosphorus
     */
    public Double getPhosphorus() {
        return phosphorus;
    }

    /**
     * Sets phosphorus.
     *
     * @param phosphorus The phosphorus
     */
    public void setPhosphorus(Double phosphorus) {
        this.phosphorus = phosphorus;
    }

    /**
     * Gets thiamin.
     *
     * @return The thiamin
     */
    public Double getThiamin() {
        return thiamin;
    }

    /**
     * Sets thiamin.
     *
     * @param thiamin The thiamin
     */
    public void setThiamin(Double thiamin) {
        this.thiamin = thiamin;
    }

    /**
     * Gets riboflavin.
     *
     * @return The riboflavin
     */
    public Double getRiboflavin() {
        return riboflavin;
    }

    /**
     * Sets riboflavin.
     *
     * @param riboflavin The riboflavin
     */
    public void setRiboflavin(Double riboflavin) {
        this.riboflavin = riboflavin;
    }

    /**
     * Gets niacin.
     *
     * @return The niacin
     */
    public Double getNiacin() {
        return niacin;
    }

    /**
     * Sets niacin.
     *
     * @param niacin The niacin
     */
    public void setNiacin(Double niacin) {
        this.niacin = niacin;
    }

    /**
     * Gets folate.
     *
     * @return The folate
     */
    public Double getFolate() {
        return folate;
    }

    /**
     * Sets folate.
     *
     * @param folate The folate
     */
    public void setFolate(Double folate) {
        this.folate = folate;
    }

    /**
     * Gets name.
     *
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets photo.
     *
     * @return The photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * Sets photo.
     *
     * @param photo The photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

}