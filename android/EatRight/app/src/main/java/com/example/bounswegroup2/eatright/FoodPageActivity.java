package com.example.bounswegroup2.eatright;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

import com.example.bounswegroup2.Models.Food;
import com.example.bounswegroup2.Models.Ingredient;

import java.util.ArrayList;

public class FoodPageActivity extends AppCompatActivity {

    private TextView foodName;
    private TextView totalCalories;
    private TextView totalCarbohydrate;
    private TextView totalProtein;
    private TextView totalFat;
    private ArrayList<Ingredient> ingredients;
    private Integer cal;
    private Integer pro;
    private Integer carb;
    private Integer fat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_page);
        Intent intent = getIntent();
        Food food = (Food)intent.getExtras().getSerializable("food");

        ingredients=new ArrayList<Ingredient>();
        ingredients=food.getIngredients();

        pro=0;
        carb=0;
        fat=0;
        cal=0;
        for(int i=0; i<ingredients.size(); i++){
            pro = pro + ingredients.get(i).getProtein();
            carb = carb + ingredients.get(i).getCarb();
            fat = fat + ingredients.get(i).getFat();
            cal = cal + ingredients.get(i).getEnergy();
        }

        foodName = (TextView) findViewById(R.id.food_name_text);
        foodName.setText(food.getName());

        totalCalories = (TextView) findViewById(R.id.total_calories_result_text);
        totalCalories.setText(cal.toString());

        totalCarbohydrate = (TextView) findViewById(R.id.carb_result_text);
        totalCarbohydrate.setText(carb.toString());

        totalFat = (TextView) findViewById(R.id.fat_result_text);
        totalFat.setText(fat.toString());

        totalProtein = (TextView) findViewById(R.id.protein_result_text);
        totalProtein.setText(pro.toString());
    }
}
