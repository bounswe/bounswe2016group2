package com.example.bounswegroup2.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Enes on 15.12.2016.
 */
public class Others implements Serializable
{

    @SerializedName("sodium")
    @Expose
    private Double sodium;
    @SerializedName("fibre")
    @Expose
    private Double fibre;
    @SerializedName("thiamin")
    @Expose
    private Double thiamin;
    @SerializedName("saturatedFat")
    @Expose
    private Double saturatedFat;
    @SerializedName("folate")
    @Expose
    private Double folate;
    @SerializedName("calcium")
    @Expose
    private Double calcium;
    @SerializedName("iron")
    @Expose
    private Double iron;
    @SerializedName("riboflavin")
    @Expose
    private Double riboflavin;
    @SerializedName("sugar")
    @Expose
    private Double sugar;
    @SerializedName("potassium")
    @Expose
    private Double potassium;
    @SerializedName("phosphorus")
    @Expose
    private Double phosphorus;
    @SerializedName("niacin")
    @Expose
    private Double niacin;
    @SerializedName("cholesterol")
    @Expose
    private Double cholesterol;
    @SerializedName("magnesium")
    @Expose
    private Double magnesium;
    private final static long serialVersionUID = -7084869837255010982L;

    /**
     * Gets sodium.
     *
     * @return the sodium
     */
    public Double getSodium() {
        return sodium;
    }

    /**
     * Sets sodium.
     *
     * @param sodium the sodium
     */
    public void setSodium(Double sodium) {
        this.sodium = sodium;
    }

    /**
     * Gets fibre.
     *
     * @return the fibre
     */
    public Double getFibre() {
        return fibre;
    }

    /**
     * Sets fibre.
     *
     * @param fibre the fibre
     */
    public void setFibre(Double fibre) {
        this.fibre = fibre;
    }

    /**
     * Gets thiamin.
     *
     * @return the thiamin
     */
    public Double getThiamin() {
        return thiamin;
    }

    /**
     * Sets thiamin.
     *
     * @param thiamin the thiamin
     */
    public void setThiamin(Double thiamin) {
        this.thiamin = thiamin;
    }

    /**
     * Gets saturated fat.
     *
     * @return the saturated fat
     */
    public Double getSaturatedFat() {
        return saturatedFat;
    }

    /**
     * Sets saturated fat.
     *
     * @param saturatedFat the saturated fat
     */
    public void setSaturatedFat(Double saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    /**
     * Gets folate.
     *
     * @return the folate
     */
    public Double getFolate() {
        return folate;
    }

    /**
     * Sets folate.
     *
     * @param folate the folate
     */
    public void setFolate(Double folate) {
        this.folate = folate;
    }

    /**
     * Gets calcium.
     *
     * @return the calcium
     */
    public Double getCalcium() {
        return calcium;
    }

    /**
     * Sets calcium.
     *
     * @param calcium the calcium
     */
    public void setCalcium(Double calcium) {
        this.calcium = calcium;
    }

    /**
     * Gets iron.
     *
     * @return the iron
     */
    public Double getIron() {
        return iron;
    }

    /**
     * Sets iron.
     *
     * @param iron the iron
     */
    public void setIron(Double iron) {
        this.iron = iron;
    }

    /**
     * Gets riboflavin.
     *
     * @return the riboflavin
     */
    public Double getRiboflavin() {
        return riboflavin;
    }

    /**
     * Sets riboflavin.
     *
     * @param riboflavin the riboflavin
     */
    public void setRiboflavin(Double riboflavin) {
        this.riboflavin = riboflavin;
    }

    /**
     * Gets sugar.
     *
     * @return the sugar
     */
    public Double getSugar() {
        return sugar;
    }

    /**
     * Sets sugar.
     *
     * @param sugar the sugar
     */
    public void setSugar(Double sugar) {
        this.sugar = sugar;
    }

    /**
     * Gets potassium.
     *
     * @return the potassium
     */
    public Double getPotassium() {
        return potassium;
    }

    /**
     * Sets potassium.
     *
     * @param potassium the potassium
     */
    public void setPotassium(Double potassium) {
        this.potassium = potassium;
    }

    /**
     * Gets phosphorus.
     *
     * @return the phosphorus
     */
    public Double getPhosphorus() {
        return phosphorus;
    }

    /**
     * Sets phosphorus.
     *
     * @param phosphorus the phosphorus
     */
    public void setPhosphorus(Double phosphorus) {
        this.phosphorus = phosphorus;
    }

    /**
     * Gets niacin.
     *
     * @return the niacin
     */
    public Double getNiacin() {
        return niacin;
    }

    /**
     * Sets niacin.
     *
     * @param niacin the niacin
     */
    public void setNiacin(Double niacin) {
        this.niacin = niacin;
    }

    /**
     * Gets cholesterol.
     *
     * @return the cholesterol
     */
    public Double getCholesterol() {
        return cholesterol;
    }

    /**
     * Sets cholesterol.
     *
     * @param cholesterol the cholesterol
     */
    public void setCholesterol(Double cholesterol) {
        this.cholesterol = cholesterol;
    }

    /**
     * Gets magnesium.
     *
     * @return the magnesium
     */
    public Double getMagnesium() {
        return magnesium;
    }

    /**
     * Sets magnesium.
     *
     * @param magnesium the magnesium
     */
    public void setMagnesium(Double magnesium) {
        this.magnesium = magnesium;
    }

}