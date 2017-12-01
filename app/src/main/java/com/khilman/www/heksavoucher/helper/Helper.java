package com.khilman.www.heksavoucher.helper;

/**
 * Created by root on 11/17/17.
 */

public class Helper {
    public static String readDate(String date){
        String[] parts = date.split("-");
        String result = parts[2] + "/" + parts[1] + "/" + parts[0];
        return result;
    }

}
