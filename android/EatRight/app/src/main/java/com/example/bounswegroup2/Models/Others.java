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

    public Double getSodium() {
        return sodium;
    }

    public void setSodium(Double sodium) {
        this.sodium = sodium;
    }

    public Double getFibre() {
        return fibre;
    }

    public void setFibre(Double fibre) {
        this.fibre = fibre;
    }

    public Double getThiamin() {
        return thiamin;
    }

    public void setThiamin(Double thiamin) {
        this.thiamin = thiamin;
    }

    public Double getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(Double saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public Double getFolate() {
        return folate;
    }

    public void setFolate(Double folate) {
        this.folate = folate;
    }

    public Double getCalcium() {
        return calcium;
    }

    public void setCalcium(Double calcium) {
        this.calcium = calcium;
    }

    public Double getIron() {
        return iron;
    }

    public void setIron(Double iron) {
        this.iron = iron;
    }

    public Double getRiboflavin() {
        return riboflavin;
    }

    public void setRiboflavin(Double riboflavin) {
        this.riboflavin = riboflavin;
    }

    public Double getSugar() {
        return sugar;
    }

    public void setSugar(Double sugar) {
        this.sugar = sugar;
    }

    public Double getPotassium() {
        return potassium;
    }

    public void setPotassium(Double potassium) {
        this.potassium = potassium;
    }

    public Double getPhosphorus() {
        return phosphorus;
    }

    public void setPhosphorus(Double phosphorus) {
        this.phosphorus = phosphorus;
    }

    public Double getNiacin() {
        return niacin;
    }

    public void setNiacin(Double niacin) {
        this.niacin = niacin;
    }

    public Double getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(Double cholesterol) {
        this.cholesterol = cholesterol;
    }

    public Double getMagnesium() {
        return magnesium;
    }

    public void setMagnesium(Double magnesium) {
        this.magnesium = magnesium;
    }

}