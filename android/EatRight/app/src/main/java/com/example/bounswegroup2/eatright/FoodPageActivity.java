package com.example.bounswegroup2.eatright;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import com.example.bounswegroup2.Models.Food;

public class FoodPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_page);
        Intent intent=getIntent();
        Food food = (Food)intent.getExtras().getSerializable("food");
    }
}
