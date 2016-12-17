package com.example.bounswegroup2.eatright;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bounswegroup2.Models.Food;
import com.example.bounswegroup2.Models.FoodLess;
import com.example.bounswegroup2.Models.Restaurant;
import com.example.bounswegroup2.Utils.ApiInterface;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.drawable.arrow_down_float;
import static android.R.drawable.arrow_up_float;

public class ServerPageActivity extends AppCompatActivity {

    private TextView serverName;
    private Restaurant restaurant;
    private ImageView imageView;
    private ArrayList<Food> nFl = new ArrayList<Food>();
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_page);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        restaurant = (Restaurant) b.getSerializable("resta");

        serverName = (TextView) findViewById(R.id.server_name_text);
        serverName.setText(restaurant.getName());


        imageView = (ImageView)findViewById(R.id.server_image);
        Picasso.with(getApplicationContext())
                .load((String) restaurant.getPhoto())
                .placeholder(null)
                .fit()
                .centerInside()
                .into(imageView);

        //View view = getLayoutInflater().inflate(R.layout.activity_server_page, null);
        /*LayoutInflater inflater=getLayoutInflater();
        ViewGroup container = (ViewGroup) findViewById(R.id.food_listview);
        View view = inflater.inflate(R.layout.activity_server_page, null);
        container.addView(view);*/

        //inflate(R.layout.activity_server_page, null);
        //ViewGroup container = (ViewGroup) findViewById(R.id.food_listview);
        //View view = getLayoutInflater().inflate(R.layout.activity_server_page, null);
        //headerView = getLayoutInflater().inflate(R.layout.food_list_header,null);
        //container.addView(view);

        //ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.activity_server_page, container, false);
        /*lv = (ListView) view.findViewById(R.id.food_listview);
        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.food_list_header,lv,false);
        lv.addHeaderView(header, null, false);


        final FoodsAdapter adapter = new FoodsAdapter(ServerPageActivity.this.getApplicationContext(), nFl);
        lv.setAdapter(adapter);*/


        /*ApiInterface test = ApiInterface.retrofit.create(ApiInterface.class);

        Call<List<FoodLess>> cb = test.getFoods();

        cb.enqueue(new Callback<List<FoodLess>>() {
            @Override
            public void onResponse(Call<List<FoodLess>> call, final Response<List<FoodLess>> response) {
                final ArrayList<FoodLess> foodList = (ArrayList<FoodLess>) response.body();
                for (FoodLess fls: foodList){
                    int id = fls.getId();
                    ApiInterface test2 = ApiInterface.retrofit.create(ApiInterface.class);
                    Call<Food> cbFood = test2.getFoodWithId(id);
                    cbFood.enqueue(new Callback<Food>() {
                        @Override
                        public void onResponse(Call<Food> call2, Response<Food> response2) {
                            Food f = response2.body();
                            nFl.add(f);
                        }

                        @Override
                        public void onFailure(Call<Food> call2, Throwable t2) {

                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<List<FoodLess>> call, Throwable t) {
                System.out.println("ERROR");
            }
        });*/
    }


}
