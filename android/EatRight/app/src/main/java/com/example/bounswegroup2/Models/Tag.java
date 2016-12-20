package com.example.bounswegroup2.Models;

/**
 * Created by Enes on 20.12.2016.
 */

import android.widget.CheckBox;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tag implements Serializable
{

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("name")
    @Expose
    private String name;

    private transient boolean isChecked;

    private final static long serialVersionUID = -2512770770618987053L;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}