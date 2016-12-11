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
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("user")
    @Expose
    private Integer user;
    @SerializedName("restaurant")
    @Expose
    private Integer restaurant;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("ingredients")
    @Expose
    private List<Ingredient> ingredients = null;

    private  int energy = 0;
    private  int pro = 0;
    private  int carb = 0;
    private  int fat = 0;
    private final static long serialVersionUID = 697699670771933128L;

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
     * The user
     */
    public Integer getUser() {
        return user;
    }

    /**
     *
     * @param user
     * The user
     */
    public void setUser(Integer user) {
        this.user = user;
    }

    /**
     *
     * @return
     * The restaurant
     */
    public Integer getRestaurant() {
        return restaurant;
    }

    /**
     *
     * @param restaurant
     * The restaurant
     */
    public void setRestaurant(Integer restaurant) {
        this.restaurant = restaurant;
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
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     *
     * @param photo
     * The photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     *
     * @return
     * The ingredients
     */
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     *
     * @param ingredients
     * The ingredients
     */
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public  void setFields(){
        for (Ingredient i : this.ingredients) {
            energy+=i.getEnergy();
            pro+=i.getProtein();
            carb+=i.getCarb();
            fat+=i.getFat();
        }
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
            //if(food.getRating() < t1.getRating()) return 1;
            //if(food.getRating() > t1.getRating()) return -1;
            return 0;
        }
    };
    public static Comparator<Food> czToARating = new Comparator<Food>() {
        @Override
        public int compare(Food food, Food t1) {
            //if(food.getRating() > t1.getRating()) return 1;
            //if(food.getRating() < t1.getRating()) return -1;
            return 0;
        }
    };

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
    }
}