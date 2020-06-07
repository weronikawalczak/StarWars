package com.weronika.nask.util;

public class Util {

    public static int getCeilOfDivision(int a, int b){
        return (int) Math.ceil((double) a/b);
    }

    public static String getLastDigitFromURL(String url){
        String[] urlParts = url.split("/");
        return urlParts[urlParts.length-1];
    }
}
