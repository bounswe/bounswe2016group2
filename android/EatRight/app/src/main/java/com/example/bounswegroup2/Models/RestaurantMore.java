package com.example.bounswegroup2.Models;

/**
 * Created by Enes on 17.12.2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * The type Restaurant more.
 */
public class RestaurantMore implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("foods")
    @Expose
    private List<FoodLess> foods = null;
    @SerializedName("comments")
    @Expose
    private List<RestaurantComment> comments = null;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("rate")
    @Expose
    private double rate;
    private final static long serialVersionUID = -7186152876703035983L;

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
     * Gets foods.
     *
     * @return the foods
     */
    public List<FoodLess> getFoods() {
        return foods;
    }

    /**
     * Sets foods.
     *
     * @param foods the foods
     */
    public void setFoods(List<FoodLess> foods) {
        this.foods = foods;
    }

    /**
     * Gets comments.
     *
     * @return the comments
     */
    public List<RestaurantComment> getComments() {
        return comments;
    }

    /**
     * Sets comments.
     *
     * @param comments the comments
     */
    public void setComments(List<RestaurantComment> comments) {
        this.comments = comments;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
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
     * Gets photo.
     *
     * @return the photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * Sets photo.
     *
     * @param photo the photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * Gets rate.
     *
     * @return the rate
     */
    public double getRate() {
        return rate;
    }

    /**
     * Sets rate.
     *
     * @param rate the rate
     */
    public void setRate(double rate) {
        this.rate = rate;
    }

}