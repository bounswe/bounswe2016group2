package com.example.bounswegroup2.Models;
import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * The type User.
 */
public class User implements Serializable {

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("diets")
    @Expose
    private List<Diet> dietList = null;

    /**
     * Gets email.
     *
     * @return The  email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets username.
     *
     * @return The  username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets first name.
     *
     * @return The  firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName The first_name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return The  lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName The last_name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets diet list.
     *
     * @return the diet list
     */
    public List<Diet> getDietList() {
        return dietList;
    }

    /**
     * Sets diet list.
     *
     * @param dietList the diet list
     */
    public void setDietList(List<Diet> dietList) {
        this.dietList = dietList;
    }
}