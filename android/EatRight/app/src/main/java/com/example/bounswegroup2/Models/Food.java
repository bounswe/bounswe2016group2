package com.example.bounswegroup2.Models;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
    private List<String> comments = null;
    @SerializedName("rates")
    @Expose
    private List<String> rates = null;
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
    private Integer weight;
    @SerializedName("details")
    @Expose
    private Details details;
    private final static transient long serialVersionUID = -1659623804730649438L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Inclusion> getInclusions() {
        return inclusions;
    }

    public void setInclusions(List<Inclusion> inclusions) {
        this.inclusions = inclusions;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public List<String> getRates() {
        return rates;
    }

    public void setRates(List<String> rates) {
        this.rates = rates;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Object getUser() {
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }


    public static transient  Comparator<Food> caToZ = new Comparator<Food>() {
        @Override
        public int compare(Food food, Food t1) {
            return food.getName().compareToIgnoreCase(t1.getName());
        }
    };
    public static transient  Comparator<Food> czToA = new Comparator<Food>() {
        @Override
        public int compare(Food food, Food t1) {
            return t1.getName().compareToIgnoreCase(food.getName());
        }
    };

    public static transient  Comparator<Food> caToZRating = new Comparator<Food>() {
        @Override
        public int compare(Food food, Food t1) {
         /*   if(Double.parseDouble(food.getRates().get(0)) < Double.parseDouble(t1.getRates().get(0))) return 1;
            if(Double.parseDouble(food.getRates().get(0)) > Double.parseDouble(t1.getRates().get(0))) return -1;*/
            return 0;
        }
    };
    public static transient  Comparator<Food> czToARating = new Comparator<Food>() {
        @Override
        public int compare(Food food, Food t1) {
            /*if(Double.parseDouble(food.getRates().get(0)) > Double.parseDouble(t1.getRates().get(0)))  return 1;
            if(Double.parseDouble(food.getRates().get(0)) < Double.parseDouble(t1.getRates().get(0))) return -1;*/
            return 0;
        }
    };
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