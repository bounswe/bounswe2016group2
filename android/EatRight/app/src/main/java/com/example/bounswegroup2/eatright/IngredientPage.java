package com.example.bounswegroup2.eatright;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;

import com.example.bounswegroup2.Models.Ingredient;

import java.util.ArrayList;

public class IngredientPage extends AppCompatActivity {
private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_page);
       ingredients = (ArrayList<Ingredient>) getIntent().getExtras().get("ingredients");
        listView = (ListView) findViewById(R.id.ingr_list);
        View header = getLayoutInflater().inflate(R.layout.ingr_list_header,null);
        listView.addHeaderView(header);
        IngredientAdapter ingr = new IngredientAdapter(IngredientPage.this,ingredients);
        listView.setAdapter(ingr);
    }

    }

