package com.example.bounswegroup2.Utils;

import com.example.bounswegroup2.Models.UserMore;

/**
 * Created by Enes on 21.10.2016.
 */

public class Constants {
    public static final String BASE_URL="http://52.208.59.70/";
    public static String API_KEY="d48d90e13fef9db1776efac64a181448";
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    public static String emailRegex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    public static String passRegex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{5,10}";
    public static String user="";
    public static Boolean isServer ;
}