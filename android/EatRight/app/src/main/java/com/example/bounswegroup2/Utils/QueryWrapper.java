package com.example.bounswegroup2.Utils;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.bounswegroup2.Utils.Constants.BASE_URL;

/** This class is for wrapping the queries
 *  for better readebility and refactorization
 * */

public class QueryWrapper {

    public Map<String, String> getOptions() {
        return options;
    }

    private Map<String, String> options;

    public void usersQuery(){
        options = new HashMap<>();
        options.put("format","json");
    }

    public void getAllFoods(){
        options = new HashMap<>();
        options.put("format","json");
    }

    public void getAllIngredients(){
        options = new HashMap<>();
        options.put("format","json");
    }

}
