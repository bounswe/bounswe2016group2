package com.example.bounswegroup2.Utils;

/**
 * Created by Enes on 21.10.2016.
 */

public class Constants {
    public static final String BASE_URL="http://api.themoviedb.org/3/";
    public static final String API_KEY="d48d90e13fef9db1776efac64a181448";
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    public static String emailRegex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    public static String passRegex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{5,10}";
}
