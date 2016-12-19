package com.example.bounswegroup2.eatright;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.bounswegroup2.Models.RestaurantComment;

import java.util.ArrayList;

/**
 * Created by gozdeberk on 19/12/16.
 */

public class ServerCommentAdapter extends ArrayAdapter<RestaurantComment> implements Filterable {
    private ArrayList<RestaurantComment> listOfComm;

    public ServerCommentAdapter(Context context, ArrayList<RestaurantComment> listOfComm) {
        super(context, 0, listOfComm);
        this.listOfComm = listOfComm;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        RestaurantComment cmm = getItem(position);
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
    public ArrayList<RestaurantComment> getCommnets() {
        return listOfComm;
    }
}
