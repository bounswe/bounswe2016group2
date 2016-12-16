package com.example.bounswegroup2.eatright;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.ListPopupWindow;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.bounswegroup2.Models.Details;
import com.example.bounswegroup2.Models.Food;
import com.example.bounswegroup2.Models.Ingredient;
import com.example.bounswegroup2.Models.Restaurant;
import com.example.bounswegroup2.Utils.ApiInterface;
import com.example.bounswegroup2.Utils.Constants;
import com.example.bounswegroup2.Utils.SessionManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodPageActivity extends AppCompatActivity {

    private TextView foodName;
    private TextView totalCalories;
    private TextView totalCarbohydrate;
    private TextView totalProtein;
    private TextView totalFat;
    private ArrayList<Ingredient> ingredients;
    private Details details;
    private Restaurant restaurant;
    private ImageView imageView;
    private Button restBut;
    private RatingBar showRatingBar;
    private RatingBar setRatingBar;
    private EditText commentText;
    private int foodID;
    private Button evalBut;
    private Button btnOpenIngredients;
    private Button btnTastedIt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_page);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        details = (Details) b.getSerializable("details");
        String name = (String) b.getSerializable("name");
        foodID = (int) b.getSerializable("foodid");
        imageView = (ImageView)findViewById(R.id.food_image);
        Picasso.with(getApplicationContext())
                .load((String) b.getSerializable("photo"))
                .placeholder(null)
                .fit()
                .centerInside()
                .into(imageView);
        ingredients= (ArrayList<Ingredient>) b.getSerializable("ingr");
        foodName = (TextView) findViewById(R.id.food_name_text);
        foodName.setText(name);
        totalCalories = (TextView) findViewById(R.id.total_calories_result_text);
        totalCalories.setText(details.getEnergy().toString());
        commentText = (EditText) findViewById(R.id.comment_text);
        totalCarbohydrate = (TextView) findViewById(R.id.carb_result_text);
        totalCarbohydrate.setText(details.getCarb().getWeight().toString());

        totalFat = (TextView) findViewById(R.id.fat_result_text);
        totalFat.setText(details.getFat().getWeight().toString());

        totalProtein = (TextView) findViewById(R.id.protein_result_text);
        totalProtein.setText(details.getProtein().getWeight().toString());
        showRatingBar = (RatingBar)findViewById(R.id.food_taste_ratingBar);
       // showRatingBar.setRating((float)b.getSerializable("rate"));
        setRatingBar = (RatingBar)findViewById(R.id.rate_food_taste_ratingBar) ;
        restaurant = (Restaurant) b.getSerializable("resta");
        restBut = (Button) findViewById(R.id.restBut);
        restBut.setText(restaurant.getName());
        restBut.setOnClickListener(restButClicked());
        evalBut = (Button) findViewById(R.id.save_eval);
        evalBut.setOnClickListener(saveButtonClicked());
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

    private OnClickListener restButClicked() {
    return new OnClickListener() {
        @Override
        public void onClick(View view) {
        Intent i = new Intent(FoodPageActivity.this,ServerPageActivity.class);
            Bundle b = new Bundle();
            b.putSerializable("resta",restaurant);
            i.putExtras(b);
            startActivity(i);
        }
    };
    }

    private OnClickListener saveButtonClicked() {
    return new OnClickListener() {
        @Override
        public void onClick(View view) {
            String comment = commentText.getText().toString();
            float rate = setRatingBar.getRating();
            String token = Constants.API_KEY;
            ApiInterface test = ApiInterface.retrofit.create(ApiInterface.class);
            Call<Response> cb = test.commentFood(foodID);
            cb.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, Response<Response> response) {

                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {

                }
            });


        }
    };
    }

    private OnClickListener tastedButtonclicked() {
        return new OnClickListener() {
            @Override
            public void onClick(View view) {
                String token = Constants.API_KEY;
                ApiInterface test = ApiInterface.retrofit.create(ApiInterface.class);
                Call<Response> cb = test.eatFood(foodID);

                cb.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, Response<Response> response) {

                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {

                    }
                });
            }
        };
    }

}