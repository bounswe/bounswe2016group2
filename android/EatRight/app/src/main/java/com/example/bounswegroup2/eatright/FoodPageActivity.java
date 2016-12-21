package com.example.bounswegroup2.eatright;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.ListPopupWindow;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bounswegroup2.Models.AteFood;
import com.example.bounswegroup2.Models.Details;
import com.example.bounswegroup2.Models.Food;
import com.example.bounswegroup2.Models.FoodComment;
import com.example.bounswegroup2.Models.FoodRate;
import com.example.bounswegroup2.Models.Ingredient;
import com.example.bounswegroup2.Models.Restaurant;
import com.example.bounswegroup2.Models.RestaurantMore;
import com.example.bounswegroup2.Utils.ApiInterface;
import com.example.bounswegroup2.Utils.Constants;
import com.example.bounswegroup2.Utils.SessionManager;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The type Food page activity.
 */
public class FoodPageActivity extends AppCompatActivity {

    private TextView foodName;
    private TextView totalCalories;
    private TextView totalCarbohydrate;
    private TextView totalProtein;
    private TextView totalFat;
    private ArrayList<Ingredient> ingredients;
    private Details details;
    private int restaurantID;
    private ImageView imageView;
    private Button restBut;
    private RatingBar showRatingBar;
    private RatingBar setRatingBar;
    private EditText commentText;
    private int foodID;
    private Button evalBut;
    private Button btnOpenIngredients;
    private Button btnTastedIt;
    private ImageButton commentBut;
    private ArrayList<FoodComment> lOfc = new ArrayList<>();
    private boolean b = true;

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
        commentBut = (ImageButton) findViewById(R.id.comment_but);
        commentBut.setOnClickListener(commentButClicked());
        lOfc = (ArrayList<FoodComment>) b.getSerializable("comments");
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
        showRatingBar.setStepSize(0.01f);

        if (b.getSerializable("rate") != null) showRatingBar.setRating(Float.parseFloat(b.getSerializable("rate").toString()));
        setRatingBar = (RatingBar)findViewById(R.id.rate_food_taste_ratingBar) ;
        restBut = (Button) findViewById(R.id.restBut);
        restaurantID = (int) b.getSerializable("restaID");
        restBut.setText((String) b.getSerializable("restaName"));
        if(restaurantID != 0)
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

    private OnClickListener commentButClicked() {
    return new OnClickListener() {
        @Override
        public void onClick(View view) {
        Intent i = new Intent(FoodPageActivity.this,CommentPageActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("comments",lOfc);
            i.putExtras(b);
            startActivity(i);
            finish();
        }
    };
    }

    private OnClickListener restButClicked() {
    return new OnClickListener() {
        @Override
        public void onClick(View view) {

            ApiInterface test = ApiInterface.retrofit.create(ApiInterface.class);
            Call<RestaurantMore> cb = test.getRestaurantWithId(restaurantID);
            cb.enqueue(new Callback<RestaurantMore>() {
                @Override
                public void onResponse(Call<RestaurantMore> call, Response<RestaurantMore> response) {
                    RestaurantMore r = response.body();
                    Intent i = new Intent(FoodPageActivity.this,ServerPageActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable("resta", r);
                    i.putExtras(b);
                    startActivity(i);
                    finish();
                }

                @Override
                public void onFailure(Call<RestaurantMore> call, Throwable t) {

                }
            });

        }
    };
    }

    private OnClickListener saveButtonClicked() {
    return new OnClickListener() {
        @Override
        public void onClick(View view) {

            String comment = commentText.getText().toString();
            ApiInterface test = ApiInterface.retrofit.create(ApiInterface.class);
            HashMap<String,String> hm = new HashMap<>();
            hm.put("comment",comment);
            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(hm)).toString());
            Call<FoodComment> cb = test.commentFood("Token "+Constants.API_KEY,foodID,body);
            cb.enqueue(new Callback<FoodComment>() {
                @Override
                public void onResponse(Call<FoodComment> call, Response<FoodComment> response) {
                FoodComment fc = response.body();
                if (fc == null) b = false;
                }

                @Override
                public void onFailure(Call<FoodComment> call, Throwable t) {

                }
            });
            float rate = setRatingBar.getRating();
            HashMap<String,Float> hm2 = new HashMap<>();
            hm2.put("score",rate);
            RequestBody body2 = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(hm2)).toString());
            ApiInterface test2 = ApiInterface.retrofit.create(ApiInterface.class);
            Call<FoodRate> cb2 = test.rateFood("Token "+Constants.API_KEY,foodID,body2);
            cb2.enqueue(new Callback<FoodRate>() {
                @Override
                public void onResponse(Call<FoodRate> call, Response<FoodRate> response) {
                    FoodRate fr = response.body();
                    if(fr == null) b=false;
                }

                @Override
                public void onFailure(Call<FoodRate> call, Throwable t) {

                }
            });

            if (b){
                Toast toast =  Toast.makeText(FoodPageActivity.this,"Your comment and rating saved. Thank you!",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                toast.show();
            }
            else{
                Toast toast =   Toast.makeText(FoodPageActivity.this,"Something went wrong. Sorry!",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                toast.show();
            }
        }
    };
    }

    private OnClickListener tastedButtonclicked() {
        return new OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,Integer> hm = new HashMap<>();
                hm.put("value",1);
                RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(hm)).toString());
                ApiInterface test = ApiInterface.retrofit.create(ApiInterface.class);
                Call<AteFood> cb = test.eatFood("Token "+Constants.API_KEY,foodID,body);

                cb.enqueue(new Callback<AteFood>() {
                    @Override
                    public void onResponse(Call<AteFood> call, Response<AteFood> response) {
                      int code = response.code();
                        AteFood atf = response.body();
                        if(atf != null) {
                            Toast toast = Toast.makeText(FoodPageActivity.this,"Yep, you ate it!",Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                            toast.show();
                        }
                        else {
                            Toast toast = Toast.makeText(FoodPageActivity.this,"You should try again pal!",Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                            toast.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AteFood> call, Throwable t) {
                        System.out.println(t.getCause().toString());
                        System.out.println(t.getMessage().toString());
                    }
                });
            }
        };
    }

}