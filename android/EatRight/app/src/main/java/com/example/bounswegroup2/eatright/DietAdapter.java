package com.example.bounswegroup2.eatright;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bounswegroup2.Models.Diet;
import com.example.bounswegroup2.Models.IngrLess;
import com.example.bounswegroup2.Models.Ingredient;

import java.util.ArrayList;

/**
 * Created by Enes on 21.12.2016.
 */

public class DietAdapter extends ArrayAdapter<Diet>{
private ArrayList<Diet> dietList;
    public DietAdapter(Context context, ArrayList<Diet> dietList) {
        super(context,0, dietList);
        this.dietList = dietList;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Diet d = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.diet_raw_layout, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.diet_name);
        TextView tvIngredients = (TextView) convertView.findViewById(R.id.diet_ingr);
        String s="";
        for (int i = 0; i<d.getIngredients().size()-1;i++) s+= d.getIngredients().get(i).getName() + ",";
        s+= d.getIngredients().get(d.getIngredients().size()-1).getName();
        tvName.setText(d.getName());
        tvIngredients.setText(s);
        // Return the completed view to render on screen
        return convertView;
    }
}
