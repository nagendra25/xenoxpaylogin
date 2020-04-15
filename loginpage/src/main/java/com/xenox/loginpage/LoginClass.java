package com.xenox.loginpage;

import android.location.Location;

public class LoginClass {

    public static float getDistanceInKm(double Lat,double Long,double endLat,double endLong){
        float[] result = new float[0];
        Location.distanceBetween(Lat,Long,endLat,endLong,result);
        return result[0]/1000;

    }
}
