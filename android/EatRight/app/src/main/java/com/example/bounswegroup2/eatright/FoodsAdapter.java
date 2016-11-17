package com.example.bounswegroup2.eatright;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.bounswegroup2.Models.Food;

import java.util.ArrayList;

/**
 * Created by Enes on 13.11.2016.
 */

public class FoodsAdapter extends ArrayAdapter<Food> implements Filterable {
    private ArrayList<Food> foods = new ArrayList<Food>();

    public FoodsAdapter(Context context, ArrayList<Food> foods) {
        super(context, 0, foods);
        this.foods = foods;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Food food = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.raw_layout, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.food_name);
        TextView tvSlug = (TextView) convertView.findViewById(R.id.food_slug);
        // Populate the data into the template view using the data object
        tvName.setText(food.getName());
        tvSlug.setText(food.getSlug());
        // Return the completed view to render on screen
        return convertView;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

   /* @Override
    public Filter getFilter(final ArrayList<Food> allFoods) {

        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                ArrayList<Food> arrayListNames = (ArrayList<Food>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                ArrayList<Food> FilteredArrayNames = new ArrayList<Food>();

                // perform your search here using the searchConstraint String.

                constraint = constraint.toString().toLowerCase();
                for (int i = 0; i < allFoods.size(); i++) {
                    String dataNames = allFoods.get(i);
                    if (dataNames.toLowerCase().startsWith(constraint.toString()))  {
                        FilteredArrayNames.add(dataNames);
                    }
                }

                results.count = FilteredArrayNames.size();
                results.values = FilteredArrayNames;
                Log.e("VALUES", results.values.toString());

                return results;
            }
        };

        return filter;
    }*/
}
