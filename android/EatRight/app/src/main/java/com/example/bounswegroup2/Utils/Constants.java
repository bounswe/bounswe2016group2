package com.example.bounswegroup2.Utils;

/**
 * Created by Enes on 21.10.2016.
 */
public class Constants {
    /**
     * The constant BASE_URL.
     */
    public static final String BASE_URL="http://52.208.59.70/";
    /**
     * The constant API_KEY.
     */
    public static String API_KEY="d48d90e13fef9db1776efac64a181448";
    /**
     * The constant CONNECTION_TIMEOUT.
     */
    public static final int CONNECTION_TIMEOUT=10000;
    /**
     * The constant READ_TIMEOUT.
     */
    public static final int READ_TIMEOUT=15000;
    /**
     * The constant emailRegex.
     */
    public static String emailRegex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    /**
     * The constant passRegex.
     */
    public static String passRegex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{5,10}";
    /**
     * The constant user.
     */
    public static String user="";
    /**
     * The constant isServer.
     */
    public static Boolean isServer ;
}