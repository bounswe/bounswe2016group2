package com.example.bounswegroup2.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Enes on 20.12.2016.
 */
public class FoodTag implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
            return id;
        }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
            this.id = id;
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
    }
