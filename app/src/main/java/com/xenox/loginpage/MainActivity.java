package com.xenox.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String diistance = LoginClass.getDistanceInKm(0,0,0,0,0);
        Log.d("distance432342",diistance+"");


    }
}
