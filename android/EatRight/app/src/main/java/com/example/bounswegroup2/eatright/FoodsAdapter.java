package com.example.bounswegroup2.eatright;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bounswegroup2.Models.Food;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Enes on 13.11.2016.
 */

public class FoodsAdapter extends ArrayAdapter<Food> implements Filterable {
    private ArrayList<Food> foods = new ArrayList<Food>();
    private boolean isSorted ;
    public FoodsAdapter(Context context, ArrayList<Food> foods) {
        super(context, 0, foods);
        this.foods = foods;
        isSorted = true;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Food food = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.food_raw_layout, parent, false);
        }
        // Lookup view for data population
        ImageView imageView = (ImageView) convertView.findViewById(R.id.food_image_view);
        TextView tvName = (TextView) convertView.findViewById(R.id.food_name);
        TextView tvSlug = (TextView) convertView.findViewById(R.id.food_slug);
        TextView tvComment = (TextView) convertView.findViewById(R.id.comment_food);
        TextView tvRating = (TextView) convertView.findViewById(R.id.rating_food);
        //tvRating.setText(food.getRates().get(0));
        tvComment.setText(food.getSlug());
        // Populate the data into the template view using the data object
        tvName.setText(food.getName());
        tvSlug.setText(food.getSlug());
        Picasso.with(getContext())
                .load(food.getPhoto())
                .placeholder(R.drawable.eatright)
                .fit()
                .centerInside()
                .into(imageView);
        // Return the completed view to render on screen
        return convertView;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }
    public void setFoods(ArrayList<Food> list){foods = list;}

    public boolean isSorted() {
        return isSorted;
    }

    public void setSorted(boolean sorted) {
        isSorted = sorted;
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
