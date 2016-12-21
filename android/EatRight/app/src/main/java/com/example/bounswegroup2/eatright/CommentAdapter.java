package com.example.bounswegroup2.eatright;

/**
 * Created by Enes on 18.12.2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.bounswegroup2.Models.FoodComment;

import java.util.ArrayList;

/**
 * Created by Enes on 13.11.2016.
 */
public class CommentAdapter extends ArrayAdapter<FoodComment> implements Filterable {
    private ArrayList<FoodComment> listOfComm;

    /**
     * Instantiates a new Comment adapter.
     *
     * @param context    the context
     * @param listOfComm the list of comm
     */
    public CommentAdapter(Context context, ArrayList<FoodComment> listOfComm) {
        super(context, 0, listOfComm);
        this.listOfComm = listOfComm;
    }

    /**
     * Creates the view for the adapter
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        FoodComment cmm = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.comm_raw_layout, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.user_name_comm);
        TextView tvComment = (TextView) convertView.findViewById(R.id.comm_comm);
        // Populate the data into the template view using the data object
        tvName.setText(cmm.getUsername());
        tvComment.setText(cmm.getComment());
        // Return the completed view to render on screen
        return convertView;
    }

    /**
     * Gets commnets.
     *
     * @return the commnets
     */
    public ArrayList<FoodComment> getCommnets() {
        return listOfComm;
    }
}


