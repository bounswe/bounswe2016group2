package com.example.bounswegroup2.Models;

/**
 * Created by yigitozgumus on 11/12/16.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ingredient {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("weight")
    @Expose
    private Integer weight;
    @SerializedName("energy")
    @Expose
    private Integer energy;
    @SerializedName("protein")
    @Expose
    private Integer protein;
    @SerializedName("carb")
    @Expose
    private Integer carb;
    @SerializedName("fat")
    @Expose
    private Integer fat;
    @SerializedName("saturatedFat")
    @Expose
    private Integer saturatedFat;
    @SerializedName("sugar")
    @Expose
    private Integer sugar;
    @SerializedName("fibre")
    @Expose
    private Double fibre;
    @SerializedName("cholesterol")
    @Expose
    private Integer cholesterol;
    @SerializedName("calcium")
    @Expose
    private Integer calcium;
    @SerializedName("iron")
    @Expose
    private Double iron;
    @SerializedName("sodium")
    @Expose
    private Integer sodium;
    @SerializedName("potassium")
    @Expose
    private Integer potassium;
    @SerializedName("magnesium")
    @Expose
    private Integer magnesium;
    @SerializedName("phosphorus")
    @Expose
    private Integer phosphorus;
    @SerializedName("thiamin")
    @Expose
    private Double thiamin;
    @SerializedName("riboflavin")
    @Expose
    private Integer riboflavin;
    @SerializedName("niacin")
    @Expose
    private Double niacin;
    @SerializedName("folate")
    @Expose
    private Integer folate;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slug")
    @Expose
    private String slug;

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
     * The weight
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     *
     * @param weight
     * The weight
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     *
     * @return
     * The energy
     */
    public Integer getEnergy() {
        return energy;
    }

    /**
     *
     * @param energy
     * The energy
     */
    public void setEnergy(Integer energy) {
        this.energy = energy;
    }

    /**
     *
     * @return
     * The protein
     */
    public Integer getProtein() {
        return protein;
    }

    /**
     *
     * @param protein
     * The protein
     */
    public void setProtein(Integer protein) {
        this.protein = protein;
    }

    /**
     *
     * @return
     * The carb
     */
    public Integer getCarb() {
        return carb;
    }

    /**
     *
     * @param carb
     * The carb
     */
    public void setCarb(Integer carb) {
        this.carb = carb;
    }

    /**
     *
     * @return
     * The fat
     */
    public Integer getFat() {
        return fat;
    }

    /**
     *
     * @param fat
     * The fat
     */
    public void setFat(Integer fat) {
        this.fat = fat;
    }

    /**
     *
     * @return
     * The saturatedFat
     */
    public Integer getSaturatedFat() {
        return saturatedFat;
    }

    /**
     *
     * @param saturatedFat
     * The saturatedFat
     */
    public void setSaturatedFat(Integer saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    /**
     *
     * @return
     * The sugar
     */
    public Integer getSugar() {
        return sugar;
    }

    /**
     *
     * @param sugar
     * The sugar
     */
    public void setSugar(Integer sugar) {
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
    public Integer getCholesterol() {
        return cholesterol;
    }

    /**
     *
     * @param cholesterol
     * The cholesterol
     */
    public void setCholesterol(Integer cholesterol) {
        this.cholesterol = cholesterol;
    }

    /**
     *
     * @return
     * The calcium
     */
    public Integer getCalcium() {
        return calcium;
    }

    /**
     *
     * @param calcium
     * The calcium
     */
    public void setCalcium(Integer calcium) {
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
    public Integer getSodium() {
        return sodium;
    }

    /**
     *
     * @param sodium
     * The sodium
     */
    public void setSodium(Integer sodium) {
        this.sodium = sodium;
    }

    /**
     *
     * @return
     * The potassium
     */
    public Integer getPotassium() {
        return potassium;
    }

    /**
     *
     * @param potassium
     * The potassium
     */
    public void setPotassium(Integer potassium) {
        this.potassium = potassium;
    }

    /**
     *
     * @return
     * The magnesium
     */
    public Integer getMagnesium() {
        return magnesium;
    }

    /**
     *
     * @param magnesium
     * The magnesium
     */
    public void setMagnesium(Integer magnesium) {
        this.magnesium = magnesium;
    }

    /**
     *
     * @return
     * The phosphorus
     */
    public Integer getPhosphorus() {
        return phosphorus;
    }

    /**
     *
     * @param phosphorus
     * The phosphorus
     */
    public void setPhosphorus(Integer phosphorus) {
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
    public Integer getRiboflavin() {
        return riboflavin;
    }

    /**
     *
     * @param riboflavin
     * The riboflavin
     */
    public void setRiboflavin(Integer riboflavin) {
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
    public Integer getFolate() {
        return folate;
    }

    /**
     *
     * @param folate
     * The folate
     */
    public void setFolate(Integer folate) {
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

}
