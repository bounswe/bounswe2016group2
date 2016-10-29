package com.example.bounswegroup2.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class is to utilize retrofit instance
 * Created by Enes on 21.10.2016.
 */

public class API {
    public static final String BASE_URL = "";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
