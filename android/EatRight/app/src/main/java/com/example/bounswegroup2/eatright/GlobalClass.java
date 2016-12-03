package com.example.bounswegroup2.eatright;

/**
 * Created by Enes on 29.11.2016.
 */

import android.app.Application;

import com.example.bounswegroup2.Models.Food;

import java.util.ArrayList;

public class GlobalClass extends Application{
    private ArrayList<Food> foodsInBox = new ArrayList<>();

    public ArrayList<Food> getFoodsInBox() {
        return foodsInBox;
    }
    public void setFoodsInBox(ArrayList<Food> foodsInBox) {
        this.foodsInBox = foodsInBox;
    }
    public void addFoodToBox(Food f){
        foodsInBox.add(f);
    }
}