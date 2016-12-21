package com.example.bounswegroup2.Models;

/**
 * Created by Enes on 20.12.2016.
 */

import android.widget.CheckBox;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Tag.
 */
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

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Is checked boolean.
     *
     * @return the boolean
     */
    public boolean isChecked() {
        return isChecked;
    }

    /**
     * Sets checked.
     *
     * @param checked the checked
     */
    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}