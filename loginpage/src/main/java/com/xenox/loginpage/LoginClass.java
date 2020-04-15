package com.xenox.loginpage;

import android.location.Location;

public class LoginClass {

    public static String getDistanceInKm(double Lat,double Long,double endLat,double endLong){
        float[] result = new float[0];
        Location.distanceBetween(Lat,Long,endLat,endLong,result);
        return "done this";


    }
}
