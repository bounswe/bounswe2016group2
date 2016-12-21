package com.example.bounswegroup2.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Enes on 21.10.2016.
 */
public class SessionManager {

    /**
     * Sets preferences.
     *
     * @param context the context
     * @param key     the key
     * @param value   the value
     */
    public static void setPreferences(Context context, String key, String value) {

        SharedPreferences.Editor editor = context.getSharedPreferences("User", Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * Gets preferences.
     *
     * @param context the context
     * @param key     the key
     * @return the preferences
     */
    public static String getPreferences(Context context, String key) {

        SharedPreferences prefs = context.getSharedPreferences("User",Context.MODE_PRIVATE);
        String position = prefs.getString(key, null);
        return position;
    }

    /**
     * Is user logged in boolean.
     *
     * @param context the context
     * @param key     the key
     * @return the boolean
     */
    public static boolean isUserLoggedIn(Context context,String key){
        SharedPreferences prefs = context.getSharedPreferences("User",Context.MODE_PRIVATE);
        return !(prefs.getString(key,null) == null);
    }

    /**
     * Clear credet.
     *
     * @param context the context
     */
    public static void clearCredet(Context context){
        SharedPreferences.Editor editor = context.getSharedPreferences("User", Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
    }
}