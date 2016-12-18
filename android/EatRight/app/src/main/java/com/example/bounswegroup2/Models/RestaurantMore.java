package com.example.bounswegroup2.Models;

/**
 * Created by Enes on 17.12.2016.
 */

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
    /*@SerializedName("rate")
    @Expose
    private Float rate;*/
    private final static long serialVersionUID = -7186152876703035983L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<FoodLess> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodLess> foods) {
        this.foods = foods;
    }

    public List<RestaurantComment> getComments() {
        return comments;
    }

    public void setComments(List<RestaurantComment> comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /*public float getRate() {
        return rate;
    }*/

    /*public void setRate(Float rate) {
        this.rate = rate;
    }*/

}