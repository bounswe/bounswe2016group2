package com.example.bounswegroup2.Models;

/**
 * Created by yigitozgumus on 11/12/16.
 */

import android.view.ViewDebug;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Food implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("ingredients")
    @Expose
    private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
    @SerializedName("rating")
    @Expose
    private double rating;

    @SerializedName("photo_links")
    @Expose
    private ArrayList<String> photoLinks = new ArrayList<>();

    public Food(String name, String slug) {
       this.name = name; this.slug = slug;
    }

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

    /**
     *
     * @return
     * The ingredients
     */
    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     *
     * @param ingredients
     * The ingredients
     */
    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public ArrayList<String> getPhotoLinks() {
        return photoLinks;
    }

    public void setPhotoLinks(ArrayList<String> photoLinks) {
        this.photoLinks = photoLinks;
    }



    public static Comparator<Food> caToZ = new Comparator<Food>() {
        @Override
        public int compare(Food food, Food t1) {
            return food.getName().compareToIgnoreCase(t1.getName());
        }
    };
    public static Comparator<Food> czToA = new Comparator<Food>() {
        @Override
        public int compare(Food food, Food t1) {
            return t1.getName().compareToIgnoreCase(food.getName());
        }
    };
    public static Comparator<Food> caToZRating = new Comparator<Food>() {
        @Override
        public int compare(Food food, Food t1) {
            if(food.getRating() < t1.getRating()) return 1;
            if(food.getRating() > t1.getRating()) return -1;
            return 0;
        }
    };
    public static Comparator<Food> czToARating = new Comparator<Food>() {
        @Override
        public int compare(Food food, Food t1) {
            if(food.getRating() > t1.getRating()) return 1;
            if(food.getRating() < t1.getRating()) return -1;
            return 0;
        }
    };

}
