package com.example.bounswegroup2.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

<<<<<<< HEAD
=======
/**
 * The type Restaurant.
 */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
public class Restaurant implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user")
    @Expose
    private Integer user;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("photo")
    @Expose
    private String photo;
<<<<<<< HEAD
    /*@SerializedName("rate")
    @Expose
    private double rate;*/

    private final static long serialVersionUID = 5741714203309533487L;

=======


    private final static long serialVersionUID = 5741714203309533487L;

    /**
     * Gets id.
     *
     * @return the id
     */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public Integer getId() {
        return id;
    }

<<<<<<< HEAD
=======
    /**
     * Sets id.
     *
     * @param id the id
     */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public void setId(Integer id) {
        this.id = id;
    }

<<<<<<< HEAD
=======
    /**
     * Gets user.
     *
     * @return the user
     */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public Integer getUser() {
        return user;
    }

<<<<<<< HEAD
=======
    /**
     * Sets user.
     *
     * @param user the user
     */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public void setUser(Integer user) {
        this.user = user;
    }

<<<<<<< HEAD
=======
    /**
     * Gets slug.
     *
     * @return the slug
     */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public String getSlug() {
        return slug;
    }

<<<<<<< HEAD
=======
    /**
     * Sets slug.
     *
     * @param slug the slug
     */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public void setSlug(String slug) {
        this.slug = slug;
    }

<<<<<<< HEAD
=======
    /**
     * Gets name.
     *
     * @return the name
     */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public String getName() {
        return name;
    }

<<<<<<< HEAD
=======
    /**
     * Sets name.
     *
     * @param name the name
     */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public void setName(String name) {
        this.name = name;
    }

<<<<<<< HEAD
=======
    /**
     * Gets description.
     *
     * @return the description
     */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public String getDescription() {
        return description;
    }

<<<<<<< HEAD
=======
    /**
     * Sets description.
     *
     * @param description the description
     */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public void setDescription(String description) {
        this.description = description;
    }

<<<<<<< HEAD
=======
    /**
     * Gets photo.
     *
     * @return the photo
     */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public String getPhoto() {
        return photo;
    }

<<<<<<< HEAD
=======
    /**
     * Sets photo.
     *
     * @param photo the photo
     */
>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
    public void setPhoto(String photo) {
        this.photo = photo;
    }

<<<<<<< HEAD
    /*public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }*/
=======

>>>>>>> 1f7dc78b9a6b58937cb36228b6573abd0ef70e2b
}