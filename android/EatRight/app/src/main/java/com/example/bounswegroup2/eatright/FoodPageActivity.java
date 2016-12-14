package com.example.bounswegroup2.eatright;

import android.content.Context;
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

import com.example.bounswegroup2.Models.Food;
import com.example.bounswegroup2.Models.Ingredient;
import com.example.bounswegroup2.Models.Restaurant;
import com.example.bounswegroup2.Utils.ApiInterface;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodPageActivity extends AppCompatActivity {

    private TextView serverName;
    private TextView foodName;
    private TextView totalCalories;
    private TextView totalCarbohydrate;
    private TextView totalProtein;
    private TextView totalFat;
    private ArrayList<Ingredient> ingredients;
    private Food fSeen;
    private ImageView imageView;
    Button btnOpenIngredients;
    Button btnTastedIt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_page);
        final Intent intent = getIntent();
        fSeen = (Food)intent.getExtras().getSerializable("food");

        imageView = (ImageView)findViewById(R.id.food_image);
        Picasso.with(getApplicationContext())
                .load(fSeen.getPhoto())
                .placeholder(null)
                .fit()
                .centerInside()
                .into(imageView);
        ingredients= (ArrayList<Ingredient>) fSeen.getIngredients();

        //connection to server page
        ApiInterface test = ApiInterface.retrofit.create(ApiInterface.class);
        Call<Restaurant> rCalled=test.getRestaurant(fSeen.getRestaurant());
        rCalled.enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                Restaurant rSeen= response.body();
                serverName = (TextView) findViewById(R.id.server_button);
                serverName.setText(rSeen.getName());
            }

            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {

            }
        });

        foodName = (TextView) findViewById(R.id.food_name_text);
        foodName.setText(fSeen.getName());
        fSeen.setFields();
        totalCalories = (TextView) findViewById(R.id.total_calories_result_text);
        totalCalories.setText(""+fSeen.getEnergy());

        totalCarbohydrate = (TextView) findViewById(R.id.carb_result_text);
        totalCarbohydrate.setText(""+fSeen.getCarb());

        totalFat = (TextView) findViewById(R.id.fat_result_text);
        totalFat.setText(""+fSeen.getFat());

        totalProtein = (TextView) findViewById(R.id.protein_result_text);
        totalProtein.setText(""+fSeen.getPro());

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