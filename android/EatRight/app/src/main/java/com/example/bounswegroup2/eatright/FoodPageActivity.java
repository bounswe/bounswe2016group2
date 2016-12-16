package com.example.bounswegroup2.eatright;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.ListPopupWindow;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.bounswegroup2.Models.Details;
import com.example.bounswegroup2.Models.Food;
import com.example.bounswegroup2.Models.Ingredient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FoodPageActivity extends AppCompatActivity {

    private TextView foodName;
    private TextView totalCalories;
    private TextView totalCarbohydrate;
    private TextView totalProtein;
    private TextView totalFat;
    private ArrayList<Ingredient> ingredients;
    private Details details;
    private Food fSeen;
    private ImageView imageView;
    Button btnOpenIngredients;
    Button btnTastedIt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_page);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        details = (Details) b.getSerializable("details");
        String name = (String) b.getSerializable("name");

        imageView = (ImageView)findViewById(R.id.food_image);
        Picasso.with(getApplicationContext())
                .load((String) b.getSerializable("photo"))
                .placeholder(null)
                .fit()
                .centerInside()
                .into(imageView);
        ingredients= (ArrayList<Ingredient>) b.getSerializable("ingr");
      //  details = fSeen.getDetails();
        foodName = (TextView) findViewById(R.id.food_name_text);
        foodName.setText(name);
        totalCalories = (TextView) findViewById(R.id.total_calories_result_text);
        totalCalories.setText(details.getEnergy().toString());

        totalCarbohydrate = (TextView) findViewById(R.id.carb_result_text);
        totalCarbohydrate.setText(details.getCarb().getWeight().toString());

        totalFat = (TextView) findViewById(R.id.fat_result_text);
        totalFat.setText(details.getFat().getWeight().toString());

        totalProtein = (TextView) findViewById(R.id.protein_result_text);
        totalProtein.setText(details.getProtein().getWeight().toString());

        btnOpenIngredients = (Button) findViewById(R.id.ingredients_button);
        btnOpenIngredients.setOnClickListener(new OnClickListener() {

            public void onClick(View v){
                Intent inten = new Intent(FoodPageActivity.this,IngredientPage.class);
                inten.putExtra("ingredients",ingredients);
                startActivity(inten);
                finish();
            }
        });
        btnTastedIt = (Button) findViewById(R.id.tasted_it_button);
        btnTastedIt.setOnClickListener(tastedButtonclicked());
    }

    private OnClickListener tastedButtonclicked() {
        return new OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: use ate api
            }
        };
    }

}