package com.example.bounswegroup2.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {

    private Integer id;
    private String email;
    private String password;
    private String username;
    private String phone;
    private String apiKey;
    private ArrayList foodsLiked;
    private ArrayList foodsDisliked;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public User() {
    }

    /**
     *
     * @param foodsLiked
     * @param id
     * @param phone
     * @param username
     * @param email
     * @param password
     * @param foodsDisliked
     * @param apiKey
     */
    public User(Integer id, String email, String password, String username, String phone, String apiKey, ArrayList foodsLiked, ArrayList foodsDisliked) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.phone = phone;
        this.apiKey = apiKey;
        this.foodsLiked = foodsLiked;
        this.foodsDisliked = foodsDisliked;
    }

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The Id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     * The password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     * The password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     * The username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     * The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     * The phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param phone
     * The phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return
     * The apiKey
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     *
     * @param apiKey
     * The api_key
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     *
     * @return
     * The foodsLiked
     */
    public ArrayList getFoodsLiked() {
        return foodsLiked;
    }

    /**
     *
     * @param foodsLiked
     * The foods_liked
     */
    public void setFoodsLiked(ArrayList foodsLiked) {
        this.foodsLiked = foodsLiked;
    }

    /**
     *
     * @return
     * The foodsDisliked
     */
    public ArrayList getFoodsDisliked() {
        return foodsDisliked;
    }

    /**
     *
     * @param foodsDisliked
     * The foods_disliked
     */
    public void setFoodsDisliked(ArrayList foodsDisliked) {
        this.foodsDisliked = foodsDisliked;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}