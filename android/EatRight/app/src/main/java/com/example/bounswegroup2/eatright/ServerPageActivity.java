package com.example.bounswegroup2.eatright;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bounswegroup2.Models.Food;
import com.example.bounswegroup2.Models.FoodLess;
import com.example.bounswegroup2.Models.Restaurant;
import com.example.bounswegroup2.Models.RestaurantComment;
import com.example.bounswegroup2.Models.RestaurantRate;
import com.example.bounswegroup2.Models.RestaurantMore;
import com.example.bounswegroup2.Utils.ApiInterface;
import com.example.bounswegroup2.Utils.Constants;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.drawable.arrow_down_float;
import static android.R.drawable.arrow_up_float;

public class ServerPageActivity extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener{

    private TextView serverName;
    private RestaurantMore restaurant;
    private ImageView imageView;
    private ArrayList<Food> nFl = new ArrayList<Food>();
    private ListView lv;
    private RatingBar rateRest;
    private RatingBar postRateRest;
    private View headerView;
    private TextView tvName;
    private TextView tvRating;
    private ImageButton commentButton;
    private Button sendButton;
    private int restaurantID;
    private EditText commentText;
    private ArrayList<RestaurantComment> lOfc = new ArrayList<>();
    private boolean b = true;
    private boolean c = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_page);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        restaurant = (RestaurantMore) b.getSerializable("resta");

        restaurantID = restaurant.getId();

        lv = (ListView) findViewById(R.id.food_listview);
        serverName = (TextView) findViewById(R.id.server_name_text);
        serverName.setText(restaurant.getName());
        rateRest = (RatingBar) findViewById(R.id.rate_rest_ratingBar);
        rateRest.setStepSize(0.01f);
        postRateRest = (RatingBar) findViewById(R.id.post_rate_rest_ratingBar);
        postRateRest.setStepSize(0.01f);
        float rates=(float) restaurant.getRate();
        rateRest.setRating(rates);

        postRateRest.setOnRatingBarChangeListener(this);

        imageView = (ImageView)findViewById(R.id.server_image);
        Picasso.with(getApplicationContext())
                .load((String) restaurant.getPhoto())
                .placeholder(null)
                .fit()
                .centerInside()
                .into(imageView);
        commentText = (EditText) findViewById(R.id.server_comment_text);
        commentButton = (ImageButton) findViewById(R.id.server_comment_but);
        lOfc = (ArrayList<RestaurantComment>) restaurant.getComments();
        commentButton.setOnClickListener(commentButtonClicked());
        sendButton = (Button) findViewById(R.id.server_send_button);
        sendButton.setOnClickListener(sendButtonClicked());

        LayoutInflater inflater = getLayoutInflater();
        headerView = inflater.inflate(R.layout.food_list_header,null);
        tvName = (TextView) headerView.findViewById(R.id.food_list_header_name);
        tvRating = (TextView) headerView.findViewById(R.id.food_list_header_rating);
        headerView.findViewById(R.id.food_list_header_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return;
            }
        });
        fillFoodList();
    }

    private View.OnClickListener sendButtonClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String comment = commentText.getText().toString();
                ApiInterface test = ApiInterface.retrofit.create(ApiInterface.class);
                HashMap<String,String> hm = new HashMap<>();
                hm.put("comment",comment);
                RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(hm)).toString());
                Call<RestaurantComment> cb = test.commentRestaurant("Token "+Constants.API_KEY,restaurantID,body);
                cb.enqueue(new Callback<RestaurantComment>() {
                    @Override
                    public void onResponse(Call<RestaurantComment> call, Response<RestaurantComment> response) {
                        RestaurantComment fc = response.body();
                        if (fc == null) c = false;
                    }

                    @Override
                    public void onFailure(Call<RestaurantComment> call, Throwable t) {

                    }
                });

                if (c) Toast.makeText(ServerPageActivity.this,"Your comment has been sent. Thank you!",Toast.LENGTH_SHORT).show();
                else Toast.makeText(ServerPageActivity.this,"Something went wrong. Sorry!",Toast.LENGTH_SHORT).show();
            }
        };
    }
    public void onRatingChanged(RatingBar ratingBar,float rating, boolean fromUser){
        HashMap<String,Float> hm2 = new HashMap<>();
        float rate= postRateRest.getRating();
        hm2.put("score",rate);
        RequestBody body2 = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(hm2)).toString());
        ApiInterface test2 = ApiInterface.retrofit.create(ApiInterface.class);
        Call<RestaurantRate> cb2 = test2.rateRestaurant("Token "+ Constants.API_KEY,restaurantID,body2);
        cb2.enqueue(new Callback<RestaurantRate>() {
            @Override
            public void onResponse(Call<RestaurantRate> call, Response<RestaurantRate> response) {
                RestaurantRate fr = response.body();
                if(fr == null) b=false;
            }

            @Override
            public void onFailure(Call<RestaurantRate> call, Throwable t) {

            }
        });
        if (b) Toast.makeText(ServerPageActivity.this,"Your rating has been sent. Thank you!",Toast.LENGTH_SHORT).show();
        else Toast.makeText(ServerPageActivity.this,"Something went wrong. Sorry!",Toast.LENGTH_SHORT).show();
    }

    private View.OnClickListener commentButtonClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ServerPageActivity.this,ServerCommentPageActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("comments",lOfc);
                i.putExtras(b);
                startActivity(i);
                finish();
            }
        };
    }


    private void fillFoodList() {
        ArrayList<FoodLess> listFLess = (ArrayList<FoodLess>) restaurant.getFoods();
        for (FoodLess fls:listFLess) {
         int id = fls.getId();
            ApiInterface test = ApiInterface.retrofit.create(ApiInterface.class);
            Call<Food> cb = test.getFoodWithId(id);
            cb.enqueue(new Callback<Food>() {
                @Override
                public void onResponse(Call<Food> call, Response<Food> response) {
                    Food f = response.body();
                    nFl.add(f);
                }

                @Override
                public void onFailure(Call<Food> call, Throwable t) {

                }
            });
        }
        final FoodsAdapter adapter = new FoodsAdapter(ServerPageActivity.this,nFl);
        lv.setAdapter(adapter);
        lv.addHeaderView(headerView);
        lv.setDivider(ContextCompat.getDrawable(ServerPageActivity.this,android.R.color.black));
        lv.setDividerHeight(1);
        tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(adapter.isSorted()){
                    Collections.sort(nFl,Food.czToA);
                    adapter.setSorted(false);
                    tvName.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,arrow_down_float,0);
                }else {
                    Collections.sort(nFl,Food.caToZ);
                    adapter.setSorted(true);
                    tvName.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,arrow_up_float,0);
                }
                adapter.setFoods(nFl);
                adapter.notifyDataSetChanged();
            }
        });
        tvRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(adapter.isSorted()){
                    Collections.sort(nFl,Food.czToARating);
                    adapter.setSorted(false);
                    tvRating.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,arrow_down_float,0);
                }else {
                    Collections.sort(nFl,Food.caToZRating);
                    adapter.setSorted(true);
                    tvRating.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,arrow_up_float,0);
                }
                adapter.setFoods(nFl);
                adapter.notifyDataSetChanged();
            }
        });
    }


}