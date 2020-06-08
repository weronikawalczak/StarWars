package com.weronika.nask.util;

public class Util {

    public static int getCeilOfDivision(int a, int b){
        return (int) Math.ceil((double) a/b);
    }

    public static int getLastDigitFromURL(String url){
        String[] urlParts = url.split("/");
        return Integer.parseInt(urlParts[urlParts.length-1]);
    }
}
