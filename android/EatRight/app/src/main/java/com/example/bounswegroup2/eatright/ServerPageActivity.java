package com.example.bounswegroup2.eatright;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bounswegroup2.Models.Food;
import com.example.bounswegroup2.Models.FoodLess;
import com.example.bounswegroup2.Models.RestaurantMore;
import com.example.bounswegroup2.Utils.ApiInterface;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.drawable.arrow_down_float;
import static android.R.drawable.arrow_up_float;

public class ServerPageActivity extends AppCompatActivity {

    private TextView serverName;
    private RestaurantMore restaurant;
    private ImageView imageView;
    private ArrayList<Food> nFl = new ArrayList<Food>();
    private ListView lv;
    private View headerView;
    private TextView tvName;
    private TextView tvRating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_page);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        restaurant = (RestaurantMore) b.getSerializable("resta");
        lv = (ListView) findViewById(R.id.food_listview);
        serverName = (TextView) findViewById(R.id.server_name_text);
        serverName.setText(restaurant.getName());

        imageView = (ImageView)findViewById(R.id.server_image);
        Picasso.with(getApplicationContext())
                .load((String) restaurant.getPhoto())
                .placeholder(null)
                .fit()
                .centerInside()
                .into(imageView);
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