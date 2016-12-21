package com.example.bounswegroup2.eatright;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.bounswegroup2.Models.Tag;

import java.util.ArrayList;

/**
 * Created by Enes on 20.12.2016.
 */
public class TagAdapter extends ArrayAdapter<Tag>  {
    private ArrayList<Tag> lot = new ArrayList<>();

    /**
     * Instantiates a new Tag adapter.
     *
     * @param context the context
     * @param lot     the lot
     */
    public TagAdapter(Context context, ArrayList<Tag> lot) {
        super(context, 0,lot);
        this.lot = lot;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
         Tag tag = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tag_raw_layout, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tag_name);
        TextView tvDes = (TextView) convertView.findViewById(R.id.tag_desc);
        tvName.setText(tag.getName());
        tvDes.setText(tag.getDescription());
        return convertView;
    }

    /**
     * Gets tags.
     *
     * @return the tags
     */
    public ArrayList<Tag> getTags() {
        return lot;
    }

    /**
     * Set tags.
     *
     * @param list the list
     */
    public void setTags(ArrayList<Tag> list){lot = list;}

}
