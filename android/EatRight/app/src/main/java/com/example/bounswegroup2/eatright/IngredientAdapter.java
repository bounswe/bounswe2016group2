package com.example.bounswegroup2.eatright;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.bounswegroup2.Models.Ingredient;

import java.util.ArrayList;

/**
 * Created by Enes on 13.11.2016.
 */
public class IngredientAdapter extends ArrayAdapter<Ingredient> implements Filterable {
    private ArrayList<Ingredient> ingredients,listOfIngr,suggestions,tempItems;

    /**
     * Instantiates a new Ingredient adapter.
     *
     * @param context     the context
     * @param ingredients the ingredients
     */
    public IngredientAdapter(Context context, ArrayList<Ingredient> ingredients) {
        super(context, 0, ingredients);
        this.ingredients = ingredients;
        this.listOfIngr = ingredients;
        this.suggestions = new ArrayList<Ingredient>();
        this.tempItems = new ArrayList<Ingredient>();
        tempItems.addAll(ingredients);

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
        TextView tvEnergy = (TextView) convertView.findViewById(R.id.ingr_energy);
        TextView tvPro = (TextView) convertView.findViewById(R.id.ingr_pro);
        TextView tvCab = (TextView) convertView.findViewById(R.id.ingr_cab);
        TextView tvFat = (TextView) convertView.findViewById(R.id.ingr_fat);
        // Populate the data into the template view using the data object
        tvName.setText(ingr.getName());
        tvEnergy.setText(""+ingr.getEnergy());
        tvPro.setText(""+ingr.getProtein());
        tvCab.setText(""+ingr.getCarb());
        tvFat.setText(""+ingr.getFat());

        // Return the completed view to render on screen
        return convertView;
    }

    /**
     * Gets ingredients.
     *
     * @return the ingredients
     */
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
            return ((Ingredient) resultValue).getName();
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
            clear();
            if (results != null && results.count > 0) {
                addAll((ArrayList<Ingredient>) results.values);
            }else{
                addAll(tempItems);
            }
            notifyDataSetChanged();
        }
    };

    /**
     * Gets ingredient.
     *
     * @param s the s
     * @return the ingredient
     */
    public Ingredient getIngredient(String s) {
        for (Ingredient ingr:tempItems)
            if(ingr.getName().equalsIgnoreCase(s))
                return ingr;
        return null;
    }

}

