package com.example.bounswegroup2.Models;

/**
 * Created by Enes on 17.12.2016.
 */

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

<<<<<<< HEAD
=======
/**
 * The type Restaurant more.
 */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
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
<<<<<<< HEAD
    private double rate;
    private final static long serialVersionUID = -7186152876703035983L;

=======
    private Float rate;
    private final static long serialVersionUID = -7186152876703035983L;

    /**
     * Gets id.
     *
     * @return the id
     */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public Integer getId() {
        return id;
    }

<<<<<<< HEAD
=======
    /**
     * Sets id.
     *
     * @param id the id
     */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public void setId(Integer id) {
        this.id = id;
    }

<<<<<<< HEAD
=======
    /**
     * Gets foods.
     *
     * @return the foods
     */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public List<FoodLess> getFoods() {
        return foods;
    }

<<<<<<< HEAD
=======
    /**
     * Sets foods.
     *
     * @param foods the foods
     */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public void setFoods(List<FoodLess> foods) {
        this.foods = foods;
    }

<<<<<<< HEAD
=======
    /**
     * Gets comments.
     *
     * @return the comments
     */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public List<RestaurantComment> getComments() {
        return comments;
    }

<<<<<<< HEAD
=======
    /**
     * Sets comments.
     *
     * @param comments the comments
     */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public void setComments(List<RestaurantComment> comments) {
        this.comments = comments;
    }

<<<<<<< HEAD
=======
    /**
     * Gets user.
     *
     * @return the user
     */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public User getUser() {
        return user;
    }

<<<<<<< HEAD
=======
    /**
     * Sets user.
     *
     * @param user the user
     */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public void setUser(User user) {
        this.user = user;
    }

<<<<<<< HEAD
=======
    /**
     * Gets name.
     *
     * @return the name
     */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public String getName() {
        return name;
    }

<<<<<<< HEAD
=======
    /**
     * Sets name.
     *
     * @param name the name
     */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public void setName(String name) {
        this.name = name;
    }

<<<<<<< HEAD
=======
    /**
     * Gets slug.
     *
     * @return the slug
     */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public String getSlug() {
        return slug;
    }

<<<<<<< HEAD
=======
    /**
     * Sets slug.
     *
     * @param slug the slug
     */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public void setSlug(String slug) {
        this.slug = slug;
    }

<<<<<<< HEAD
=======
    /**
     * Gets description.
     *
     * @return the description
     */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public String getDescription() {
        return description;
    }

<<<<<<< HEAD
=======
    /**
     * Sets description.
     *
     * @param description the description
     */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public void setDescription(String description) {
        this.description = description;
    }

<<<<<<< HEAD
=======
    /**
     * Gets photo.
     *
     * @return the photo
     */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public String getPhoto() {
        return photo;
    }

<<<<<<< HEAD
=======
    /**
     * Sets photo.
     *
     * @param photo the photo
     */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public void setPhoto(String photo) {
        this.photo = photo;
    }

<<<<<<< HEAD
    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
=======
    /**
     * Gets rate.
     *
     * @return the rate
     */
    public float getRate() {
        return rate;
    }

    /**
     * Sets rate.
     *
     * @param rate the rate
     */
    public void setRate(Float rate) {
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
        this.rate = rate;
    }

}