package com.example.bounswegroup2.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

/**
 * The type Food.
 */
public class Food implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("inclusions")
    @Expose
    private List<Inclusion> inclusions = null;
    @SerializedName("comments")
    @Expose
    private List<FoodComment> comments = null;
   /* @SerializedName("rates")
    @Expose
    private List<String> rates = null;*/
    @SerializedName("restaurant")
    @Expose
    private Restaurant restaurant;
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
    @SerializedName("ingredients")
    @Expose
    private List<Ingredient> ingredients = null;
    @SerializedName("weight")
    @Expose
    private Double weight;
    @SerializedName("details")
    @Expose
    private Details details;
    @SerializedName("rate")
    @Expose
    private Double rate;
    @SerializedName("tags")
    @Expose
    private List<FoodTag> tags;
    private final static transient long serialVersionUID = -1659623804730649438L;

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
     * Gets inclusions.
     *
     * @return the inclusions
     */
    public List<Inclusion> getInclusions() {
        return inclusions;
    }

    /**
     * Sets inclusions.
     *
     * @param inclusions the inclusions
     */
    public void setInclusions(List<Inclusion> inclusions) {
        this.inclusions = inclusions;
    }

    /**
     * Gets comments.
     *
     * @return the comments
     */
    public List<FoodComment> getComments() {
        return comments;
    }

    /**
     * Sets comments.
     *
     * @param comments the comments
     */
    public void setComments(List<FoodComment> comments) {
        this.comments = comments;
    }

 /*   public List<FoodRate> getRates() {
        return rates;
    }

    public void setRates(List<FoodRate> rates) {
        this.rates = rates;
    }*/

    /**
     * Gets restaurant.
     *
     * @return the restaurant
     */
    public Restaurant getRestaurant() {
        return restaurant;
    }

    /**
     * Sets restaurant.
     *
     * @param restaurant the restaurant
     */
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
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
     * Gets ingredients.
     *
     * @return the ingredients
     */
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     * Sets ingredients.
     *
     * @param ingredients the ingredients
     */
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Gets weight.
     *
     * @return the weight
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * Sets weight.
     *
     * @param weight the weight
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    /**
     * Gets details.
     *
     * @return the details
     */
    public Details getDetails() {
        return details;
    }

    /**
     * Sets details.
     *
     * @param details the details
     */
    public void setDetails(Details details) {
        this.details = details;
    }


    /**
     * The constant caToZ.
     */
    public static transient  Comparator<Food> caToZ = new Comparator<Food>() {
        @Override
        public int compare(Food food, Food t1) {
            return food.getName().compareToIgnoreCase(t1.getName());
        }
    };
    /**
     * The constant czToA.
     */
    public static transient  Comparator<Food> czToA = new Comparator<Food>() {
        @Override
        public int compare(Food food, Food t1) {
            return t1.getName().compareToIgnoreCase(food.getName());
        }
    };

    /**
     * The constant caToZRating.
     */
    public static transient  Comparator<Food> caToZRating = new Comparator<Food>() {
        @Override
        public int compare(Food food, Food t1) {
            if(food.getRate() < t1.getRate()) return 1;
            if(food.getRate() > t1.getRate()) return -1;
            return 0;
        }
    };
    /**
     * The constant czToARating.
     */
    public static transient  Comparator<Food> czToARating = new Comparator<Food>() {
        @Override
        public int compare(Food food, Food t1) {
            if(food.getRate() > t1.getRate())  return 1;
            if(food.getRate() < t1.getRate()) return -1;
            return 0;
        }
    };

    /**
     * Gets rate.
     *
     * @return the rate
     */
    public Double getRate() {
        return rate;
    }

    /**
     * Sets rate.
     *
     * @param rate the rate
     */
    public void setRate(Double rate) {
        this.rate = rate;
    }

    /**
     * Gets tags.
     *
     * @return the tags
     */
    public List<FoodTag> getTags() {
        return tags;
    }

    /**
     * Sets tags.
     *
     * @param tags the tags
     */
    public void setTags(List<FoodTag> tags) {
        this.tags = tags;
    }
/*
    public int getEnergy() {
        return energy;
    }

    public int getPro() {
        return pro;
    }

    public int getCarb() {
        return carb;
    }

    public int getFat() {
        return fat;
    }*/
}