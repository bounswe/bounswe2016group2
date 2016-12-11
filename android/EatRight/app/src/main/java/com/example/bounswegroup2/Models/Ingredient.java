package com.example.bounswegroup2.Models;

/**
 * Created by yigitozgumus on 11/12/16.
 */

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
    private Object photo;
    private final static long serialVersionUID = 8877731895154071859L;

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
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
     * The energy
     */
    public Double getEnergy() {
        return energy;
    }

    /**
     *
     * @param energy
     * The energy
     */
    public void setEnergy(Double energy) {
        this.energy = energy;
    }

    /**
     *
     * @return
     * The protein
     */
    public Double getProtein() {
        return protein;
    }

    /**
     *
     * @param protein
     * The protein
     */
    public void setProtein(Double protein) {
        this.protein = protein;
    }

    /**
     *
     * @return
     * The carb
     */
    public Double getCarb() {
        return carb;
    }

    /**
     *
     * @param carb
     * The carb
     */
    public void setCarb(Double carb) {
        this.carb = carb;
    }

    /**
     *
     * @return
     * The fat
     */
    public Double getFat() {
        return fat;
    }

    /**
     *
     * @param fat
     * The fat
     */
    public void setFat(Double fat) {
        this.fat = fat;
    }

    /**
     *
     * @return
     * The measureValue
     */
    public Double getMeasureValue() {
        return measureValue;
    }

    /**
     *
     * @param measureValue
     * The measureValue
     */
    public void setMeasureValue(Double measureValue) {
        this.measureValue = measureValue;
    }

    /**
     *
     * @return
     * The measureUnit
     */
    public String getMeasureUnit() {
        return measureUnit;
    }

    /**
     *
     * @param measureUnit
     * The measureUnit
     */
    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    /**
     *
     * @return
     * The defaultValue
     */
    public Double getDefaultValue() {
        return defaultValue;
    }

    /**
     *
     * @param defaultValue
     * The defaultValue
     */
    public void setDefaultValue(Double defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     *
     * @return
     * The defaultUnit
     */
    public String getDefaultUnit() {
        return defaultUnit;
    }

    /**
     *
     * @param defaultUnit
     * The defaultUnit
     */
    public void setDefaultUnit(String defaultUnit) {
        this.defaultUnit = defaultUnit;
    }

    /**
     *
     * @return
     * The saturatedFat
     */
    public Double getSaturatedFat() {
        return saturatedFat;
    }

    /**
     *
     * @param saturatedFat
     * The saturatedFat
     */
    public void setSaturatedFat(Double saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    /**
     *
     * @return
     * The sugar
     */
    public Double getSugar() {
        return sugar;
    }

    /**
     *
     * @param sugar
     * The sugar
     */
    public void setSugar(Double sugar) {
        this.sugar = sugar;
    }

    /**
     *
     * @return
     * The fibre
     */
    public Double getFibre() {
        return fibre;
    }

    /**
     *
     * @param fibre
     * The fibre
     */
    public void setFibre(Double fibre) {
        this.fibre = fibre;
    }

    /**
     *
     * @return
     * The cholesterol
     */
    public Double getCholesterol() {
        return cholesterol;
    }

    /**
     *
     * @param cholesterol
     * The cholesterol
     */
    public void setCholesterol(Double cholesterol) {
        this.cholesterol = cholesterol;
    }

    /**
     *
     * @return
     * The calcium
     */
    public Double getCalcium() {
        return calcium;
    }

    /**
     *
     * @param calcium
     * The calcium
     */
    public void setCalcium(Double calcium) {
        this.calcium = calcium;
    }

    /**
     *
     * @return
     * The iron
     */
    public Double getIron() {
        return iron;
    }

    /**
     *
     * @param iron
     * The iron
     */
    public void setIron(Double iron) {
        this.iron = iron;
    }

    /**
     *
     * @return
     * The sodium
     */
    public Double getSodium() {
        return sodium;
    }

    /**
     *
     * @param sodium
     * The sodium
     */
    public void setSodium(Double sodium) {
        this.sodium = sodium;
    }

    /**
     *
     * @return
     * The potassium
     */
    public Double getPotassium() {
        return potassium;
    }

    /**
     *
     * @param potassium
     * The potassium
     */
    public void setPotassium(Double potassium) {
        this.potassium = potassium;
    }

    /**
     *
     * @return
     * The magnesium
     */
    public Double getMagnesium() {
        return magnesium;
    }

    /**
     *
     * @param magnesium
     * The magnesium
     */
    public void setMagnesium(Double magnesium) {
        this.magnesium = magnesium;
    }

    /**
     *
     * @return
     * The phosphorus
     */
    public Double getPhosphorus() {
        return phosphorus;
    }

    /**
     *
     * @param phosphorus
     * The phosphorus
     */
    public void setPhosphorus(Double phosphorus) {
        this.phosphorus = phosphorus;
    }

    /**
     *
     * @return
     * The thiamin
     */
    public Double getThiamin() {
        return thiamin;
    }

    /**
     *
     * @param thiamin
     * The thiamin
     */
    public void setThiamin(Double thiamin) {
        this.thiamin = thiamin;
    }

    /**
     *
     * @return
     * The riboflavin
     */
    public Double getRiboflavin() {
        return riboflavin;
    }

    /**
     *
     * @param riboflavin
     * The riboflavin
     */
    public void setRiboflavin(Double riboflavin) {
        this.riboflavin = riboflavin;
    }

    /**
     *
     * @return
     * The niacin
     */
    public Double getNiacin() {
        return niacin;
    }

    /**
     *
     * @param niacin
     * The niacin
     */
    public void setNiacin(Double niacin) {
        this.niacin = niacin;
    }

    /**
     *
     * @return
     * The folate
     */
    public Double getFolate() {
        return folate;
    }

    /**
     *
     * @param folate
     * The folate
     */
    public void setFolate(Double folate) {
        this.folate = folate;
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
     * The photo
     */
    public Object getPhoto() {
        return photo;
    }

    /**
     *
     * @param photo
     * The photo
     */
    public void setPhoto(Object photo) {
        this.photo = photo;
    }

}