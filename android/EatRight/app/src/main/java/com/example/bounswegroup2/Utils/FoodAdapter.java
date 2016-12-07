package com.example.bounswegroup2.Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bounswegroup2.Models.Food;
import com.example.bounswegroup2.eatright.R;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yigitozgumus on 11/23/16.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private ArrayList<Food> foods  = new ArrayList<>();
    private Context context;
    private static ClickListener clickListener;

    //Creating onClick listener for the ImageTexts
    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {
        ImageView foodImage;
        TextView foodTitle;
        public ViewHolder(View view) {
            super(view);
            foodImage = (ImageView)view.findViewById(R.id.food_image);
            foodImage.setOnClickListener(this);
            foodImage.setOnLongClickListener(this);
            foodTitle = (TextView) view.findViewById(R.id.food_title);
            foodTitle.setOnClickListener(this);
            foodTitle.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return false;
        }

    }

    public void setOnItemClickListener(ClickListener clickListener) {
        FoodAdapter.clickListener = clickListener;
    }

    public FoodAdapter(Context context, ArrayList<Food> foodList){
        this.context = context;
        this.foods = foodList;
    }

    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FoodAdapter.ViewHolder holder, int position) {
        holder.foodTitle.setText(foods.get(position).getName());
        Picasso.with(context)
                .load("https://nutritionaz.files.wordpress.com/2014/06/eat-right.jpg")
                .placeholder(R.drawable.eatright)
                .fit()
                .centerInside()
                .into(holder.foodImage);
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }
}
