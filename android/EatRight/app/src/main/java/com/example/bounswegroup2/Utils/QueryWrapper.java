package com.example.bounswegroup2.Utils;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.bounswegroup2.Utils.Constants.BASE_URL;

/**
 * This class is for wrapping the queries
 * for better readebility and refactorization
 */
public class QueryWrapper {

    /**
     * Gets options.
     *
     * @return the options
     */
    public Map<String, String> getOptions() {
        return options;
    }

    private Map<String, String> options;

    /**
     * Instantiates a new Query wrapper.
     */
    public QueryWrapper(){
        options = new HashMap<>();
        options.put("format","json");
    }


    /**
     * Get all foods.
     */
    public void getAllFoods(){

    }

    /**
     * Get all ingredients.
     */
    public void getAllIngredients(){

    }

    /**
     * Put.
     *
     * @param key the key
     * @param val the val
     */
    public void put(String key,String val){
        this.options.put(key,val);
    }
}