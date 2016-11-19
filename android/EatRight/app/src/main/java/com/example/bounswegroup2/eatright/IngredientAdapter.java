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
import com.example.bounswegroup2.Models.Ingredient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Enes on 13.11.2016.
 */

public class IngredientAdapter extends ArrayAdapter<Ingredient> implements Filterable {
    private ArrayList<Ingredient> ingredients,suggestions,tempItems;

    public IngredientAdapter(Context context, ArrayList<Ingredient> ingredients) {
        super(context, 0, ingredients);
        this.ingredients = ingredients;
        this.suggestions = new ArrayList<Ingredient>();
        this.tempItems = new ArrayList<Ingredient>(this.ingredients);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Ingredient ingr = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ingr_raw_layout, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.ingr_name);
        TextView tvSlug = (TextView) convertView.findViewById(R.id.ingr_slug);
        // Populate the data into the template view using the data object
        tvName.setText(ingr.getName());
        tvSlug.setText(ingr.getSlug());
        // Return the completed view to render on screen
        return convertView;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    /**
     * Custom Filter implementation for custom suggestions we provide.
     */
    Filter nameFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            String str = ((Ingredient) resultValue).getName();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (Ingredient i : tempItems) {
                    if (i.getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        suggestions.add(i);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List<Ingredient> filterList = (ArrayList<Ingredient>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (Ingredient i : filterList) {
                    add(i);
                    notifyDataSetChanged();
                }
            }
        }
    };
}

