package com.example.bounswegroup2.eatright;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bounswegroup2.Models.Restaurant;
import com.squareup.picasso.Picasso;

public class ServerPageActivity extends AppCompatActivity {

    private TextView serverName;
    private Restaurant restaurant;
    private ImageView imageView;

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
    }
}
